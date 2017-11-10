package com.tensai.grenius.data;

import android.content.Context;
import android.util.Log;

import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.tensai.grenius.data.db.DbHelper;
import com.tensai.grenius.data.network.ApiHelper;
import com.tensai.grenius.data.network.response.BookmarkWordsResponse;
import com.tensai.grenius.data.network.response.LoginResponse;
import com.tensai.grenius.data.prefs.PreferenceHelper;
import com.tensai.grenius.di.ApplicationContext;
import com.tensai.grenius.model.Category;
import com.tensai.grenius.model.Institute;
import com.tensai.grenius.model.Titleinstitute;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.model.Articles;
import com.tensai.grenius.model.WordOfDay;

import java.util.ArrayList;
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
    public String getUserFBToken() { return preferenceHelper.getUserFBToken(); }

    @Override
    public void setUserFBToken(String fbToken) {    preferenceHelper.setUserFBToken(fbToken);   }

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
    public void deleteUserData() { preferenceHelper.deleteUserData();   }

    @Override
    public void saveBookmarks(List<Word> bookmarkList) {    preferenceHelper.saveBookmarks(bookmarkList);}

    @Override
    public void setMarkedWords(Word obj) {
        preferenceHelper.setMarkedWords(obj);
    }

    @Override
    public List<Word> getMarkedWords() { return preferenceHelper.getMarkedWords(); }

    @Override
    public void removeMarkedWords(Word obj) { preferenceHelper.removeMarkedWords(obj); }

    @Override
    public boolean getTutorial(String place) { return preferenceHelper.getTutorial(place); }

    @Override
    public boolean isAlarmSet() {
        return preferenceHelper.isAlarmSet();
    }

    @Override
    public void unsetAlarm() {
        preferenceHelper.unsetAlarm();
    }

    @Override
    public void setTutorial(String place, boolean tutorialshown) { preferenceHelper.setTutorial(place, tutorialshown);}

    @Override
    public void saveWordOfDay(WordOfDay word) {
        preferenceHelper.saveWordOfDay(word);
    }

    @Override
    public WordOfDay getSavedWordOfDay() {
        return preferenceHelper.getSavedWordOfDay();
    }

    @Override
    public Observable<LoginResponse> login(String userId, String username, String accessToken, String emailId, String city) {
        return apiHelper.login(userId,username,accessToken,emailId,city);
    }

    @Override
    public Observable<LoginResponse> register(String name, String password, String mobile, String city, String emailId) {
        return apiHelper.register(name, password, mobile, city, emailId);
    }

    @Override
    public Observable<List<Word>> downloadWords(int index,String emailId,String sessionId) {
        return apiHelper.downloadWords(index,emailId,sessionId);
    }

    @Override
    public Observable<LoginResponse> signIn(String emailId, String password) {
        return apiHelper.signIn(emailId, password);
    }

    @Override
    public Boolean areWordsPresent() {
        return dbHelper.areWordsPresent();
    }

    @Override
    public Boolean areCategoriesPresent() {
        return dbHelper.areCategoriesPresent();
    }

    @Override
    public void deleteDatabase() {  dbHelper.deleteDatabase();  }

    public Observable <List<Articles>> getArticles(String emailId,String sessionId){
        return apiHelper.getArticles(emailId,sessionId);
    }

    @Override
    public Observable<List<Articles>> getDashboardArticles(String emailId,String sessionId) {
        return apiHelper.getDashboardArticles(emailId,sessionId);
    }

    @Override
    public Observable<WordOfDay> getWordOfDay(String emailId,String sessionId) {
        return apiHelper.getWordOfDay(emailId,sessionId);
    }

    @Override
    public Observable<BookmarkWordsResponse> sendBookmarkWords(ArrayList<Word> words, String userID,String sessionId) {
        return apiHelper.sendBookmarkWords(words,userID,sessionId);
    }

    @Override
    public Observable<List<Word>> downloadBookmarkWords(String emailId, String sessionId) {
        return apiHelper.downloadBookmarkWords(emailId, sessionId);
    }

    @Override
    public Observable<List<WordOfDay>> wordOfDays(String emailId,String sessionId) {
        return apiHelper.wordOfDays(emailId,sessionId);
    }

    @Override
    public Observable<BookmarkWordsResponse> generatePasskey(String emailId) {  return apiHelper.generatePasskey(emailId);  }

    @Override
    public Observable<BookmarkWordsResponse> verifyPasskey(String emailId, String passkey) {    return apiHelper.verifyPasskey(emailId, passkey);    }

    @Override
    public Observable<BookmarkWordsResponse> updatePassword(String emailId, String password, String passkey) {  return apiHelper.updatePassword(emailId, password, passkey);    }

    @Override
    public Observable<List<Institute>> getInstitutes(String emailId,String sessionId) {
        return apiHelper.getInstitutes(emailId,sessionId);
    }

    @Override
    public Observable<List<Titleinstitute>> getTitleInstitute(String emailId, String sessionId) {
        return apiHelper.getTitleInstitute(emailId,sessionId);
    }

    @Override
    public Observable<List<Category>> getCategory(String emailId,String sessionId) {
        return  apiHelper.getCategory(emailId,sessionId);
    }

    public void getAllWords(QueryTransaction.QueryResultListCallback<Word> queryCallback, Transaction.Error errorCallback){
        dbHelper.getAllWords(queryCallback,errorCallback);
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
