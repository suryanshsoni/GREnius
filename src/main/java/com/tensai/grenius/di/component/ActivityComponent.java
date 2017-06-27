package com.tensai.grenius.di.component;

import com.tensai.grenius.MainActivity;
import com.tensai.grenius.di.PerActivity;
import com.tensai.grenius.di.module.ActivityModule;
import com.tensai.grenius.ui.home.HomeActivity;
import com.tensai.grenius.ui.home.articles_fragment.ArticlesFragment;
import com.tensai.grenius.ui.home.words_all_fragment.WordsAllFragment;
import com.tensai.grenius.ui.home.words_all_fragment.words_fragment.WordsFragment;
import com.tensai.grenius.ui.login.LoginActivity;

import dagger.Component;
/**
 * Created by Pavilion on 20-06-2017.
 */
@PerActivity
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
    void inject(LoginActivity loginActivity);
    void inject(WordsAllFragment fragment);
    void inject(WordsFragment wordsFragment);
    void inject(ArticlesFragment articlesFragment);
}
