package com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.flash_card;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.BaseActivity;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlashCardActivity extends BaseActivity implements CardView, TutorialDialogFragment.TutorialCallback {
    List<Word> wordlist;
    int startposition;

    MyAdapter mAdapter;
    @BindView(R.id.container)
    ViewPager mPager;

    @Inject
    CardPresenter<CardView> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        wordlist = intent.getParcelableArrayListExtra("wordlist");
        startposition = intent.getIntExtra("position",0);

        getActivityComponent().inject(this);
        presenter.onAttach(FlashCardActivity.this);

       // Log.i("position", Integer.toString(startposition));
        // Log.i("wordlist", wordlist.get(0).getWord());


        ButterKnife.bind(this);
        mAdapter = new MyAdapter(getSupportFragmentManager(), wordlist,0);
        mPager.setAdapter(mAdapter);

        mPager.setCurrentItem(startposition);
        if(!presenter.getTutorial()) {
            //show tutorial
            showDialog();
            Log.i("Tut:","show dialog called now");
        }
    }
    @Override
    public void dialogDismissed() {
        Log.i("Tut:","in activity dialog dismissed method ");
       int index = mPager.getCurrentItem();
        mAdapter = (MyAdapter) mPager.getAdapter();
        CardFragment cardFragment = mAdapter.getFragment(index);
        cardFragment.dialogDismissed();
        Log.i("Tut:","card fragment dismiss method called ");
    }

    @Override
    public void setView(Word object) {
        //reqd method
    }

    public static class MyAdapter extends FragmentStatePagerAdapter {
        private SparseArray<CardFragment> mPageReferenceMap = new SparseArray<>();
        List<Word> wordlist;
        int startpos = 0;

        public MyAdapter(FragmentManager fragmentManager, List<Word> wordlist, int startpos) {
            super(fragmentManager);
            this.wordlist = wordlist;
            this.startpos = startpos;
        }

        @Override
        public int getCount() {
            int size=0;
            if(wordlist!=null) {
                size=wordlist.size();
            }
            
            return size;
        }
        @Override
        public CardFragment getItem(int position) {
            //call fragment method
            CardFragment cardFragment = CardFragment.show(wordlist.get(position));
            mPageReferenceMap.put(position, cardFragment);
            return cardFragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            super.destroyItem(container, position, object);
            mPageReferenceMap.remove(Integer.valueOf(position));
        }

        public CardFragment getFragment (int key){
            CardFragment reference = mPageReferenceMap.get(key);
            if (reference!=null){
                return reference;
            }else {
                return null;
            }
        }
    }

    public void showDialog() {

        TutorialDialogFragment tutorialDialogFragment = new TutorialDialogFragment();
        tutorialDialogFragment.show(getFragmentManager(),"tutorial");
        Log.i("Tut:","dialog fragment called");

    }

}