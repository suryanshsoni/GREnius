package com.tensai.grenius.ui.login.login_page;


import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.MvpPresenter;

import java.util.List;


/**
 * Created by ishitabhandari on 27/08/17.
 */

public interface LoginPagePresenter <V extends LoginPageView> extends MvpPresenter<V> {
    void onFbClicked();
    void generatePasslink(String name, String password, String city, String emailId);
    void signIn (String emailId, String password);
    void getBookmarkWords(String emailId, String sessionId);
    void verifyPasslink(String emailId, String passkey, String name);
}
