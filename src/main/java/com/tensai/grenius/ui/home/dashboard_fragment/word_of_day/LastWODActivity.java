package com.tensai.grenius.ui.home.dashboard_fragment.word_of_day;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.tensai.grenius.R;
import com.tensai.grenius.model.WordOfDay;
import com.tensai.grenius.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LastWODActivity extends BaseActivity implements LastWODView, LastWODFragment.OnFragmentInteractionListener {

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
    @BindView(R.id.btn_left)
    ImageButton btnLeft;
    @BindView(R.id.rl_left)
    RelativeLayout rlLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_wod);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        try {
            ab.setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        getActivityComponent().inject(this);
        presenter.onAttach(this);
        if (isNetworkConnected()){
            presenter.getLastWords();
        }else {
            showSnackbar(container,getResources().getString(R.string.network_error));
        }
    }

    @Override
    public void setView(List<WordOfDay> wordOfDays) {
        WODlist = wordOfDays;
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), WODlist);
        container.setAdapter(viewPagerAdapter);
        container.setCurrentItem(29);
        final Animation anim_right = AnimationUtils.loadAnimation(LastWODActivity.this, R.anim.move_horizontal_right);
        anim_right.setFillAfter(true);
        rlLeft.startAnimation(anim_right);
        rlLeft.setVisibility(View.VISIBLE);
        anim_right.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation anim_left = AnimationUtils.loadAnimation(LastWODActivity.this, R.anim.move_horizontal_left);
                anim_left.setFillAfter(true);
                rlLeft.startAnimation(anim_left);
                anim_left.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        rlLeft.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void onButtonLeft() {
        container.setCurrentItem(container.getCurrentItem() - 1);
    }

    @Override
    public void speak(String word) {
        presenter.speak(word);
    }

    public static class ViewPagerAdapter extends FragmentStatePagerAdapter {
        List<WordOfDay> wordlist;

        public ViewPagerAdapter(FragmentManager fm, List<WordOfDay> words) {
            super(fm);
            wordlist = words;
        }

        @Override
        public LastWODFragment getItem(int position) {
            LastWODFragment lastWODFragment = new LastWODFragment();
            try {
                lastWODFragment = LastWODFragment.show(wordlist.get(wordlist.size() - 1 - position));
                Log.i("SZAX:",""+wordlist.get(wordlist.size() - 1 - position).getWord());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return lastWODFragment;
        }

        @Override
        public int getCount() {
            int size = 0;
            try {
                size = wordlist.size();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return size;
        }
    }
}
