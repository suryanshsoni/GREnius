package com.tensai.grenius.ui.home.institutes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tensai.grenius.R;
import com.tensai.grenius.ui.base.BaseActivity;
import com.tensai.grenius.view.SlideTextView;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstituteIndividual extends BaseActivity {

    String title, url, location, imagePath;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.institutes_appbar)
    AppBarLayout institutesAppbar;
    @BindView(R.id.institute_bckgrnd)
    ImageView instituteBckgrnd;
    @BindView(R.id.institute_details)
    WebView instituteDetails;

    public static String desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_individual);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        imagePath = intent.getStringExtra("imagePath");
        //location = intent.getStringExtra("location");
        //desc = intent.getStringExtra("desc");
        //url = intent.getStringExtra("url");

        Picasso.with(getApplicationContext())
                .load(imagePath)
                .into(instituteBckgrnd);


        instituteDetails.getSettings().setJavaScriptEnabled(true);
        instituteDetails.getSettings().setLoadWithOverviewMode(true);
        instituteDetails.getSettings().setUseWideViewPort(true);
        instituteDetails.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        instituteDetails.getSettings().setBuiltInZoomControls(true);

        String str= StringEscapeUtils.unescapeJson(desc);
        instituteDetails.loadData(str,"text/html;charset=utf-8","utf-8");

    }
}
