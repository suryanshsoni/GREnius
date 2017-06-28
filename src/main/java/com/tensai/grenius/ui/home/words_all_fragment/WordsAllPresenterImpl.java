package com.tensai.grenius.ui.home.words_all_fragment;

import android.support.annotation.Nullable;
import android.util.EventLog;
import android.util.Log;

import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.BasePresenter;
import com.tensai.grenius.ui.home.HomePresenter;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by ishitabhandari on 25/06/17.
 */

public class WordsAllPresenterImpl <V extends WordsAllView> extends BasePresenter<V> implements WordsAllPresenter<V> {
    @Inject
    public WordsAllPresenterImpl(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getWordlist() {
        List<String> wordlists = getDataManager().getAllWordlists();
        getMvpView().showWordlists(wordlists);
    }

    @Override
    public void getAllWords() {
        getDataManager().getAllWords(new QueryTransaction.QueryResultListCallback<Word>() {
            @Override
            public void onListQueryResult(QueryTransaction transaction, @Nullable List<Word> tResult) {
                Log.d("Demo",""+tResult.toString());
                Log.d("Demo",""+tResult.get(0).getWord());
            }
        }, new Transaction.Error() {
            @Override
            public void onError(Transaction transaction, Throwable error) {
                Log.d("DEMO", "Error here");
                error.printStackTrace();
            }
        });
    }

    @Override
    public void onEvent(int position1, int position2 ) {

    }
}
