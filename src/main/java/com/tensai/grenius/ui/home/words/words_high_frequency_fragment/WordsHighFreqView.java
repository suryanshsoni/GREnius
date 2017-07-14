package com.tensai.grenius.ui.home.words.words_high_frequency_fragment;

import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.MvpView;

import java.util.List;

/**
 * Created by ishitabhandari on 06/07/17.
 */

public interface WordsHighFreqView extends MvpView{

    void showWords(List<Word> fWords);

}
