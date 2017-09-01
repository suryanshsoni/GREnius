package com.tensai.grenius.ui.login.login_page;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.data.network.response.LoginResponse;
import com.tensai.grenius.model.Category;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.BasePresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ishitabhandari on 27/08/17.
 */

public class LoginPagePresenterImpl<V extends LoginPageView> extends BasePresenter<V> implements LoginPagePresenter<V>, FacebookCallback<LoginResult>,
        GraphRequest.GraphJSONObjectCallback{
    private String TAG = "LoginPagePresenterImpl";
    public String accessToken = "";
    List<Word> words;
    List<Category> categories;
    Boolean areWords=false,areCategories=false;

    @Inject
    FirebaseAnalytics firebaseAnalytics;

    @Inject
    public LoginPagePresenterImpl(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onAttach(V mvpView) {
        Log.d(TAG, "On Attached Called!");
        super.onAttach(mvpView);
        try{
            checkViewAttached();
            // getMvpView().checkGooglePlayServices();
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
    public void onFbClicked() {
        getMvpView().initiateFbLogin();
    }

    @Override
    public void onOTPVerification(String name, String password, String mobile, String city, final String emailId) {
        Log.i("Mobile no",mobile);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SIGN_UP_METHOD, "register");
        bundle.putString("user_email",emailId);

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP, bundle);

        getMvpView().showLoading("Registering...");
        getDataManager().setCurrentUserName(name);
        getDataManager().register(name,password,mobile,city,emailId)
                .subscribeOn(Schedulers.io())
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
                        firebaseAnalytics.setUserId(loginResponse.getId());
                        getDataManager().setCurrentUserId(loginResponse.getId());
                        //firebaseAnalytics.setUserProperty("email",emailId);
                        checkAlreadyLoggedIn();
                    }
                });
    }

    @Override
    public void signIn(String emailId, String password) {
        getMvpView().showLoading("Logging In...");
        getDataManager().signIn(emailId, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("LogInError",""+e.getMessage());
                        getMvpView().hideLoading();
                        getMvpView().showToast("Invalid Credentials");
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        if(loginResponse.getStatus().equals("true")){
                            //user validated
                            getDataManager().setSessionId(loginResponse.getSessionId());
                            firebaseAnalytics.setUserId(loginResponse.getId());
                            getDataManager().setCurrentUserName(loginResponse.getName());
                            getDataManager().setCurrentUserId(loginResponse.getId());
                            getMvpView().hideLoading();
                            checkAlreadyLoggedIn();
                        }else {
                            //user not found
                            getMvpView().showToast("Invalid Credentials");
                        }
                    }
                });
    }

    private void checkAlreadyLoggedIn() {
        String sessionId = getDataManager().getSessionId();
        if(sessionId != null ){
            Boolean b=getDataManager().areWordsPresent();
            Log.i("LOG",""+b);
            if(!getDataManager().areWordsPresent()){
                getMvpView().showLoading("Initializing App...Hang on!!!");
                getDataManager().downloadWords(0)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<Word>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("LoginPresenter", e.getMessage());
                            }

                            @Override
                            public void onNext(List<Word> words) {
                                //do work here
                                setWords(words);
                                if(words!=null){
                                    new AsyncTask<Void, Void, Void>() {
                                        @Override
                                        protected void onPreExecute() {
                                            super.onPreExecute();
                                        }

                                        @Override
                                        protected Void doInBackground(Void... params) {
                                            try {
                                                //Thread.sleep(5000);
                                                saveWords();

                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }
                                            return null;
                                        }

                                        @Override
                                        protected void onPostExecute(Void response) {
                                            areWords=true;
                                            callHome();
                                        }
                                    }.execute();

                                    getDataManager().setWordCount(words.size());
                                }
                            }
                        });
            }
            else
                areWords=true;
            if(!getDataManager().areCategoriesPresent()){
                //getMvpView().showToast("Downloading Words...");
                getDataManager().getCategory()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<Category>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("LoginPresenter", e.getMessage());
                            }

                            @Override
                            public void onNext(List<Category> categories) {
                                //do work here
                                setCategories(categories);
                                if(categories!=null){
                                    new AsyncTask<Void, Void, Void>() {
                                        @Override
                                        protected void onPreExecute() {
                                            super.onPreExecute();
                                            //getMvpView().showToast("Started Downloading");
                                        }

                                        @Override
                                        protected Void doInBackground(Void... params) {
                                            try {
                                                saveCategories();
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }
                                            return null;
                                        }

                                        @Override
                                        protected void onPostExecute(Void response) {
                                            areCategories=true;
                                            callHome();
                                        }
                                    }.execute();
                                }
                            }
                        });
            }
            else
                areCategories=true;
            callHome();
        }
    }

    void callHome(){
        if(areCategories&&areWords){
            getMvpView().hideLoading();
            getMvpView().openHomeActivity();
        }

    }
    void setCategories(List<Category> categories){
        this.categories=categories;

    }
    void setWords(List<Word> words){
        this.words=words;
    }
    void saveWords(){
        int count =1;
        for(Word word :words){

            Log.i("DEMO",""+count++);
            word.save();
        }
    }
    void saveCategories(){
        int count = 1;
        for(Category category :categories){

            Log.i("DEMO",""+count++);
            category.save();
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
    public void onCompleted(JSONObject object, GraphResponse response) {
        // Application code
        try {
            getMvpView().showLoading("Logging In.");
            Log.d("Log","Got JSON object" + object.toString());
            String name = object.getString("name");
            String email = object.getString("email");
            String id = object.getString("id");
            Log.i("id: ",email+"/"+id+"/"+name);

            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.SIGN_UP_METHOD, "facebook");
            bundle.putString("user_email",email);

            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP, bundle);

            getDataManager().setCurrentUserName(name);
            getDataManager().setCurrentUserId(id);
            getDataManager().login(id,name, accessToken, email)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<LoginResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("LoginPagePresenter", e.getMessage());
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
}
