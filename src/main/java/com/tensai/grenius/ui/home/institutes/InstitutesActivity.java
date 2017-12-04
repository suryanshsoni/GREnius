package com.tensai.grenius.ui.home.institutes;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Institute;
import com.tensai.grenius.ui.base.BaseActivity;

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

    List<Institute> gre_institutes, cat_institutes;
    @BindView(R.id.fl_institute)
    FrameLayout flInstitute;

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

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void showInstitutes(List<Institute> gre_institutes, List<Institute> cat_institutes) {
        this.gre_institutes = gre_institutes;
        this.cat_institutes = cat_institutes;
        Log.i("insti:", "" + gre_institutes + cat_institutes);
        try {
            if (cat_institutes.size() == 0) {
                singleFrag(gre_institutes);
            } else if(gre_institutes.size() == 0){
                singleFrag(cat_institutes);
            } else {
                instituteTabLayout.addTab(instituteTabLayout.newTab().setText("GRE/GMAT/SAT"));
                instituteTabLayout.addTab(instituteTabLayout.newTab().setText("CAT/NMAT/XAT"));
                instituteTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                final ViewPagerAdapter adapter = new ViewPagerAdapter
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void singleFrag (List<Institute> institutes){
        Bundle args = new Bundle();
        GREIntFragment intFragment = new GREIntFragment();
        args.putParcelableArrayList("institutelist", (ArrayList<? extends Parcelable>) institutes);
        intFragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fl_institute, intFragment)
                .commit();
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
            switch (position) {
                case 0:
                    GREIntFragment tab1 = new GREIntFragment();
                    args.putParcelableArrayList("institutelist", (ArrayList<? extends Parcelable>) gre_institutes);
                    tab1.setArguments(args);
                    return tab1;
                case 1:
                    CATIntFragment tab2 = new CATIntFragment();
                    args.putParcelableArrayList("institutelist", (ArrayList<? extends Parcelable>) cat_institutes);
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
