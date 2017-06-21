package com.tensai.grenius.data.network;

import javax.inject.Inject;

/**
 * Created by Pavilion on 21-06-2017.
 */

public class ApiHelperImpl implements ApiHelper {
    private final ApiService apiService;
    @Inject
    public ApiHelperImpl(ApiService apiService) {
        this.apiService = apiService;
    }


}
