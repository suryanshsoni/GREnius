package com.tensai.grenius.data.network;

import android.util.Log;

import com.tensai.grenius.data.network.response.BookmarkWordsResponse;
import com.tensai.grenius.data.network.response.LoginResponse;
import com.tensai.grenius.data.network.response.ProfileResponse;
import com.tensai.grenius.model.Category;
import com.tensai.grenius.model.Institute;
import com.tensai.grenius.model.Titleinstitute;
import com.tensai.grenius.model.Word;

import java.util.ArrayList;
import java.util.List;
import com.tensai.grenius.model.Articles;
import com.tensai.grenius.model.WordOfDay;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Pavilion on 21-06-2017.
 */

public class ApiHelperImpl implements ApiHelper {

    private final ApiService apiService;
    @Inject
    public ApiHelperImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<LoginResponse> login(String userId, String username, String accessToken, String emailId, String city) {
        return apiService.login(userId,username,accessToken,emailId,city);
    }

    @Override
    public Observable<LoginResponse> register(String name, String password, String mobile, String city, String emailId) {
        return apiService.register(name, password, mobile, city, emailId);
    }

    @Override
    public Observable<List<Word>> downloadWords(int index,String emailId,String sessionId) {
        return apiService.downloadWords(index,emailId,sessionId);
    }

    @Override
    public Observable <List<WordOfDay>> wordOfDays(String emailId,String sessionId) {
        return apiService.wordOfDays(emailId,sessionId);
    }

    @Override
    public Observable<BookmarkWordsResponse> generatePasskey(String emailId) {  return apiService.generatePasskey(emailId); }

    @Override
    public Observable<BookmarkWordsResponse> verifyPasskey(String emailId, String passkey) {   return apiService.verifyPasskey(emailId, passkey);   }

    @Override
    public Observable<BookmarkWordsResponse> updatePassword(String emailId, String password, String passkey) {  return apiService.updatePassword(emailId, password, passkey);  }

    @Override
    public Observable<List<Institute>> getInstitutes(String emailId,String sessionId) {
        return apiService.getInstitutes(emailId,sessionId);
    }

    @Override
    public Observable<List<Titleinstitute>> getTitleInstitute(String emailId, String sessionId) {
        return apiService.getTitleInstitute(emailId,sessionId);
    }

    @Override
    public Observable<ProfileResponse> updateProfile(String emailId, String gender, String mobile, String city, String motive) {
        return apiService.updateProfile(emailId, gender, mobile, city, motive);
    }

    @Override
    public Observable<LoginResponse> signIn(String emailId, String password) {
        return apiService.signIn(emailId, password);
    }

    @Override
    public Observable<List<Articles>> getArticles(String emailId,String sessionId) {
        return apiService.getArticles(emailId,sessionId);
    }

    @Override
    public Observable<List<Articles>> getDashboardArticles(String emailId,String sessionId) {
        return apiService.getDashboardArticles(emailId,sessionId);
    }

    @Override
    public Observable<List<Category>> getCategory(String emailId,String sessionId) {
        return apiService.getCategory(emailId,sessionId);
    }

    @Override
    public Observable<WordOfDay> getWordOfDay(String emailId,String sessionId) {
        return apiService.getWordOfDay(emailId,sessionId);
    }

    @Override
    public Observable<BookmarkWordsResponse> sendBookmarkWords(ArrayList<Word> words, String userID, String sessionId) {
        BookmarkBody bookmarkBody=new BookmarkBody();
        bookmarkBody.setUserId(userID);
        bookmarkBody.setSessionId(sessionId);
        bookmarkBody.setWords(words);

        Log.i("ASD:","words:"+words+"userId:"+userID+"sessionId:"+sessionId);
        return apiService.sendBookmarkWords(bookmarkBody);
    }

    @Override
    public Observable<List<Word>> downloadBookmarkWords(String emailId, String sessionId) {
        return apiService.downloadBookmarkWords(emailId, sessionId);
    }
}
