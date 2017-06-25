package com.tensai.grenius.ui.login;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.data.network.response.LoginResponse;
import com.tensai.grenius.ui.base.BasePresenter;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Pavilion on 22-06-2017.
 */

public class LoginPresenterImpl<V extends LoginView> extends BasePresenter<V> implements LoginPresenter<V>,
        FacebookCallback<LoginResult>,GraphRequest.GraphJSONObjectCallback {
    private static String TAG = "Login Presenter";
    public String accessToken = "";

    @Inject
    public LoginPresenterImpl(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onAttach(V mvpView) {
        Log.d(TAG, "On Attached Called!");
        super.onAttach(mvpView);
        try{
            checkViewAttached();
            getMvpView().checkGooglePlayServices();
            checkAlreadyLoggedIn();
            getMvpView().registerFacebookCallbackResult(this);
        }catch (MvpViewNotAttachedException e){
            e.printStackTrace();
        }

        String userId = getDataManager().getCurrentUserid();
        if(userId != null){
            getMvpView().onError(userId);
        }
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        Log.d(TAG,"SUCCESSFULLY LOGGED IN!");
        Log.d(TAG,"Access: " + loginResult.getAccessToken().getToken());
        this.accessToken = loginResult.getAccessToken().getToken();
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),this);
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email");
        request.setParameters(parameters);
        request.executeAsync();
        getMvpView().showLoading("Fetching User Information");
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onError(FacebookException error) {

    }


    @Override
    public void onFbClicked() {
        getMvpView().initiateFbLogin();
    }

    public void onRegisterClicked(String name,String password,String mobile,String country,String city, String emailId)
    {
        getMvpView().showLoading("Registering...");
        getDataManager().setCurrentUserName(name);
        getDataManager().register(name,password,mobile,country,city,emailId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<LoginResponse>()
            {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Log.d("LoginPresenter", e.getMessage());
                }

                @Override
                public void onNext(LoginResponse loginResponse) {
                    getMvpView().hideLoading();
                    Log.d("LoginPresenter", loginResponse.getStatus());
                    getDataManager().setSessionId(loginResponse.getSessionId());
                    checkAlreadyLoggedIn();
                }
            });
    }

    @Override
    public void onNextClick(int position) {
        if(position != 3){
            getMvpView().showNextSlide();
        }
    }

    @Override
    public void onSkipClick() {
        checkAlreadyLoggedIn();
    }

    @Override
    public void onCompleted(JSONObject object, GraphResponse response) {
        // Application code
        try {
            getMvpView().showLoading("Logging In.");
            Log.d("Log","Got JSON object" + object.toString());
            String name = object.getString("name");
            String email = object.getString("email");
            String id = object.getString("id");
            Log.d(TAG,"id: " + id);
            getDataManager().setCurrentUserId(id);
            getDataManager().setCurrentUserName(name);
            getDataManager().login(id,name, accessToken, email)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<LoginResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("LoginPresenter", e.getMessage());
                        }

                        @Override
                        public void onNext(LoginResponse loginResponse) {
                            getMvpView().hideLoading();
                            Log.d("LoginPresenter", loginResponse.getStatus());
                            getDataManager().setSessionId(loginResponse.getSessionId());
                            checkAlreadyLoggedIn();
                        }
                    });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void checkAlreadyLoggedIn() {

        String sessionId = getDataManager().getSessionId();
        if(sessionId != null ){
            getMvpView().openHomeActivity();
        }
    }
}
