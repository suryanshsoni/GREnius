package com.tensai.grenius.ui.home.words_all_fragment;

import android.util.EventLog;

import com.tensai.grenius.ui.base.MvpPresenter;
import java.util.Map;

/**
 * Created by ishitabhandari on 25/06/17.
 */

public interface WordsAllPresenter <V extends WordsAllView> extends MvpPresenter<V>{

    void getWordlist();
    void onEvent(int position1, int position2);
}
