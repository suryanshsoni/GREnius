package com.tensai.grenius.ui.home.words_synonym_fragement;

import com.tensai.grenius.ui.base.MvpPresenter;

/**
 * Created by rishabhpanwar on 16/07/17.
 */

public interface WordsSynonymPresenter <V extends WordsSynonymView> extends MvpPresenter<V> {

    void getCategories();
    boolean getTutorial();
    void setTutorial(boolean tutorialshown);

}
