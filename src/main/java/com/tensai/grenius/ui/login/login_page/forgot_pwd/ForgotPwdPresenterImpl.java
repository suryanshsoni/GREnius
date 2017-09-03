package com.tensai.grenius.ui.login.login_page.forgot_pwd;

import android.util.Log;

import com.tensai.grenius.R;
import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.data.network.response.BookmarkWordsResponse;
import com.tensai.grenius.ui.base.BasePresenter;

import javax.inject.Inject;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ishitabhandari on 02/09/17.
 */

public class ForgotPwdPresenterImpl<V extends ForgotPwdView> extends BasePresenter<V> implements ForgotPwdPresenter<V> {

    @Inject
    public ForgotPwdPresenterImpl(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void generatePasskey(String emailId) {
        getDataManager().generatePasskey(emailId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BookmarkWordsResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Forgot:", e.getMessage());
                        getMvpView().onError(R.string.server_error);
                    }

                    @Override
                    public void onNext(BookmarkWordsResponse bookmarkWordsResponse) {
                        if (bookmarkWordsResponse.getStatus().equals("true")) {
                            getMvpView().enterPasskey(true);
                        }
                        getMvpView().showToast(bookmarkWordsResponse.getMessage());
                    }
                });
    }

    @Override
    public void verifyPasskey(String emailId, String passkey) {
        getMvpView().showLoading("Verifying ...");
        getDataManager().verifyPasskey(emailId, passkey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BookmarkWordsResponse>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Forgot:", e.getMessage());
                        getMvpView().hideLoading();
                        getMvpView().onError(R.string.server_error);
                    }

                    @Override
                    public void onNext(BookmarkWordsResponse bookmarkWordsResponse) {
                        getMvpView().hideLoading();
                        if (bookmarkWordsResponse.getStatus().equals("true")) {
                            //verified
                            getMvpView().changePassword();
                        }else {
                            getMvpView().showToast("" + bookmarkWordsResponse.getMessage());
                        }

                    }
                });
    }

    @Override
    public void updatePassword(String emailId, String password, String passkey) {
        getMvpView().showLoading("Updating Password ...");
        getDataManager().updatePassword(emailId, password, passkey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BookmarkWordsResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Forgot:", e.getMessage());
                        getMvpView().hideLoading();
                        getMvpView().onError(R.string.server_error);
                    }

                    @Override
                    public void onNext(BookmarkWordsResponse bookmarkWordsResponse) {
                        getMvpView().hideLoading();
                        if (bookmarkWordsResponse.getStatus().equals("true")) {
                            //verified
                            getMvpView().showToast("" + bookmarkWordsResponse.getMessage());
                            getMvpView().redirectLogin();
                        }else {
                            getMvpView().showToast("" + bookmarkWordsResponse.getMessage());
                        }
                    }
                });
    }
}
