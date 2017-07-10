package com.tensai.grenius.ui.home.dashboard_fragment;

import com.tensai.grenius.ui.base.MvpPresenter;

/**
 * Created by rishabhpanwar on 24/06/17.
 */

public interface DashboardPresenter <V extends DashboardView> extends MvpPresenter<V> {

    void getDashboardArticles();
}
