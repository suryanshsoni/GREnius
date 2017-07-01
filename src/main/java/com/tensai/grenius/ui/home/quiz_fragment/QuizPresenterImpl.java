package com.tensai.grenius.ui.home.quiz_fragment;

import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Pavilion on 30-06-2017.
 */

public class QuizPresenterImpl <V extends QuizView> extends BasePresenter<V> implements QuizPresenter<V> {
    @Inject
    public QuizPresenterImpl(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onViewInitialized(int position) {

    }
}
