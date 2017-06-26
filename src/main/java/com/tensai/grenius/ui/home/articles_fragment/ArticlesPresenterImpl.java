package com.tensai.grenius.ui.home.articles_fragment;

import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.ui.base.BasePresenter;

/**
 * Created by rishabhpanwar on 25/06/17.
 */

public class ArticlesPresenterImpl <V extends ArticlesView> extends BasePresenter<V> implements ArticlesPresenter<V> {
    public ArticlesPresenterImpl(DataManager dataManager) {
        super(dataManager);
    }
}
