package com.tensai.grenius.data;


import com.tensai.grenius.data.db.DbHelper;
import com.tensai.grenius.data.network.ApiHelper;
import com.tensai.grenius.data.prefs.PreferenceHelper;

/**
 * Created by Pavilion on 21-06-2017.
 */

public interface DataManager extends DbHelper, PreferenceHelper, ApiHelper {


}
