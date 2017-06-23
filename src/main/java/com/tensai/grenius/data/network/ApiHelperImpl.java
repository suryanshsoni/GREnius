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

}
