package com.tensai.grenius.data.prefs;

import com.tensai.grenius.model.Word;

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



    void setResourceId(int resourceId);

    int getResourceId();
    void setWordCount(int count);
    int getWordCount();

}
