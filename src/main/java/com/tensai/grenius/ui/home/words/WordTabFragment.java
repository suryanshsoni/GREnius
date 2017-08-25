package com.tensai.grenius.ui.home.words;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tensai.grenius.R;
import com.tensai.grenius.ui.base.BaseFragment;
import com.tensai.grenius.ui.home.words.words_all_fragment.WordsAllFragment;
import com.tensai.grenius.ui.home.words.words_high_frequency_fragment.WordsHighFreqFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordTabFragment extends BaseFragment {


    @BindView(R.id.word_tab_layout)
    TabLayout wordTabLayout;
    @BindView(R.id.vp_word)
    ViewPager vpWord;
    Unbinder unbinder;
    public WordTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_word_tab, container, false);

        unbinder = ButterKnife.bind(this, view);
        wordTabLayout.addTab(wordTabLayout.newTab().setText("All"));
        wordTabLayout.addTab(wordTabLayout.newTab().setText("Frequent"));
        wordTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPagerAdapter adapter = new ViewPagerAdapter
                (getChildFragmentManager(), wordTabLayout.getTabCount());
        vpWord.setAdapter(adapter);
        vpWord.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(wordTabLayout));
        wordTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpWord.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        int numoftabs;
        public ViewPagerAdapter(FragmentManager fm, int numoftabs) {
            super(fm);
            this.numoftabs= numoftabs;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            switch (position) {
                case 0:
                    WordsAllFragment tab1 = new WordsAllFragment();
                    return tab1;
                case 1:
                    WordsHighFreqFragment tab2 = new WordsHighFreqFragment();
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
