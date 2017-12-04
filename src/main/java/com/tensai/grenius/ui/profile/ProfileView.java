package com.tensai.grenius.ui.profile;

import com.tensai.grenius.ui.base.MvpView;

/**
 * Created by ishitabhandari on 05/11/17.
 */

public interface ProfileView extends MvpView {

    void showProfile(int progress, String email, String gender, String age, String mobile, String city, String motive, String insti);

    void showProgress(int progress);
}
