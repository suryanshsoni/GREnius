package com.tensai.grenius.ui.home.institutes;

import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by rishabhpanwar on 26/10/17.
 */

public class InstitutePresenterImpl <V extends InstituteView> extends BasePresenter<V> implements InstitutePresenter<V> {

    @Inject
    public InstitutePresenterImpl(DataManager dataManager) {
        super(dataManager);
    }
}
