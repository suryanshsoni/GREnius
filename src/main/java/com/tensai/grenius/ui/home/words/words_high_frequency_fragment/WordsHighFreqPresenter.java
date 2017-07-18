package com.tensai.grenius.ui.home.words.words_high_frequency_fragment;

import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.MvpPresenter;

import java.util.ArrayList;

/**
 * Created by ishitabhandari on 06/07/17.
 */

public interface WordsHighFreqPresenter <V extends WordsHighFreqView> extends MvpPresenter<V> {

    void getHighFrequentWords();

    }
