package com.tensai.grenius.data.db;

import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.tensai.grenius.model.Category;
import com.tensai.grenius.model.Word;

import java.util.List;

/**
 * Created by Pavilion on 21-06-2017.
 */

public interface DbHelper {
    Boolean areWordsPresent();
    Boolean areCategoriesPresent();

    void getAllWords(QueryTransaction.QueryResultListCallback<Word> queryCallback,Transaction.Error errorCallback);
    void getHighFreqWords(QueryTransaction.QueryResultListCallback<Word> queryCallback, Transaction.Error errorCallback);
    void getFiftyWords(int pos,QueryTransaction.QueryResultListCallback<Word> queryCallback,Transaction.Error errorCallback);
    void getAllCategories(QueryTransaction.QueryResultListCallback<Category> queryCallback, Transaction.Error errorCallback);

}
