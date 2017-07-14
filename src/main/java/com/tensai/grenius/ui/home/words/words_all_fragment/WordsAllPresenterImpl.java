package com.tensai.grenius.ui.home.words.words_all_fragment;

import android.support.annotation.Nullable;
import android.util.Log;

import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ishitabhandari on 25/06/17.
 */

public class WordsAllPresenterImpl <V extends WordsAllView> extends BasePresenter<V> implements WordsAllPresenter<V> {
    protected List<Word> tResults;
    @Inject
    public WordsAllPresenterImpl(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getAllWords() {
        getDataManager().getAllWords(new QueryTransaction.QueryResultListCallback<Word>() {
            @Override
            public void onListQueryResult(QueryTransaction transaction, @Nullable List<Word> tResult) {
                tResults = tResult;
                getMvpView().showWordlists(tResult);
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
    public ArrayList<Word> getWordlist(int fromIndex, int toIndex) {
        ArrayList<Word> wordlist = new ArrayList<Word>();
        for (int i= fromIndex; i<toIndex; i++)
        {
            wordlist.add(tResults.get(i));
        }
        return wordlist;
    }

}
