package com.tensai.grenius.data.network;

import com.tensai.grenius.data.network.response.BookmarkWordsResponse;
import com.tensai.grenius.data.network.response.LoginResponse;
import com.tensai.grenius.model.Category;
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
    public Observable<LoginResponse> login(String userId, String username, String accessToken, String emailId) {
        return apiService.login(emailId,username,accessToken,emailId);
    }

    @Override
    public Observable<LoginResponse> register(String name, String password, String mobile, String city, String emailId) {
        return apiService.register(name, password, mobile, city, emailId);
    }

    @Override
    public Observable<List<Word>> downloadWords(int index) {
        return apiService.downloadWords(index);
    }

    @Override
    public Observable<List<Articles>> getArticles() {
        return apiService.getArticles();
    }

    @Override
    public Observable<List<Articles>> getDashboardArticles() {
        return apiService.getDashboardArticles();
    }

    @Override
    public Observable<List<Category>> getCategory() {
        return apiService.getCategory();
    }

    @Override
    public Observable<WordOfDay> getWordOfDay() {
        return apiService.getWordOfDay();
    }

    @Override
    public Observable<BookmarkWordsResponse> sendBookmarkWords(ArrayList<Word> words, String userID, String sessionId) {
        return apiService.sendBookmarkWords(words, userID, sessionId);
    }

    @Override
    public Observable<List<Word>> getBookmarkWords() {
        return apiService.downloadBookmarkWords();
    }
}
