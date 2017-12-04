package com.tensai.grenius.ui.home.institutes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tensai.grenius.R;
import com.tensai.grenius.ui.base.BaseActivity;
import com.tensai.grenius.view.SlideTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstituteIndividual extends BaseActivity {

    String title, url, desc, location, imagePath;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.institutes_appbar)
    AppBarLayout institutesAppbar;
    @BindView(R.id.institute_title)
    SlideTextView instituteTitle;
    @BindView(R.id.institute_bckgrnd)
    ImageView instituteBckgrnd;
    @BindView(R.id.institute_url)
    SlideTextView instituteUrl;
    @BindView(R.id.institute_details)
    SlideTextView instituteDetails;

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
        desc = intent.getStringExtra("desc");
        url = intent.getStringExtra("url");

        Picasso.with(getApplicationContext())
                .load(imagePath)
                .resize(300,200)
                .into(instituteBckgrnd);

        instituteTitle.setText(title);
        //instituteLocation.setText(location);
        instituteUrl.setText("Website: "+ url);
        instituteDetails.setText(desc);

    }
}
