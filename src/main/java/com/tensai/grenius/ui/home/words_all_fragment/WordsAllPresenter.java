package com.tensai.grenius.ui.home.words_all_fragment;

import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.MvpPresenter;

import java.util.ArrayList;


/**
 * Created by ishitabhandari on 25/06/17.
 */

public interface WordsAllPresenter <V extends WordsAllView> extends MvpPresenter<V>{

    void getAllWords();
    ArrayList<Word> getWordlist(int fromIndex, int toIndex);

}
