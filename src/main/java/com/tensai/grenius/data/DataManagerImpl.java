package com.tensai.grenius.data;

import android.content.Context;
import android.util.Log;

import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.tensai.grenius.data.db.DbHelper;
import com.tensai.grenius.data.network.ApiHelper;
import com.tensai.grenius.data.network.response.LoginResponse;
import com.tensai.grenius.data.prefs.PreferenceHelper;
import com.tensai.grenius.di.ApplicationContext;
import com.tensai.grenius.model.Category;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.model.Articles;
import com.tensai.grenius.model.WordOfDay;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Pavilion on 21-06-2017.
 */

public class DataManagerImpl implements DataManager {
    private final Context context;
    private final DbHelper dbHelper;
    private final PreferenceHelper preferenceHelper;
    private final ApiHelper apiHelper;

    @Inject //1
    public DataManagerImpl(@ApplicationContext Context context,
                           DbHelper dbHelper,
                           PreferenceHelper preferenceHelper,
                           ApiHelper apiHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
        this.preferenceHelper = preferenceHelper;
        this.apiHelper = apiHelper;
    }

    @Override
    public void setCurrentUserId(String userId) {
        preferenceHelper.setCurrentUserId(userId);
    }

    @Override
    public String getCurrentUserid() {
        return preferenceHelper.getCurrentUserid();
    }

    @Override
    public void setCurrentUserName(String userName) {
        preferenceHelper.setCurrentUserName(userName);
    }

    @Override
    public String getCurrentUserName() {
        return preferenceHelper.getCurrentUserName();
    }

    @Override
    public void setSessionId(String sessionId) {
        preferenceHelper.setSessionId(sessionId);
    }

    @Override
    public String getSessionId() {
        return preferenceHelper.getSessionId();
    }

    @Override
    public void setResourceId(int resourceId) {
        preferenceHelper.setResourceId(resourceId);
    }

    @Override
    public int getResourceId() {
        return preferenceHelper.getResourceId();
    }

    @Override
    public void setWordCount(int count) {
        preferenceHelper.setWordCount(count);
    }

    @Override
    public int getWordCount() {
        return preferenceHelper.getWordCount();
    }

    @Override
    public void setCategoryCount(int count) {
        preferenceHelper.setCategoryCount(count);
    }

    @Override
    public int getCategoryCount() {
        return preferenceHelper.getCategoryCount();
    }

    @Override
    public void setMarkedWords(Word obj) {
        Log.i("Mark: ", "In mark method DataM");
        preferenceHelper.setMarkedWords(obj);
    }

    @Override
    public List<Word> getMarkedWords() { return preferenceHelper.getMarkedWords(); }

    @Override
    public void removeMarkedWords(Word obj) { preferenceHelper.removeMarkedWords(obj); }

    @Override
    public boolean getTutorial() { return preferenceHelper.getTutorial(); }

    @Override
    public void setTutorial(boolean tutorialshown) { preferenceHelper.setTutorial(tutorialshown);}

    @Override
    public void saveWordOfDay(WordOfDay word) {
        preferenceHelper.saveWordOfDay(word);
    }

    @Override
    public WordOfDay getSavedWordOfDay() {
        return preferenceHelper.getSavedWordOfDay();
    }

    @Override
    public Observable<LoginResponse> login(String userId, String username, String accessToken, String emailId) {
        return apiHelper.login(userId,username,accessToken,emailId);
    }

    @Override
    public Observable<LoginResponse> register(String name, String password, String mobile, String country, String city, String emailId) {
        return apiHelper.register(name, password, mobile, country, city, emailId);
    }

    @Override
    public Observable<List<Word>> downloadWords(int index) {
        return apiHelper.downloadWords(index);
    }

    @Override
    public Boolean areWordsPresent() {
        return dbHelper.areWordsPresent();
    }

    @Override
    public Boolean areCategoriesPresent() {
        return dbHelper.areCategoriesPresent();
    }

    public Observable <List<Articles>> getArticles(){
        return apiHelper.getArticles();
    }

    @Override
    public Observable<List<Articles>> getDashboardArticles() {
        return apiHelper.getDashboardArticles();
    }

    @Override
    public Observable<WordOfDay> getWordOfDay() {
        return apiHelper.getWordOfDay();
    }

    @Override
    public Observable<List<Category>> getCategory() {
        return  apiHelper.getCategory();
    }

    public void getAllWords(QueryTransaction.QueryResultListCallback<Word> queryCallback, Transaction.Error errorCallback){
        dbHelper.getAllWords(queryCallback,errorCallback);
    }

    @Override
    public void getHighFreqWords(QueryTransaction.QueryResultListCallback<Word> queryCallback, Transaction.Error errorCallback) {
        dbHelper.getHighFreqWords(queryCallback,errorCallback);
    }

    @Override
    public void getFiftyWords(int pos, QueryTransaction.QueryResultListCallback<Word> queryCallback, Transaction.Error errorCallback) {
        dbHelper.getFiftyWords(pos,queryCallback,errorCallback);
    }

    @Override
    public void getAllCategories(QueryTransaction.QueryResultListCallback<Category> queryCallback, Transaction.Error errorCallback) {
        dbHelper.getAllCategories(queryCallback,errorCallback);
    }

    @Override
    public void getHfWords(QueryTransaction.QueryResultListCallback<Word> queryCallback, Transaction.Error errorCallback) {
        dbHelper.getHfWords(queryCallback,errorCallback);
    }

}
