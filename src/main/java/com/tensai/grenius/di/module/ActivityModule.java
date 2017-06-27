package com.tensai.grenius.di.module;

import android.app.Activity;
import android.content.Context;

import com.tensai.grenius.di.ActivityContext;
import com.tensai.grenius.ui.home.HomePresenter;
import com.tensai.grenius.ui.home.HomePresenterImpl;
import com.tensai.grenius.ui.home.HomeView;
import com.tensai.grenius.ui.home.articles_fragment.ArticlesPresenter;
import com.tensai.grenius.ui.home.articles_fragment.ArticlesPresenterImpl;
import com.tensai.grenius.ui.home.articles_fragment.ArticlesView;
import com.tensai.grenius.ui.home.words_all_fragment.WordsAllPresenter;
import com.tensai.grenius.ui.home.words_all_fragment.WordsAllPresenterImpl;
import com.tensai.grenius.ui.home.words_all_fragment.WordsAllView;
import com.tensai.grenius.ui.home.words_all_fragment.words_fragment.WordsPresenter;
import com.tensai.grenius.ui.home.words_all_fragment.words_fragment.WordsPresenterImpl;
import com.tensai.grenius.ui.home.words_all_fragment.words_fragment.WordsView;
import com.tensai.grenius.ui.login.LoginPresenter;
import com.tensai.grenius.ui.login.LoginPresenterImpl;
import com.tensai.grenius.ui.login.LoginView;

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

    @Provides
    LoginPresenter<LoginView> provideLoginPresenter(LoginPresenterImpl<LoginView> presenter){
        return  presenter;
    }

    @Provides
    HomePresenter<HomeView> provideHomePresenter(HomePresenterImpl<HomeView> presenter){
        return presenter;
    }

    @Provides
    WordsAllPresenter<WordsAllView> provideWordsAllPresenter(WordsAllPresenterImpl<WordsAllView> presenter){
        return  presenter;
    }

    @Provides
    WordsPresenter<WordsView> provideWordsPresenter (WordsPresenterImpl<WordsView> presenter) {
        return presenter;
    }

    @Provides
    ArticlesPresenter<ArticlesView> provideArticlesPresenter (ArticlesPresenterImpl<ArticlesView> presenter){
        return presenter;
    }
}
