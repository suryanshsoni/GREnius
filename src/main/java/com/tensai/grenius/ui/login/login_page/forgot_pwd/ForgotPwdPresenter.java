package com.tensai.grenius.ui.login.login_page.forgot_pwd;

import com.tensai.grenius.ui.base.MvpPresenter;

/**
 * Created by ishitabhandari on 02/09/17.
 */

public interface ForgotPwdPresenter<V extends ForgotPwdView> extends MvpPresenter<V> {
    void generatePasskey(String emailId);
    void verifyPasskey(String emailId, String passkey);
    void updatePassword(String emailId, String password, String passkey);
}
