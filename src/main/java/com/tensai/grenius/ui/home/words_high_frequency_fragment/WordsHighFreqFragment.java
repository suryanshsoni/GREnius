package com.tensai.grenius.ui.home.words_high_frequency_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tensai.grenius.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordsHighFreqFragment extends Fragment {


    public WordsHighFreqFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_words_high_freq, container, false);
    }

}
