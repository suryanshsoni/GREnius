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
        int progress = getDataManager().getProgress();
        getMvpView().showProfile(progress, profile.get(0),profile.get(1),profile.get(2),profile.get(3),profile.get(4),profile.get(5),profile.get(6));
    }

    @Override
    public void updateProfile(final int progress, String emailId, final String gender, final String dob, final String mobile, final String city, final String motive, final String work) {
        getMvpView().showLoading("Updating your profile");
        getDataManager().updateProfile(emailId, gender, dob, mobile, city, motive, work)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProfileResponse>() {
                    @Override
                    public void onCompleted() {
                        getMvpView().hideLoading();
                        getMvpView().showToast("Profile successfully updated");
                        getMvpView().showProgress(progress);
                        getDataManager().updateProgress(progress);
                        getDataManager().updateProfile(gender,dob,mobile,city,motive, work);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().hideLoading();
                        getMvpView().showToast("ERROR :");
                    }

                    @Override
                    public void onNext(ProfileResponse profileResponse) {

                    }
                });
    }
}
