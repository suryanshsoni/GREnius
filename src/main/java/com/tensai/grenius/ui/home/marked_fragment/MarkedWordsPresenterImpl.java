package com.tensai.grenius.ui.home.marked_fragment;

import android.util.Log;

import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ishitabhandari on 18/07/17.
 */

public class MarkedWordsPresenterImpl<V extends MarkedWordsView> extends BasePresenter<V> implements MarkedWordsPresenter<V> {
    @Inject
    public MarkedWordsPresenterImpl(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getMarkedWords() {
        List<Word> list = getDataManager().getMarkedWords();
        Log.i("Mark:", "After list"+list);
        getMvpView().setView(list);
    }
}
