package com.tensai.grenius.ui.home.articles_fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tensai.grenius.R;
import com.tensai.grenius.model.Articles;
import com.tensai.grenius.util.ScreenUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tensai.grenius.model.Word_Table.imagePath;
import static com.tensai.grenius.util.AppConstants.API_BASE_URL;
import static com.tensai.grenius.util.AppConstants.GRENIUS_IMAGE_PREFIX;

/**
 * Created by rishabhpanwar on 25/06/17.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder>{

    Context ctx;
    List<Articles> articles;
    int screenUtilsWidth,dp;

    public ArticlesAdapter(Context context, List<Articles> articles) {
        this.ctx = context;
        this.articles = articles;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_articles, parent, false);
        screenUtilsWidth=ScreenUtils.getScreenWidth(ctx);
        float density = ctx.getResources().getDisplayMetrics().density;
        dp = (int) (screenUtilsWidth / density);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvArticleTitle.setText(articles.get(position).getTitle());
        final String URL= API_BASE_URL+articles.get(position).getImagePath();

        //holder.ivArticleBckgrnd.setColorFilter(Color.argb(129,0,0,0));

        Picasso.with(ctx)
                .load(URL)
                .resize(dp,260)
                .into(holder.ivArticleBckgrnd);

        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ctx, ArticleSingleActivity.class);
                intent.putExtra("title",articles.get(position).getTitle());
                intent.putExtra("imagePath",""+URL);
                intent.putExtra("desc",""+articles.get(position).getDesc());
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        try{
            return articles.size();
        }catch (Exception e){
            return 0;
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_article_bckgrnd)
        ImageView ivArticleBckgrnd;
        @BindView(R.id.tv_article_title)
        TextView tvArticleTitle;
        @BindView(R.id.rl)
        RelativeLayout rl;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface Callback{
        void onArticleClick(String title,String imagePath, String desc);
    }
}
