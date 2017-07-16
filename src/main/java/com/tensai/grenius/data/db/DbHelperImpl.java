package com.tensai.grenius.data.db;

import android.util.Log;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.tensai.grenius.data.db.DbHelper;
import com.tensai.grenius.model.Category;
import com.tensai.grenius.model.Category_Table;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.model.Word_Table;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Pavilion on 21-06-2017.
 */

public class DbHelperImpl implements DbHelper {
    @Inject
    public DbHelperImpl() {
    }

    @Override
    public Boolean areWordsPresent() {
        Word word = SQLite.select()
                    .from(Word.class)
                    .where(Word_Table.sno.eq("1"))
                    .querySingle();
        if(word!=null)
        Log.i("DEMO",word.toString()+"");
        return word!=null;
    }

    @Override
    public Boolean areCategoriesPresent() {
        Category category = SQLite.select()
                .from(Category.class)
                .where(Word_Table.sno.eq("1"))
                .querySingle();
        if(category!=null)
            Log.i("DEMO",category.toString()+"");
        return category!=null;
    }

    @Override
    public void getAllWords(QueryTransaction.QueryResultListCallback<Word> queryCallback,Transaction.Error errorCallback) {
        SQLite.select()
                .from(Word.class)
                .where(Word_Table.pos.greaterThan("0"))
                .async()
                .queryListResultCallback(queryCallback)
                .error(errorCallback)
                .execute();

    }

    @Override
    public void getHighFreqWords(QueryTransaction.QueryResultListCallback<Word> queryCallback, Transaction.Error errorCallback) {
        SQLite.select().from(Word.class)
                .where(Word_Table.pos.greaterThan("30"))
                .async()
                .queryListResultCallback(queryCallback)
                .error(errorCallback)
                .execute();
    }

    @Override
    public void getFiftyWords(int pos,QueryTransaction.QueryResultListCallback<Word> queryCallback,Transaction.Error errorCallback) {
        SQLite.select()
                .from(Word.class)
                .where(Word_Table.pos.greaterThan(String.valueOf(pos-1)))
                .async()
                .queryListResultCallback(queryCallback)
                .error(errorCallback)
                .execute();

    }

    @Override
    public void getAllCategories(QueryTransaction.QueryResultListCallback<Category> queryCallback, Transaction.Error errorCallback) {
        SQLite.select()
                .from(Category.class)
                .where(Category_Table.sno.greaterThan("0"))
                .async()
                .queryListResultCallback(queryCallback)
                .error(errorCallback)
                .execute();

    }

}
