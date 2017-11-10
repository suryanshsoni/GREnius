package com.tensai.grenius.ui.home.institutes;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Institute;
import com.tensai.grenius.ui.base.BaseActivity;
import com.tensai.grenius.ui.home.words.WordTabFragment;
import com.tensai.grenius.ui.home.words.words_all_fragment.WordsAllFragment;
import com.tensai.grenius.ui.home.words.words_high_frequency_fragment.WordsHighFreqFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class InstitutesActivity extends BaseActivity implements InstituteView, CATIntFragment.OnFragmentInteractionListener, GREIntFragment.OnFragmentInteractionListener {

    @Inject
    InstitutePresenter<InstituteView> presenter;
    Unbinder unbinder;
    @BindView(R.id.institute_tab_layout)
    TabLayout instituteTabLayout;
    @BindView(R.id.vp_institute)
    ViewPager vpInstitute;

    List<Institute> institutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institutes);

        getActivityComponent().inject(this);
        presenter.onAttach(InstitutesActivity.this);
        unbinder = ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        presenter.getInstitutes();

        instituteTabLayout.addTab(instituteTabLayout.newTab().setText("GRE/GMAT/SAT"));
        instituteTabLayout.addTab(instituteTabLayout.newTab().setText("CAT/NMAT/XAT"));
        instituteTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void showInstitutes(List<Institute> institutes) {
        this.institutes=institutes;
        Log.i("insti:",""+institutes);

        final InstitutesActivity.ViewPagerAdapter adapter = new InstitutesActivity.ViewPagerAdapter
                (getSupportFragmentManager(), instituteTabLayout.getTabCount());
        vpInstitute.setAdapter(adapter);
        vpInstitute.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(instituteTabLayout));
        instituteTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpInstitute.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        int numoftabs;

        public ViewPagerAdapter(FragmentManager fm, int numoftabs) {
            super(fm);
            this.numoftabs = numoftabs;
        }

        @Override
        public Fragment getItem(int position) {

            Bundle args = new Bundle();
            args.putParcelableArrayList("institutelist", (ArrayList<? extends Parcelable>) institutes);
            switch (position) {
                case 0:
                    GREIntFragment tab1 = new GREIntFragment();
                    tab1.setArguments(args);
                    return tab1;
                case 1:
                    CATIntFragment tab2 = new CATIntFragment();
                    tab2.setArguments(args);
                    return tab2;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return numoftabs;
        }
    }

}
