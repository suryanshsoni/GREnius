package com.tensai.grenius.ui.home.dashboard_fragment;

import android.util.Log;

import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.model.*;
import com.tensai.grenius.model.WordOfDay;
import com.tensai.grenius.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by rishabhpanwar on 24/06/17.
 */

public class DashboardPresenterImpl <V extends DashboardView> extends BasePresenter<V> implements DashboardPresenter<V> {
    Word word = new Word();

    @Inject
    public DashboardPresenterImpl(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getDashboardArticles() {

        getDataManager().getDashboardArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Func1<Throwable, List<Articles>>() {
                    @Override
                    public List<Articles> call(Throwable throwable) {
                        return null;
                    }
                })
                .subscribe(new Action1<List<Articles>>() {
                    @Override
                    public void call(List<Articles> articles) {
                        getMvpView().showDashboardArticles(articles);
                    }
                });
    }

    @Override
    public void getWordOfDay() {
        getDataManager().getWordOfDay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Func1<Throwable, WordOfDay>() {
                    @Override
                    public WordOfDay call(Throwable throwable) {
                        return getDataManager().getSavedWordOfDay();
                    }
                })
                .subscribe(new Action1<com.tensai.grenius.model.WordOfDay>() {
                    @Override
                    public void call(WordOfDay wordOfDay) {
                        getDataManager().saveWordOfDay(wordOfDay);
                        getMvpView().showWordOfDay(wordOfDay);
                    }

                });
    }

    @Override
    public boolean isWordOfDayMarked(WordOfDay wordOfDay) {
        List<Word> markedWords = getDataManager().getMarkedWords();
        word.setWord(wordOfDay.getWord());
        if (markedWords != null) {
            try {
                if (markedWords.contains(word)) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        else{
            return true;
        }
    }

    @Override
    public void markWord(WordOfDay wordOfDay) {
        word = new Word(null,wordOfDay.getWord(),wordOfDay.getMeaning(),wordOfDay.getSynonym(),"z",wordOfDay.getPos(),wordOfDay.getExample(),wordOfDay.getImagePath(),"Y",null);
        getDataManager().setMarkedWords(word);
    }

    @Override
    public void removeMarkedWord(WordOfDay wordOfDay) {
        word = new Word(null,wordOfDay.getWord(),wordOfDay.getMeaning(),wordOfDay.getSynonym(),"z",wordOfDay.getPos(),wordOfDay.getExample(),wordOfDay.getImagePath(),"Y",null);
        getDataManager().removeMarkedWords(word);
    }
}
