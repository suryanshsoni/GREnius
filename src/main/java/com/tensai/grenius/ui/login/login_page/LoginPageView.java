package com.tensai.grenius.ui.login.login_page;

import com.facebook.FacebookCallback;
import com.facebook.login.LoginResult;
import com.tensai.grenius.ui.base.MvpView;

/**
 * Created by ishitabhandari on 27/08/17.
 */

public interface LoginPageView extends MvpView {

    void initiateFbLogin();
    void registerFacebookCallbackResult(FacebookCallback<LoginResult> loginResultFacebookCallback);
    void openHomeActivity();
    void enterPasslink(boolean bool);
}
