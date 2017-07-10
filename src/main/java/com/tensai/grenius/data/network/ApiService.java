package com.tensai.grenius.data.network;

import com.tensai.grenius.data.network.response.LoginResponse;
import com.tensai.grenius.model.Word;

import java.util.List;
import com.tensai.grenius.model.Articles;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Pavilion on 21-06-2017.
 */

public interface ApiService {
    @POST("/register")
    @FormUrlEncoded
    Observable<LoginResponse> login(@Field("fbId") String userLoginId, @Field("username") String username,
                                    @Field("accessToken") String accessToken, @Field("emailId") String emailId);


    @POST("/register")
    @FormUrlEncoded
    Observable<LoginResponse> register(@Field("name") String name, @Field("password") String password,
                                    @Field("mobile") String mobile,@Field("country") String country,
                                    @Field("city") String city, @Field("emailId") String emailId);
    @POST("/words")
    @FormUrlEncoded
    Observable<List<Word>> downloadWords(@Field("index") int index);


    @POST("/articles")
    Observable <List<Articles>> getArticles();

    @POST("/dashboardArticles")
    Observable <List<Articles>> getDashboardArticles();
}
