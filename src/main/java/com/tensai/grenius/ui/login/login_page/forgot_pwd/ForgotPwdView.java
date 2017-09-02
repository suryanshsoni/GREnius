package com.tensai.grenius.ui.login.login_page.forgot_pwd;

import com.tensai.grenius.ui.base.MvpView;

/**
 * Created by ishitabhandari on 02/09/17.
 */

public interface ForgotPwdView extends MvpView {
    void enterPasskey(boolean enter);
    void changePassword();
    void redirectLogin();
}
