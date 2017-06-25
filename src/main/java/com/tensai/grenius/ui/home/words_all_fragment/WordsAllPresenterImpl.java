package com.tensai.grenius.ui.home.words_all_fragment;

import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.ui.base.BasePresenter;
import com.tensai.grenius.ui.home.HomePresenter;

import java.util.List;

/**
 * Created by ishitabhandari on 25/06/17.
 */

public class WordsAllPresenterImpl <V extends WordsAllView> extends BasePresenter<V> implements WordsAllPresenter<V> {
    public WordsAllPresenterImpl(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getWordlist() {
        List<String> wordlists = getDataManager().getAllWordlists();
        getMvpView().showWordlists(wordlists);
    }
}
