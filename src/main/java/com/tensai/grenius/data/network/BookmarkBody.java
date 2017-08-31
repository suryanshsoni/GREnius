package com.tensai.grenius.data.network;

import com.tensai.grenius.model.Word;

import java.util.ArrayList;

/**
 * Created by rishabhpanwar on 31/08/17.
 */

public class BookmarkBody {

    ArrayList<Word> words;
    String userId;
    String sessionId;

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
