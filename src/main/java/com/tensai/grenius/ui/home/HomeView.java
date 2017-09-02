package com.tensai.grenius.ui.home;

import com.tensai.grenius.ui.base.MvpView;

/**
 * Created by rishabhpanwar on 24/06/17.
 */

public interface HomeView extends MvpView {
    void showUserDetails(String userId,String userName);
    void redirectLogOut();
    void onUploadBookmarkError();
}
