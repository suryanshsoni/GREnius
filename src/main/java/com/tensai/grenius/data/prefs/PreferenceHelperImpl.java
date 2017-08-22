package com.tensai.grenius.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tensai.grenius.data.prefs.PreferenceHelper;
import com.tensai.grenius.di.ApplicationContext;
import com.tensai.grenius.di.PreferenceInfo;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.model.WordOfDay;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

/**
 * Created by Pavilion on 21-06-2017.
 */

public class PreferenceHelperImpl implements PreferenceHelper {
    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";
    private static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";
    private static final String PREF_KEY_SESSION_ID = "PREF_KEY_SESSION_ID";
    private static final String PREF_KEY_MARKED_WORDS = "PREF_KEY_MARKED_WORDS";
    private static final String PREF_KEY_RESOURCE_ID = "PREF_KEY_RESOURCE_ID";
    private static final String PREF_KEY_WORDCOUNT_ID = "PREF_KEY_WORDCOUNT_ID";
    private static final String PREF_KEY_DB_TUTORIAL = "PREF_KEY_DB_TUTORIAl";
    private static final String PREF_KEY_CARD_TUTORIAL = "PREF_KEY_CARD_TUTORIAl";
    private static final String PREF_KEY_GENRE_TUTORIAL = "PREF_KEY_GENRE_TUTORIAl";
    private static final String PREF_KEY_CATEGORY_ID = "PREF_KEY_CATEGORY_ID";
    private static final String PREF_KEY_WORDOFDAY_ID = "PREF_KEY_WORDOFDAY_ID";

    private final SharedPreferences prefs;
    List<Word> markedlist = new ArrayList<Word>();

    @Inject
    public PreferenceHelperImpl(@ApplicationContext Context context, @PreferenceInfo String prefFileName){
        prefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public void setCurrentUserId(String userId) {
        prefs.edit().putString(PREF_KEY_CURRENT_USER_ID, userId).apply();
    }

    @Override
    public String getCurrentUserid() {
        return prefs.getString(PREF_KEY_CURRENT_USER_ID, null);
    }

    @Override
    public void setCurrentUserName(String userName) {
        prefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply();
    }

    @Override
    public String getCurrentUserName() {
        return prefs.getString(PREF_KEY_CURRENT_USER_NAME, null);
    }

    @Override
    public void setSessionId(String sessionId) {
        prefs.edit().putString(PREF_KEY_SESSION_ID, sessionId).apply();
    }

    @Override
    public String getSessionId() {
        return prefs.getString(PREF_KEY_SESSION_ID, null);
    }

    @Override
    public void setMarkedWords(Word obj) {
        Log.i("Mark: ", "In mark method prefs"+obj.getWord());
        if(getMarkedWords()!=null){
            markedlist = getMarkedWords();
        }
        markedlist.add(obj);
        Log.i("Mark: ", "In mark method prefs"+markedlist);
        SharedPreferences.Editor editor= prefs.edit();
        editor.putString(PREF_KEY_MARKED_WORDS,new Gson().toJson(markedlist));
        editor.apply();

    }

    @Override
    public List<Word> getMarkedWords() {
        Log.i("Mark: ", "In get mark method ");

        String json = prefs.getString(PREF_KEY_MARKED_WORDS, null);
        Type type = new TypeToken<List<Word>>() {
        }.getType();
        return new Gson().fromJson(json, type);
    }

    @Override
    public void removeMarkedWords(Word obj) {
        Log.i("Mark: ", "In mark method prefs"+obj.getWord());
        markedlist = getMarkedWords();
        markedlist.remove(obj);
        SharedPreferences.Editor editor= prefs.edit();
        editor.putString(PREF_KEY_MARKED_WORDS,new Gson().toJson(markedlist));
        editor.apply();
    }

    @Override
    public boolean getTutorial(String place) {
        boolean tutorialshown = false;
        switch (place) {
            case "dashboard":
                tutorialshown = prefs.getBoolean(PREF_KEY_DB_TUTORIAL, false);
                break;
            case "card":
                tutorialshown = prefs.getBoolean(PREF_KEY_CARD_TUTORIAL, false);
                break;
            case "genres":
                tutorialshown = prefs.getBoolean(PREF_KEY_GENRE_TUTORIAL, false);
                break;
            default:
                tutorialshown = false;
                break;
        }
        return tutorialshown;
    }

    @Override
    public void setTutorial( String place, boolean tutorialshown) {
        switch (place) {
            case "dashboard":
                Log.i("tut","in db" );
                prefs.edit().putBoolean(PREF_KEY_DB_TUTORIAL,tutorialshown).apply();
                break;
            case "card":
                Log.i("tut","in card" );
                prefs.edit().putBoolean(PREF_KEY_CARD_TUTORIAL, tutorialshown).apply();
                break;
            case "genres":
                Log.i("tut","in genres" );
                prefs.edit().putBoolean(PREF_KEY_GENRE_TUTORIAL, tutorialshown).apply();
                break;
        }
    }

    @Override
    public void saveWordOfDay(WordOfDay word) {
        SharedPreferences.Editor editor= prefs.edit();
        editor.putString(PREF_KEY_WORDOFDAY_ID,new Gson().toJson(word));
        editor.apply();
    }

    @Override
    public WordOfDay getSavedWordOfDay() {
        String json = prefs.getString(PREF_KEY_WORDOFDAY_ID, null);
        Type type = new TypeToken<WordOfDay>() {
        }.getType();
        return new Gson().fromJson(json, type);
    }

    @Override
    public void setResourceId(int resourceId) {
        prefs.edit().putInt(PREF_KEY_RESOURCE_ID, resourceId).apply();
    }

    @Override
    public int getResourceId() {
        return prefs.getInt(PREF_KEY_RESOURCE_ID, 0);
    }

    @Override
    public void setWordCount(int count) {
        prefs.edit().putInt(PREF_KEY_WORDCOUNT_ID, count).apply();
    }

    @Override
    public int getWordCount() {
        return prefs.getInt(PREF_KEY_WORDCOUNT_ID, 0);
    }

    @Override
    public void setCategoryCount(int count) {
        prefs.edit().putInt(PREF_KEY_CATEGORY_ID, count).apply();
    }

    @Override
    public int getCategoryCount() {
        return prefs.getInt(PREF_KEY_CATEGORY_ID, 0);
    }

}
