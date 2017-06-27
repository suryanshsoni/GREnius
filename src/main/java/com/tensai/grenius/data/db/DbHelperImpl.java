package com.tensai.grenius.data.db;

import android.util.Log;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.tensai.grenius.data.db.DbHelper;
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
    public List<String> getAllWordlists() {
        List<String> wordlists=new ArrayList<String>();
        wordlists.add("Abound - Banter");
        wordlists.add("Baptism - Cherubic");
        wordlists.add("Dawdle - Expedite");
        wordlists.add("Fallow-Jargon");
        return wordlists;
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

}
