package com.tensai.grenius.ui.home.dashboard_fragment.word_of_day;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.tensai.grenius.R;
import com.tensai.grenius.model.WordOfDay;
import com.tensai.grenius.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LastWODActivity extends BaseActivity implements LastWODView {

    static List<WordOfDay> WODlist;
    ViewPagerAdapter viewPagerAdapter;
    @Inject
    FirebaseAnalytics firebaseAnalytics;
    @Inject
    LastWODPresenter<LastWODView> presenter;
    @BindView(R.id.container)
    ViewPager container;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_wod);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        try{
            ab.setDisplayHomeAsUpEnabled(true);
        }catch(Exception e){
            e.printStackTrace();
        }

        getActivityComponent().inject(this);
        presenter.onAttach(this);
        presenter.getLastWords();
    }

    @Override
    public void setView(List<WordOfDay> wordOfDays) {
        WODlist = wordOfDays;
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), WODlist);
        container.setAdapter(viewPagerAdapter);
    }

    public static class ViewPagerAdapter extends FragmentStatePagerAdapter {
        List<WordOfDay> wordlist;

        public ViewPagerAdapter(FragmentManager fm, List<WordOfDay> words) {
            super(fm);
            wordlist = words;
        }

        @Override
        public LastWODFragment getItem(int position) {
            LastWODFragment lastWODFragment = LastWODFragment.show(wordlist.get(position));
            return lastWODFragment;
        }

        @Override
        public int getCount() {
            int size=0;
            try{
                size= wordlist.size();
            }catch (Exception e){
                e.printStackTrace();
            }
            return size;
        }
    }
}
