package com.tensai.grenius.ui.home.dashboard_fragment.word_of_day;

import com.tensai.grenius.model.WordOfDay;
import com.tensai.grenius.ui.base.MvpPresenter;

import java.util.List;

/**
 * Created by rishabhpanwar on 01/09/17.
 */

public interface LastWODPresenter <V extends LastWODView> extends MvpPresenter<V> {
    List<WordOfDay> getLastWords();

    void speak(String word);
}
