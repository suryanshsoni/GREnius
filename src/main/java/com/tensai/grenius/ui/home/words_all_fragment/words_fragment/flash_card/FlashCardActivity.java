package com.tensai.grenius.ui.home.words_all_fragment.words_fragment.flash_card;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Word;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FlashCardActivity extends FragmentActivity {
    List<Word> wordlist;
    int startposition;

    MyAdapter mAdapter;
    @BindView(R.id.container)
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        wordlist = intent.getParcelableArrayListExtra("wordlist");
        startposition = intent.getIntExtra("position",0);
        Log.i("position", Integer.toString(startposition));
        Log.i("wordlist", wordlist.get(0).getWord());


        setContentView(R.layout.activity_flash_card);
        ButterKnife.bind(this);
        mAdapter = new MyAdapter(getSupportFragmentManager(), wordlist, startposition);
        mPager.setAdapter(mAdapter);
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
            return CardFragment.show(wordlist.get(startpos));
        }
    }
}