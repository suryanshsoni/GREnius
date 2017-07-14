package com.tensai.grenius.ui.home.words.words_high_frequency_fragment;

import android.support.annotation.Nullable;
import android.util.Log;

import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ishitabhandari on 06/07/17.
 */

public class WordsHighFreqPresenterImpl <V extends WordsHighFreqView> extends BasePresenter<V> implements WordsHighFreqPresenter<V> {

    protected List<Word> fWords;

    @Inject
    public WordsHighFreqPresenterImpl(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getFrequentWords(){
        getDataManager().getFreqWords(new QueryTransaction.QueryResultListCallback<Word>() {
            @Override
            public void onListQueryResult(QueryTransaction transaction, @Nullable List<Word> tResult) {
                fWords = tResult;
                getMvpView().showWords(fWords);
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
