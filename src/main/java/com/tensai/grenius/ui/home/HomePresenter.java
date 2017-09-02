package com.tensai.grenius.ui.home;

import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.MvpPresenter;
import com.tensai.grenius.ui.base.MvpView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rishabhpanwar on 24/06/17.
 */

public interface HomePresenter <V extends HomeView> extends MvpPresenter<V> {

    void getUserDetails();
    int getResourceId();
    void setResourceId(int resourceId);
    int getWordCount();
    void update();
    List<Word> getMarkedWords();
    void uploadBookmarkedWords(ArrayList<Word> words);
    void deleteUserData();
}
