package com.tensai.grenius.ui.home;

import com.tensai.grenius.ui.base.MvpPresenter;
import com.tensai.grenius.ui.base.MvpView;

/**
 * Created by rishabhpanwar on 24/06/17.
 */

public interface HomePresenter <V extends HomeView> extends MvpPresenter<V> {

    void getUserDetails();
}
