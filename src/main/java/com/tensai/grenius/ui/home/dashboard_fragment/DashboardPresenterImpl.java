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
                        return null;
                    }
                })
                .subscribe(new Action1<com.tensai.grenius.model.WordOfDay>() {
                    @Override
                    public void call(WordOfDay wordOfDay) {
                        getMvpView().showWordOfDay(wordOfDay);
                    }

                });
    }
}
