package com.tensai.grenius.ui.home;

import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by rishabhpanwar on 24/06/17.
 */

public class HomePresenterImpl <V extends HomeView> extends BasePresenter<V> implements HomePresenter<V> {

    @Inject
    public HomePresenterImpl(DataManager dataManager) {
        super(dataManager);
    }
}
