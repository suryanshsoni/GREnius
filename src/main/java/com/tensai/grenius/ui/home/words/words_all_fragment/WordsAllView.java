package com.tensai.grenius.ui.home.words.words_all_fragment;

import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.MvpView;

import java.util.List;

/**
 * Created by ishitabhandari on 25/06/17.
 */

public interface WordsAllView extends MvpView {
    void showWordlists(List<Word> wordlists);
    void setPresenter(WordsAllPresenter<WordsAllView> presenter);
}
