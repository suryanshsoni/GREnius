package com.tensai.grenius.di.module;

import android.app.IntentService;
import android.app.Service;

import dagger.Module;

/**
 * Created by Pavilion on 23-08-2017.
 */

@Module
public class ServiceModule {

    private final IntentService mService;

    public ServiceModule(IntentService service) {
        mService = service;
    }
}
