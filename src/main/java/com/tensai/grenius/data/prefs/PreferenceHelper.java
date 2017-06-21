package com.tensai.grenius.data.prefs;

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
}
