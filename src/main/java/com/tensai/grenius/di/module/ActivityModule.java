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
import com.tensai.grenius.ui.home.dashboard_fragment.DashboardPresenter;
import com.tensai.grenius.ui.home.dashboard_fragment.DashboardPresenterImpl;
import com.tensai.grenius.ui.home.dashboard_fragment.DashboardView;
import com.tensai.grenius.ui.home.dashboard_fragment.word_of_day.LastWODPresenter;
import com.tensai.grenius.ui.home.dashboard_fragment.word_of_day.LastWODPresenterImpl;
import com.tensai.grenius.ui.home.dashboard_fragment.word_of_day.LastWODView;
import com.tensai.grenius.ui.home.institutes.InstitutePresenter;
import com.tensai.grenius.ui.home.institutes.InstitutePresenterImpl;
import com.tensai.grenius.ui.home.institutes.InstituteView;
import com.tensai.grenius.ui.home.marked_fragment.MarkedWordsPresenter;
import com.tensai.grenius.ui.home.marked_fragment.MarkedWordsPresenterImpl;
import com.tensai.grenius.ui.home.marked_fragment.MarkedWordsView;
import com.tensai.grenius.ui.home.quiz_fragment.QuizPresenter;
import com.tensai.grenius.ui.home.quiz_fragment.QuizPresenterImpl;
import com.tensai.grenius.ui.home.quiz_fragment.QuizView;
import com.tensai.grenius.ui.home.words.words_all_fragment.WordsAllPresenter;
import com.tensai.grenius.ui.home.words.words_all_fragment.WordsAllPresenterImpl;
import com.tensai.grenius.ui.home.words.words_all_fragment.WordsAllView;
import com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.WordsPresenter;
import com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.WordsPresenterImpl;
import com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.WordsView;
import com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.flash_card.CardPresenter;
import com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.flash_card.CardPresenterImpl;
import com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.flash_card.CardView;
import com.tensai.grenius.ui.home.words.words_high_frequency_fragment.WordsHighFreqPresenter;
import com.tensai.grenius.ui.home.words.words_high_frequency_fragment.WordsHighFreqPresenterImpl;
import com.tensai.grenius.ui.home.words.words_high_frequency_fragment.WordsHighFreqView;
import com.tensai.grenius.ui.home.words_synonym_fragement.WordsSynonymPresenter;
import com.tensai.grenius.ui.home.words_synonym_fragement.WordsSynonymPresenterImpl;
import com.tensai.grenius.ui.home.words_synonym_fragement.WordsSynonymView;
import com.tensai.grenius.ui.login.LoginPresenter;
import com.tensai.grenius.ui.login.LoginPresenterImpl;
import com.tensai.grenius.ui.login.LoginView;
import com.tensai.grenius.ui.login.login_page.LoginPagePresenter;
import com.tensai.grenius.ui.login.login_page.LoginPagePresenterImpl;
import com.tensai.grenius.ui.login.login_page.LoginPageView;
import com.tensai.grenius.ui.login.login_page.forgot_pwd.ForgotPwdPresenter;
import com.tensai.grenius.ui.login.login_page.forgot_pwd.ForgotPwdPresenterImpl;
import com.tensai.grenius.ui.login.login_page.forgot_pwd.ForgotPwdView;
import com.tensai.grenius.ui.profile.ProfilePresenter;
import com.tensai.grenius.ui.profile.ProfilePresenterImpl;
import com.tensai.grenius.ui.profile.ProfileView;

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

    @Provides
    QuizPresenter<QuizView> provideQuizPresenter(QuizPresenterImpl<QuizView> presenter){
        return  presenter;
    }

    @Provides
    CardPresenter<CardView> provideCardPresenter(CardPresenterImpl<CardView> presenter){
        return presenter;
    }

    @Provides
    WordsHighFreqPresenter<WordsHighFreqView> provideWordsHighFreqPresenter(WordsHighFreqPresenterImpl<WordsHighFreqView> presenter){
        return presenter;
    }

    @Provides
    DashboardPresenter<DashboardView> provideDashboardPresenter (DashboardPresenterImpl<DashboardView> presenter){
        return presenter;
    }

    @Provides
    WordsSynonymPresenter<WordsSynonymView> provideWordsSynonymPresenter (WordsSynonymPresenterImpl<WordsSynonymView> presenter){
        return presenter;
    }
    @Provides
    MarkedWordsPresenter<MarkedWordsView> provideMarkedWordsPresenter (MarkedWordsPresenterImpl<MarkedWordsView> presenter){
        return presenter;
    }
    @Provides
    LoginPagePresenter<LoginPageView> provideLoginPagePresenter (LoginPagePresenterImpl<LoginPageView> presenter){
        return presenter;
    }

    @Provides
    LastWODPresenter<LastWODView> provideLastPagePresenter (LastWODPresenterImpl<LastWODView> presenter){
        return presenter;
    }

    @Provides
    ForgotPwdPresenter<ForgotPwdView> provideForgotPwdPresenter (ForgotPwdPresenterImpl<ForgotPwdView> presenter){
        return presenter;
    }

    @Provides
    InstitutePresenter<InstituteView> provideInstitutePresenter (InstitutePresenterImpl<InstituteView> presenter){
        return presenter;
    }

    @Provides
    ProfilePresenter<ProfileView> provideProfilePresenter (ProfilePresenterImpl<ProfileView> presenter){
        return presenter;
    }

}
