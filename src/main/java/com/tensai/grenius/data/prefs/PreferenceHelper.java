package com.tensai.grenius.data.prefs;

import com.tensai.grenius.model.Word;
import com.tensai.grenius.model.WordOfDay;

import java.util.List;

/**
 * Created by Pavilion on 21-06-2017.
 */

public interface PreferenceHelper {
    void setCurrentUserId(String userId);

    String getCurrentUserid();

    void setCurrentUserName(String userName);

    String getCurrentUserName();

    void setSessionId(String sessionId);

    String getSessionId();

    void setMarkedWords(Word obj);

    List<Word> getMarkedWords();

    void removeMarkedWords(Word obj);

    boolean getTutorial();

    void setTutorial(boolean tutorialshown);

    void saveWordOfDay(WordOfDay word);
    WordOfDay getSavedWordOfDay();
    void setResourceId(int resourceId);

    int getResourceId();
    void setWordCount(int count);
    int getWordCount();
    void setCategoryCount(int count);
    int getCategoryCount();
}
