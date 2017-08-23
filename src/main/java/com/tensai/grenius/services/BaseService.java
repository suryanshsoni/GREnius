package com.tensai.grenius.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.tensai.grenius.GREniusApplication;
import com.tensai.grenius.di.component.DaggerServiceComponent;
import com.tensai.grenius.di.component.ServiceComponent;

/**
 * Created by Pavilion on 23-08-2017.
 */

public class BaseService extends IntentService {
    ServiceComponent serviceComponent;

    public BaseService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        serviceComponent = DaggerServiceComponent.builder()
                .applicationComponent(((GREniusApplication) getApplication()).getApplicationComponent())
                .build();
    }

    public ServiceComponent getServiceComponent() {
        return serviceComponent;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
