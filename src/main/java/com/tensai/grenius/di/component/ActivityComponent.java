package com.tensai.grenius.di.component;

import com.tensai.grenius.MainActivity;
import com.tensai.grenius.di.PerActivity;
import com.tensai.grenius.di.module.ActivityModule;
import com.tensai.grenius.ui.home.HomeActivity;
import com.tensai.grenius.ui.home.articles_fragment.ArticlesFragment;
import com.tensai.grenius.ui.home.dashboard_fragment.DashboardFragment;
import com.tensai.grenius.ui.home.marked_fragment.MarkedWordsActivity;
import com.tensai.grenius.ui.home.quiz_fragment.QuizFragment;
import com.tensai.grenius.ui.home.words.words_all_fragment.WordsAllFragment;
import com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.WordsFragment;
import com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.flash_card.CardFragment;
import com.tensai.grenius.ui.home.words.words_high_frequency_fragment.WordsHighFreqFragment;
import com.tensai.grenius.ui.home.words_synonym_fragement.WordsSynonymFragment;
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
    void inject(QuizFragment quizFragment);
    void inject(WordsHighFreqFragment wordsHighFreqFragment);
    void inject(CardFragment cardFragment);
    void inject(DashboardFragment dashboardFragment);
    void inject(WordsSynonymFragment wordsSynonymFragment);
    void inject(HomeActivity homeActivity);
    void inject(MarkedWordsActivity markedWordsActivity);
}
