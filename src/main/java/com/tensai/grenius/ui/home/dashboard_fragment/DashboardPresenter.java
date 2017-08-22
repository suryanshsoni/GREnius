package com.tensai.grenius.ui.home.dashboard_fragment;

import com.tensai.grenius.model.WordOfDay;
import com.tensai.grenius.ui.base.MvpPresenter;

import java.util.List;

/**
 * Created by rishabhpanwar on 24/06/17.
 */

public interface DashboardPresenter <V extends DashboardView> extends MvpPresenter<V> {

    void getDashboardArticles();
    void speak(String toSpeak);
    void getWordOfDay();
    boolean isWordOfDayMarked(com.tensai.grenius.model.WordOfDay wordOfDay);
    void markWord(WordOfDay wordOfDay);
    void removeMarkedWord(WordOfDay obj);
    boolean getTutorial();
    void setTutorial(boolean tutorialshown);


}
