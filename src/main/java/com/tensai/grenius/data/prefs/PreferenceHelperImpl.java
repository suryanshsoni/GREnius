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
    private static final String PREF_KEY_USER_FB_TOKEN = "PREF_KEY_USER_FB_TOKEN";
    private static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";
    private static final String PREF_KEY_CURRENT_USER_GENDER = "PREF_KEY_CURRENT_USER_GENDER";
    private static final String PREF_KEY_MOBILE = "PREF_KEY_MOBILE";
    private static final String PREF_KEY_CITY = "PREF_KEY_CITY";
    private static final String PREF_KEY_MOTIVE = "PREF_KEY_MOTIVE";
    private static final String PREF_KEY_SESSION_ID = "PREF_KEY_SESSION_ID";
    private static final String PREF_KEY_MARKED_WORDS = "PREF_KEY_MARKED_WORDS";
    private static final String PREF_KEY_RESOURCE_ID = "PREF_KEY_RESOURCE_ID";
    private static final String PREF_KEY_WORDCOUNT_ID = "PREF_KEY_WORDCOUNT_ID";
    private static final String PREF_KEY_DB_TUTORIAL = "PREF_KEY_DB_TUTORIAl";
    private static final String PREF_KEY_CARD_TUTORIAL = "PREF_KEY_CARD_TUTORIAl";
    private static final String PREF_KEY_GENRE_TUTORIAL = "PREF_KEY_GENRE_TUTORIAl";
    private static final String PREF_KEY_QUIZ_TUTORIAL = "PREF_KEY_QUIZ_TUTORIAL";
    private static final String PREF_KEY_CATEGORY_ID = "PREF_KEY_CATEGORY_ID";
    private static final String PREF_KEY_WORDOFDAY_ID = "PREF_KEY_WORDOFDAY_ID";
    private static final String PREF_KEY_ALARMSET_ID = "PREF_KEY_ALARMSET_ID";
    private static final String PREF_KEY_PROGRESS = "PREF_KEY_PROGRESS";

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
    public String getUserFBToken() {    return prefs.getString(PREF_KEY_USER_FB_TOKEN,null);    }

    @Override
    public void setUserFBToken(String fbToken) {
        prefs.edit().putString(PREF_KEY_USER_FB_TOKEN, fbToken).apply();
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
        if(getMarkedWords()!=null){
            markedlist = getMarkedWords();
        }
        markedlist.add(obj);
        SharedPreferences.Editor editor= prefs.edit();
        editor.putString(PREF_KEY_MARKED_WORDS,new Gson().toJson(markedlist));
        editor.apply();
    }

    @Override
    public void saveBookmarks(List<Word> bookmarkList) {
        Log.i("getBM", "gg"+bookmarkList.toString());
        prefs.edit().putString(PREF_KEY_MARKED_WORDS,new Gson().toJson(bookmarkList)).apply();
    }

    @Override
    public List<String> getProfile() {
        List<String> profile = new ArrayList<String>();
        profile.add(0,prefs.getString(PREF_KEY_CURRENT_USER_ID, null));
        profile.add(1,prefs.getString(PREF_KEY_CURRENT_USER_GENDER,null));
        profile.add(2,prefs.getString(PREF_KEY_MOBILE, null));
        profile.add(3,prefs.getString(PREF_KEY_CITY,null));
        profile.add(4,prefs.getString(PREF_KEY_MOTIVE, null));
        return  profile;
    }

    @Override
    public void updateProfile(String gender, String mobile, String city, String motive) {
        prefs.edit().putString(PREF_KEY_CURRENT_USER_GENDER, gender).apply();
        prefs.edit().putString(PREF_KEY_MOBILE, mobile).apply();
        prefs.edit().putString(PREF_KEY_CITY, city).apply();
        prefs.edit().putString(PREF_KEY_MOTIVE, motive).apply();
    }

    @Override
    public void updateProgress(int progress) {  prefs.edit().putInt(PREF_KEY_PROGRESS,progress).apply();}

    @Override
    public int getProgress() {  return prefs.getInt(PREF_KEY_PROGRESS,1);  }

    @Override
    public List<Word> getMarkedWords() {
        String json = prefs.getString(PREF_KEY_MARKED_WORDS, null);
        Type type = new TypeToken<List<Word>>() {
        }.getType();
        return new Gson().fromJson(json, type);
    }

    @Override
    public void removeMarkedWords(Word obj) {
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
            case "quiz":
                tutorialshown = prefs.getBoolean(PREF_KEY_QUIZ_TUTORIAL, false);
                break;
            default:
                tutorialshown = false;
                break;
        }
        return tutorialshown;
    }

    @Override
    public boolean isAlarmSet() {
        boolean val= prefs.getBoolean(PREF_KEY_ALARMSET_ID,false);
        if(val==false){
            prefs.edit().putBoolean(PREF_KEY_ALARMSET_ID,!val).apply();
        }
        return val;
    }

    @Override
    public void unsetAlarm() {
        prefs.edit().putBoolean(PREF_KEY_ALARMSET_ID,false).apply();
    }

    @Override
    public void setTutorial( String place, boolean tutorialshown) {
        switch (place) {
            case "dashboard":
                prefs.edit().putBoolean(PREF_KEY_DB_TUTORIAL,tutorialshown).apply();
                break;
            case "card":
                prefs.edit().putBoolean(PREF_KEY_CARD_TUTORIAL, tutorialshown).apply();
                break;
            case "genres":
                prefs.edit().putBoolean(PREF_KEY_GENRE_TUTORIAL, tutorialshown).apply();
                break;
            case "quiz":
                prefs.edit().putBoolean(PREF_KEY_QUIZ_TUTORIAL, tutorialshown).apply();
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

    @Override
    public void deleteUserData() {
        prefs.edit().remove(PREF_KEY_SESSION_ID).apply();
        prefs.edit().remove(PREF_KEY_CURRENT_USER_ID).apply();
        prefs.edit().remove(PREF_KEY_CURRENT_USER_NAME).apply();
        prefs.edit().remove(PREF_KEY_USER_FB_TOKEN).apply();
        prefs.edit().remove(PREF_KEY_RESOURCE_ID).apply();
        prefs.edit().remove(PREF_KEY_MARKED_WORDS).apply();
        prefs.edit().remove(PREF_KEY_PROGRESS).apply();
        prefs.edit().remove(PREF_KEY_MOTIVE).apply();
        prefs.edit().remove(PREF_KEY_CITY).apply();
        prefs.edit().remove(PREF_KEY_CURRENT_USER_GENDER).apply();
        prefs.edit().remove(PREF_KEY_MOBILE).apply();
        markedlist=null;
        markedlist=new ArrayList<Word>();
    }

}
