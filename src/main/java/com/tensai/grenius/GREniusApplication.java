package com.tensai.grenius;

import android.app.Application;
import android.speech.tts.TextToSpeech;

import com.facebook.FacebookSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.di.component.ApplicationComponent;
import com.tensai.grenius.di.component.DaggerApplicationComponent;
import com.tensai.grenius.di.module.ApplicationModule;


import javax.inject.Inject;

/**
 * Created by Pavilion on 21-06-2017.
 */

public class GREniusApplication extends Application{
    @Inject
    DataManager dataManager;
    @Inject
    TextToSpeech tts;
    @Inject
    FirebaseAnalytics firebaseAnalytics;
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        applicationComponent.inject(this);

        FacebookSdk.sdkInitialize(this);
        FlowManager.init(new FlowConfig.Builder(this).build());

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public DataManager getDataManager(){
        return dataManager;
    }


}
