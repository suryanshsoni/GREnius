package com.tensai.grenius.ui.home.words.words_high_frequency_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

public class WordsHighFreqFragment extends BaseFragment implements WordsHighFreqView {

    @Inject
    WordsHighFreqPresenter<WordsHighFreqView> presenter;

    public WordsHighFreqFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_words_high_freq, container, false);
        getActivityComponent().inject(this);
        presenter.onAttach(this);

        return view;
    }

    @Override
    public void showWords(List<Word> fWords) {

    }
}
