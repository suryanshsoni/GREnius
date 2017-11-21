package com.tensai.grenius.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.tensai.grenius.data.DataManager;

import javax.inject.Inject;

/**
 * Created by Pavilion on 11-09-2017.
 */

public class NotificationBootService extends BaseService {

    @Inject
    DataManager dataManager;

    public NotificationBootService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getServiceComponent().inject(this);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        super.onHandleIntent(intent);

    }
}
