package com.tensai.grenius.ui.home.dashboard_fragment;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.squareup.picasso.Picasso;
import com.tensai.grenius.R;
import com.tensai.grenius.model.Articles;

import butterknife.BindView;

import static com.tensai.grenius.util.AppConstants.API_BASE_URL;

/**
 * Created by rishabhpanwar on 06/07/17.
 */
@NonReusable
@Layout(R.layout.dashboard_article_layout)

public class ArticlesDashboard {

    @BindView(R.id.iv_dashboard_article_bckgrnd)
    ImageView ivArticleBckgrnd;
    @View(R.id.tv_article_titles)
    private TextView tv_article_title;

    private Articles articles;
    private Context ctx;
    private String imagePath;
    CharSequence title_text;

    public ArticlesDashboard(Context ctx, Articles articles) {
        this.articles = articles;
        this.ctx = ctx;
    }

    @Resolve
    private void onResolved() {

        title_text = articles.getTitle();

        imagePath = API_BASE_URL + articles.getImagePath();
        tv_article_title.setText("Coming Soon!");

        //ivArticleBckgrnd.setBackgroundResource(R.drawable.ic);

        /*Picasso.with(ctx)
                .load(imagePath)
                .placeholder(R.drawable.avatar_one)
                .into(ivArticleBckgrnd);*/
    }

    @Click(R.id.card_view)
    public Articles onArticleClicked() {
        return articles;
    }
}
