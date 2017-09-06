package com.tensai.grenius.data.network;

import com.tensai.grenius.data.network.response.BookmarkWordsResponse;
import com.tensai.grenius.data.network.response.LoginResponse;
import com.tensai.grenius.model.Category;
import com.tensai.grenius.model.Word;

import java.util.ArrayList;
import java.util.List;
import com.tensai.grenius.model.Articles;
import com.tensai.grenius.model.WordOfDay;

import rx.Observable;

/**
 * Created by Pavilion on 21-06-2017.
 */

public interface ApiHelper {

    Observable <LoginResponse> login(String userId, String username, String accessToken, String emailId, String city);

    Observable <LoginResponse> register(String name,String password,String mobile,String city,String emailId);

    Observable <List<Word>> downloadWords(int index);

    Observable <LoginResponse> signIn(String emailId, String password);

    Observable <List<Articles>> getArticles();

    Observable <List<Articles>> getDashboardArticles();

    Observable <List<Category>> getCategory();

    Observable <WordOfDay> getWordOfDay();

    Observable <BookmarkWordsResponse> sendBookmarkWords(ArrayList<Word> words, String userID, String sessionId);

    Observable <List<Word>> downloadBookmarkWords(String emailId, String sessionId);

    Observable <List<WordOfDay>> wordOfDays();

    Observable<BookmarkWordsResponse> generatePasskey (String emailId);

    Observable<BookmarkWordsResponse> verifyPasskey (String emailId, String passkey);

    Observable<BookmarkWordsResponse> updatePassword (String emailId, String password, String passkey);

}
