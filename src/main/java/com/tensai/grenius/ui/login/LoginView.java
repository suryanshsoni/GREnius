package com.tensai.grenius.ui.login;

import com.facebook.FacebookCallback;
import com.facebook.login.LoginResult;
import com.tensai.grenius.ui.base.MvpView;

/**
 * Created by Pavilion on 22-06-2017.
 */

public interface LoginView extends MvpView {

    void openHomeActivity();

    void checkGooglePlayServices();
    //void registerFacebookCallbackResult(FacebookCallback<LoginResult> loginResultFacebookCallback);
    //void verifyPhoneNumber(String mobile);
}
