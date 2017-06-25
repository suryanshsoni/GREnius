package com.tensai.grenius.ui.home.words_all_fragment;

import com.tensai.grenius.ui.base.MvpPresenter;

import java.util.List;

/**
 * Created by ishitabhandari on 25/06/17.
 */

public interface WordsAllPresenter <V extends WordsAllView> extends MvpPresenter<V>{

    void getWordlist();
}
