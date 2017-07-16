package com.tensai.grenius.ui.home.words_synonym_fragement;

import android.support.annotation.Nullable;
import android.util.Log;

import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.model.Category;
import com.tensai.grenius.ui.base.BasePresenter;
import com.tensai.grenius.ui.home.articles_fragment.ArticlesPresenter;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by rishabhpanwar on 16/07/17.
 */

public class WordsSynonymPresenterImpl <V extends WordsSynonymView> extends BasePresenter<V> implements WordsSynonymPresenter<V> {
    List<Category> tResults;
    @Inject
    public WordsSynonymPresenterImpl(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getCategories() {
        getDataManager().getAllCategories(new QueryTransaction.QueryResultListCallback<Category>() {
            @Override
            public void onListQueryResult(QueryTransaction transaction, @Nullable List<Category> tResult) {
                tResults = tResult;
                Log.d("Demo",""+tResult.get(0).getCategory());
                getMvpView().showCategories(tResult);
            }
        }, new Transaction.Error() {
            @Override
            public void onError(Transaction transaction, Throwable error) {
                Log.d("DEMO", "Error here");
                error.printStackTrace();
            }
        });
    }
}


