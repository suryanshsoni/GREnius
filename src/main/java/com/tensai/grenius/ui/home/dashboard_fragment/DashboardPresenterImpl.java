package com.tensai.grenius.ui.home.dashboard_fragment;

import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.ui.base.BasePresenter;

/**
 * Created by rishabhpanwar on 24/06/17.
 */

public class DashboardPresenterImpl <V extends DashboardView> extends BasePresenter<V> implements DashboardPresenter<V> {
    public DashboardPresenterImpl(DataManager dataManager) {
        super(dataManager);
    }
}
