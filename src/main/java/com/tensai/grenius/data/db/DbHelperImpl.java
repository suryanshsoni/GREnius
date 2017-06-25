package com.tensai.grenius.data.db;

import com.tensai.grenius.data.db.DbHelper;

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
        List<String> wordlists=null;
        wordlists.add("Abound - Banter");
        wordlists.add("Baptism - Cherubic");
        wordlists.add("Dawdle - Expedite");
        wordlists.add("Fallow-Jargon");
        return wordlists;
    }
}
