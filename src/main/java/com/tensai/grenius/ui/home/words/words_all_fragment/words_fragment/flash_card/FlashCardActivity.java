package com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.flash_card;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.BaseActivity;
import com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.flash_card.CardFragment;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FlashCardActivity extends BaseActivity implements CardFragment.Callback {
    List<Word> wordlist;
    int startposition;

    MyAdapter mAdapter;
    @BindView(R.id.container)
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        wordlist = intent.getParcelableArrayListExtra("wordlist");
        startposition = intent.getIntExtra("position",0);
       // Log.i("position", Integer.toString(startposition));
        // Log.i("wordlist", wordlist.get(0).getWord());


        ButterKnife.bind(this);
        mAdapter = new MyAdapter(getSupportFragmentManager(), wordlist,0);
        mPager.setAdapter(mAdapter);

        mPager.setCurrentItem(startposition);

    }

    public static class MyAdapter extends FragmentStatePagerAdapter {
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

            return CardFragment.show(wordlist.get(position));
        }
/*
        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            Fragment mCurrentFragment = ((Fragment) object);

            super.setPrimaryItem(container, position, object);
        }
  */
    }

    @Override
    public void showTutorial(TutorialDialogFragment.TutorialCallback callback) {
        TutorialDialogFragment tutorialDialogFragment = new TutorialDialogFragment(callback);
        tutorialDialogFragment.show(getFragmentManager(),"tutorial");
    }
}