package com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment;

import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by ishitabhandari on 26/06/17.
 */

public class WordsPresenterImpl <V extends WordsView> extends BasePresenter<V> implements WordsPresenter<V> {

    @Inject
    public WordsPresenterImpl(DataManager dataManager) {
        super(dataManager);
    }
}
