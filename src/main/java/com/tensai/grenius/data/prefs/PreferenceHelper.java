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

    String getUserFBToken();

    void setUserFBToken(String fbToken);

    void setSessionId(String sessionId);

    String getSessionId();

    void setMarkedWords(Word obj);

    List<Word> getMarkedWords();

    void removeMarkedWords(Word obj);

    boolean getTutorial(String place);
    boolean isAlarmSet();
    void unsetAlarm();
    void setTutorial(String place, boolean tutorialshown);

    void saveWordOfDay(WordOfDay word);
    WordOfDay getSavedWordOfDay();
    void setResourceId(int resourceId);

    int getResourceId();
    void setWordCount(int count);
    int getWordCount();
    void setCategoryCount(int count);
    int getCategoryCount();

    void deleteUserData();

    void saveBookmarks(List<Word> bookmarkList);

    List<String> getProfile();

    void updateProfile(String gender, String mobile, String city, String motive);

    void updateProgress(int progress);

    int getProgress();
}
