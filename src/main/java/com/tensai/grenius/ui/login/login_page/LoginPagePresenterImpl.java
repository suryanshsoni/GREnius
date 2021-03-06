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
import com.tensai.grenius.R;
import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.data.network.response.LoginResponse;
import com.tensai.grenius.data.network.response.ProfileDetailResponse;
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

        /*String userId = getDataManager().getCurrentUserid();
        if(userId != null){
            getMvpView().onError(userId);
        }*/
    }

    @Override
    public void onFbClicked() {
        getMvpView().initiateFbLogin();
    }

    @Override
    public void generatePasslink(String name, String password, String city, final String emailId) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SIGN_UP_METHOD, "register");
        bundle.putString("user_email",emailId);

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP, bundle);

        getMvpView().showLoading("Generating Passkey...");
        getDataManager().generatePasslink(name,password,city,emailId)
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
                        getMvpView().hideLoading();
                        getMvpView().onError(R.string.server_error);

                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        getMvpView().hideLoading();
                        if (loginResponse.getStatus().equals("true")){
                            getMvpView().enterPasslink(loginResponse.getStatus().equals("true"));
                            getMvpView().showToast("Passkey sent to your emailID. ");
                        }else {
                            Log.i("QWE:","in else!");
                            getMvpView().showToast(loginResponse.getSessionId());
                        }
                    }
                });
    }

    @Override
    public void signIn(final String emailId, String password) {
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
                        getMvpView().onError(R.string.server_error);
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
                            getBookmarkWords(emailId, loginResponse.getSessionId());
                            getProfileDetails(emailId);
                            checkAlreadyLoggedIn();
                        }else {
                            //user not found
                            getMvpView().showToast(loginResponse.getSessionId());
                            getMvpView().hideLoading();

                        }
                    }
                });
    }

    @Override
    public void getBookmarkWords(String emailId, String sessionId) {
        getDataManager().downloadBookmarkWords(emailId, sessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Word>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("getBM", e.getMessage());
                        getMvpView().hideLoading();
                        getMvpView().onError(R.string.server_error);
                    }

                    @Override
                    public void onNext(List<Word> words) {
                        Log.i("getBM", words.toString());
                        getDataManager().saveBookmarks(words);
                    }
                });
    }

    @Override
    public void verifyPasslink(final String emailId, String passkey, final String name, final String city) {
        getMvpView().showLoading("Verifying ...");
        getDataManager().verifyPasslink(emailId, passkey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResponse>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().hideLoading();
                        getMvpView().onError(R.string.server_error);
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {

                        if (loginResponse.getStatus().equals("true")) {
                            //verified
                            getDataManager().setSessionId(loginResponse.getSessionId());
                            firebaseAnalytics.setUserId(loginResponse.getId());
                            getDataManager().setCurrentUserName(name);
                            getDataManager().setCurrentUserId(emailId);
                            getDataManager().setCity(city);
                            checkAlreadyLoggedIn();
                        }else {
                            getMvpView().hideLoading();
                            getMvpView().showToast("Passkey does not match!");
                        }

                    }
                });

    }

    public void getProfileDetails(String emailId){
        Log.i("Profile Response", "in get profile");
        getDataManager().getProfile(emailId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProfileDetailResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ProfileDetailResponse profileDetailResponse) {
                        int progress = 1;
                        if (profileDetailResponse.getGender()!=null){
                            progress++;
                        }
                        if (profileDetailResponse.getDob()!=null){
                            progress++;
                        }
                        if (profileDetailResponse.getMobile()!=null){
                            progress++;
                        }
                        if (profileDetailResponse.getMotive()!=null){
                            progress++;
                        }
                        if (profileDetailResponse.getWork()!=null){
                            progress++;
                        }
                        if (profileDetailResponse.getCity()!=null){
                            progress++;
                        }
                            getDataManager().updateProgress(progress);
                            getDataManager().updateProfile(profileDetailResponse.getGender(),profileDetailResponse.getDob(),profileDetailResponse.getMobile(),profileDetailResponse.getCity(),profileDetailResponse.getMotive(),profileDetailResponse.getWork());
                    }
                });
    }

    private void checkAlreadyLoggedIn() {
        String sessionId = getDataManager().getSessionId();
        Log.d("LoginPresenter","Session id:"+sessionId);
        if(sessionId != null ){
            Boolean b=getDataManager().areWordsPresent();
            Log.i("LOG",""+b);
            if(!getDataManager().areWordsPresent()){
                getMvpView().showLoading("Downloading data and Initializing App... Hang on!!!");
                getDataManager().downloadWords(0,getDataManager().getCurrentUserid(),getDataManager().getSessionId())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<Word>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("LoginPresenter", e.getMessage());
                                getMvpView().hideLoading();
                                getMvpView().onError(R.string.server_error);
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
                getDataManager().getCategory(getDataManager().getCurrentUserid(),getDataManager().getSessionId())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<Category>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                getMvpView().hideLoading();
                                getMvpView().onError(R.string.server_error);
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
            word.save();
        }
    }
    void saveCategories(){
        int count = 1;
        for(Category category :categories){
            category.save();
        }
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        this.accessToken = loginResult.getAccessToken().getToken();
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),this);
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,location");
        request.setParameters(parameters);
        request.executeAsync();
        getMvpView().showLoading("Fetching User Information");
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onError(FacebookException error) {
        getMvpView().hideLoading();
        getMvpView().onError("Cannot login, please try again!");
    }

    @Override
    public void onCompleted(JSONObject object, GraphResponse response) {
        // Application code
        try {
            getMvpView().showLoading("Logging In.");
            Log.d("Log","lmn:" + object.toString());
            String name = object.getString("name");
            final String email = object.getString("email");
            String id = object.getString("id");

            JSONObject jsonobject_location = object.getJSONObject("location");
            String city = jsonobject_location.getString("name");


            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.SIGN_UP_METHOD, "facebook");
            bundle.putString("user_email",email);

            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP, bundle);

            getDataManager().setCurrentUserName(name);
            getDataManager().setCurrentUserId(email);
            getDataManager().setUserFBToken(id);
            getDataManager().setCity(city);
            getDataManager().login(id,name, accessToken, email,city)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<LoginResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            getMvpView().hideLoading();
                            getMvpView().onError(R.string.server_error);
                        }

                        @Override
                        public void onNext(LoginResponse loginResponse) {
                            getMvpView().hideLoading();
                            Log.d("LoginPresenter", loginResponse.getStatus());
                            getDataManager().setSessionId(loginResponse.getSessionId());
                            getBookmarkWords(email,loginResponse.getSessionId());
                            getProfileDetails(email);
                            checkAlreadyLoggedIn();
                        }
                    });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
