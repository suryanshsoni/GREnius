package com.tensai.grenius.ui.login;

import android.content.Context;

import com.tensai.grenius.ui.base.MvpPresenter;

/**
 * Created by Pavilion on 22-06-2017.
 */

public interface LoginPresenter<V extends LoginView> extends MvpPresenter<V> {

    boolean getTutorial();
}
