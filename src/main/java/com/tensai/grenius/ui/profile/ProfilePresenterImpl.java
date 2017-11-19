package com.tensai.grenius.ui.profile;

import android.util.Log;

import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.data.network.response.ProfileResponse;
import com.tensai.grenius.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ishitabhandari on 05/11/17.
 */

public class ProfilePresenterImpl<V extends ProfileView> extends BasePresenter<V> implements ProfilePresenter<V> {

    @Inject
    public ProfilePresenterImpl(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getProfile() {
        List<String> profile = getDataManager().getProfile();
        getMvpView().showProfile(profile.get(0),profile.get(1),profile.get(2),profile.get(3),profile.get(4));
    }

    @Override
    public void updateProfile(String emailId, String gender, String mobile, String city, String motive) {
        getMvpView().showLoading("Updating your profile");
        getDataManager().updateProfile(emailId, gender, mobile, city, motive)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProfileResponse>() {
                    @Override
                    public void onCompleted() {
                        getMvpView().hideLoading();
                        getMvpView().showToast("Profile successfully updated");
                        Log.i("MNB:","in completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().hideLoading();
                        getMvpView().showToast("ERROR");
                        Log.i("MNB:","in Error");
                    }

                    @Override
                    public void onNext(ProfileResponse profileResponse) {

                    }
                });
    }
}
