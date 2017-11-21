package com.tensai.grenius.ui.profile;

import com.tensai.grenius.ui.base.MvpPresenter;

import java.util.List;

/**
 * Created by ishitabhandari on 05/11/17.
 */

public interface ProfilePresenter <V extends ProfileView> extends MvpPresenter<V> {

    void getProfile();

    void updateProfile(String emailId, String gender, String mobile, String city, String motive);
}
