package com.tensai.grenius.ui.home;

import com.tensai.grenius.ui.base.MvpView;

/**
 * Created by rishabhpanwar on 24/06/17.
 */

public interface HomeView extends MvpView {

    void showUserDetails(String userId,String userName, String fbToken);

    void redirectLogOut();

    void onUploadBookmarkError();

    void showProgress(int progress);

}
