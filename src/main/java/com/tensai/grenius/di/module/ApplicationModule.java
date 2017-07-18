package com.tensai.grenius.di.module;

import android.app.Application;
import android.content.Context;
import android.speech.tts.TextToSpeech;

import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.data.DataManagerImpl;
import com.tensai.grenius.data.db.DbHelper;
import com.tensai.grenius.data.db.DbHelperImpl;
import com.tensai.grenius.data.network.ApiHelper;
import com.tensai.grenius.data.network.ApiHelperImpl;
import com.tensai.grenius.data.network.ApiService;
import com.tensai.grenius.data.prefs.PreferenceHelper;
import com.tensai.grenius.data.prefs.PreferenceHelperImpl;
import com.tensai.grenius.di.ApplicationContext;
import com.tensai.grenius.di.PreferenceInfo;
import com.tensai.grenius.util.AppConstants;

import java.util.Locale;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Pavilion on 20-06-2017.
 */
@Module
public class ApplicationModule {
    private final Application application;
    private TextToSpeech tts;
    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext(){
        return application;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return application;
    }

    @Provides
    @PreferenceInfo
    String providePrefFileName(){
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(DbHelperImpl dbHelper){
        return dbHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(ApiHelperImpl apiHelper){
        return  apiHelper;
    }

    @Provides
    @Singleton
    PreferenceHelper providePrefHelper(PreferenceHelperImpl preferenceHelper){
        return preferenceHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(DataManagerImpl dataManager){
        return dataManager;
    }

    @Provides
    @Singleton
    Retrofit provideCall() {
        Retrofit r = null;
        r = new Retrofit.Builder()
                .baseUrl(AppConstants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return r;
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    TextToSpeech provideTts(){
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.ENGLISH);
                }
            }
        });
        return tts;
    }


}
