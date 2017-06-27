package com.tensai.grenius.ui.home.articles_fragment;

import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.data.network.response.LoginResponse;
import com.tensai.grenius.model.Articles;
import com.tensai.grenius.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by rishabhpanwar on 25/06/17.
 */

public class ArticlesPresenterImpl <V extends ArticlesView> extends BasePresenter<V> implements ArticlesPresenter<V> {

    @Inject
    public ArticlesPresenterImpl(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getArticles() {
        getMvpView().showLoading("Loading Articles...");
        getDataManager().getArticles()
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
                        getMvpView().hideLoading();
                        getMvpView().showArticles(articles);
                    }
                });
    }
}
