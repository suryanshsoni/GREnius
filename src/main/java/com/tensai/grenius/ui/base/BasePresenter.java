package com.tensai.grenius.ui.base;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.tensai.grenius.data.DataManager;

import javax.inject.Inject;

/**
 * Created by Pavilion on 21-06-2017.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private final DataManager dataManager;

    private V mvpView;
    @Inject
    TextToSpeech tts;
    @Inject
    FirebaseAnalytics firebaseAnalytics;
    @Inject
    public BasePresenter(DataManager dataManager){
        this.dataManager = dataManager;
    }

    @Override
    public void onAttach(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void onDetach() {
        this.mvpView = null;
    }


    @Override
    public void handleApiError(Object error){

    }

    @Override
    public void setUserAsLoggedOut() {

    }

    public boolean isViewAttached(){
        return mvpView != null;
    }

    public V getMvpView(){
        return mvpView;
    }

    public void checkViewAttached(){
        if(!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public DataManager getDataManager() {
        return dataManager;
    }
    public TextToSpeech getTts(){return tts;}

    public void speak(String toSpeak){
        getTts().speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }
    public static class MvpViewNotAttachedException extends RuntimeException{
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before"+
                    " requesting data to the Presenter");
        }
    }
}
