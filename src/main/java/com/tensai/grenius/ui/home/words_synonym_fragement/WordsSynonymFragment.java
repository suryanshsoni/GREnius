package com.tensai.grenius.ui.home.words_synonym_fragement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tensai.grenius.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordsSynonymFragment extends Fragment {


    public WordsSynonymFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_words_synonym, container, false);
    }

}
