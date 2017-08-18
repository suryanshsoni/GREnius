package com.tensai.grenius.ui.home.articles_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tensai.grenius.R;
import com.tensai.grenius.util.ScreenUtils;
import com.tensai.grenius.view.SlideTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleSingleActivity extends AppCompatActivity {

    @BindView(R.id.articles_title)
    SlideTextView articlesTitle;
    @BindView(R.id.articles_bckgrnd)
    ImageView articlesBckgrnd;
    @BindView(R.id.articles_details)
    WebView articlesDetails;
    String title,Url;

    static String msg;
    int screenUtilsWidth,dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_single);
        ButterKnife.bind(this);

        screenUtilsWidth= ScreenUtils.getScreenWidth(getApplicationContext());
        float density = getResources().getDisplayMetrics().density;
        dp = (int) (screenUtilsWidth / density);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();
        //msg=intent.getStringExtra("desc");
        title=intent.getStringExtra("title");
        Url=intent.getStringExtra("imagePath");

        Picasso.with(getApplicationContext())
                .load(Url)
                .resize(300,200)
                .into(articlesBckgrnd);

        articlesDetails.loadData(msg,"text/html;charset=utf-8","utf-8");
        articlesTitle.setText(title);
    }
}
