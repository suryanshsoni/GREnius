package com.tensai.grenius.di.component;

import android.app.Application;
import android.content.Context;

import com.tensai.grenius.GREniusApplication;
import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.di.ApplicationContext;
import com.tensai.grenius.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
/**
 * Created by Pavilion on 20-06-2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    //Kick start [iniy]
    void inject(GREniusApplication app);

    //Remaining
    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}

