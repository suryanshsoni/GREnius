package com.tensai.grenius.data.network;

import com.tensai.grenius.data.network.response.LoginResponse;

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
    public Observable<LoginResponse> register(String name, String password, String mobile, String country, String city, String emailId) {
        return apiService.register(name, password, mobile, country, city, emailId);
    }

}
