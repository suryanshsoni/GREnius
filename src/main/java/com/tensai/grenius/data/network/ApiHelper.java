package com.tensai.grenius.data.network;

import com.tensai.grenius.data.network.response.BookmarkWordsResponse;
import com.tensai.grenius.data.network.response.LoginResponse;
import com.tensai.grenius.model.Category;
import com.tensai.grenius.model.Institute;
import com.tensai.grenius.model.Titleinstitute;
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

    Observable <List<Word>> downloadWords(int index,String emailId,String sessionId);

    Observable <LoginResponse> signIn(String emailId, String password);

    Observable <List<Articles>> getArticles(String emailId,String sessionId);

    Observable <List<Articles>> getDashboardArticles(String emailId,String sessionId);

    Observable <List<Category>> getCategory(String emailId,String sessionId);

    Observable <WordOfDay> getWordOfDay(String emailId,String sessionId);

    Observable <BookmarkWordsResponse> sendBookmarkWords(ArrayList<Word> words, String userID, String sessionId);

    Observable <List<Word>> downloadBookmarkWords(String emailId, String sessionId);

    Observable <List<WordOfDay>> wordOfDays(String emailId,String sessionId);

    Observable<BookmarkWordsResponse> generatePasskey (String emailId);

    Observable<BookmarkWordsResponse> verifyPasskey (String emailId, String passkey);

    Observable<BookmarkWordsResponse> updatePassword (String emailId, String password, String passkey);

    Observable <List<Institute>> getInstitutes(String emailId,String sessionId);

    Observable <List<Titleinstitute>> getTitleInstitute(String emailId, String sessionId);

}
