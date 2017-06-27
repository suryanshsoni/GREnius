package com.tensai.grenius.data.network;

import com.tensai.grenius.data.network.response.LoginResponse;
import com.tensai.grenius.model.Word;

import java.util.List;
import com.tensai.grenius.model.Articles;

import java.util.List;

import rx.Observable;

/**
 * Created by Pavilion on 21-06-2017.
 */

public interface ApiHelper {
    Observable<LoginResponse> login(String userId, String username, String accessToken, String emailId);

    Observable<LoginResponse> register(String name,String password,String mobile,String country,String city,String emailId);
    Observable<List<Word>> downloadWords(int index);

    Observable <List<Articles>> getArticles();
}
