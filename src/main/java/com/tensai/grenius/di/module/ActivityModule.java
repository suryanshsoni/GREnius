package com.tensai.grenius.di.module;

import android.app.Activity;
import android.content.Context;

import com.tensai.grenius.di.ActivityContext;

import dagger.Module;
import dagger.Provides;
/**
 * Created by Pavilion on 20-06-2017.
 */
@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext(){
        return activity;
    }

    @Provides
    Activity provideActivity(){
        return activity;
    }



}
