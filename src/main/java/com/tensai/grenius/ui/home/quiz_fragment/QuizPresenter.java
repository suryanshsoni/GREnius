package com.tensai.grenius.ui.home.quiz_fragment;

import com.tensai.grenius.ui.base.MvpPresenter;

/**
 * Created by Pavilion on 30-06-2017.
 */

public interface QuizPresenter<V extends QuizView> extends MvpPresenter<V> {
    void onViewInitialized(int position);
    void onCardExhausted(int correct,int incorrect);
}
