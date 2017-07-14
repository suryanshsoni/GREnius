package com.tensai.grenius.ui.home.dashboard_fragment;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.tensai.grenius.R;
import com.tensai.grenius.model.Word;

/**
 * Created by Pavilion on 13-07-2017.
 */

@NonReusable
@Layout(R.layout.dashboard_wordofday_layout)

public class WordOfDay {

    private Word word;
    private Context ctx;

    public WordOfDay(){

    }

    @Resolve
    private void onResolved(){
    }
}