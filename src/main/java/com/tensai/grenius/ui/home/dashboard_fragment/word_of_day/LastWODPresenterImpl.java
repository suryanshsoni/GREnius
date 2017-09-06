package com.tensai.grenius.ui.home.dashboard_fragment.word_of_day;

import android.util.Log;

import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.model.WordOfDay;
import com.tensai.grenius.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by rishabhpanwar on 01/09/17.
 */

public class LastWODPresenterImpl <V extends LastWODView> extends BasePresenter<V> implements LastWODPresenter<V> {

    List<WordOfDay> wordOfDays;

    @Inject
    public LastWODPresenterImpl(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public List<WordOfDay> getLastWords() {

        getDataManager().wordOfDays(getDataManager().getCurrentUserid(),getDataManager().getSessionId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<WordOfDay>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<WordOfDay> wordOfDays) {
                        getMvpView().setView(wordOfDays);
                    }

                });


        return null;
    }
}
