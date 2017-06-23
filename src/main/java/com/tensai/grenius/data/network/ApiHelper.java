package com.tensai.grenius.data.network;

import com.tensai.grenius.data.network.response.LoginResponse;

import rx.Observable;

/**
 * Created by Pavilion on 21-06-2017.
 */

public interface ApiHelper {
    Observable<LoginResponse> login(String userId, String username, String accessToken, String emailId);

}
