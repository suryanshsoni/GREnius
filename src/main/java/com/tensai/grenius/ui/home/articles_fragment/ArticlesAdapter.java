package com.tensai.grenius.ui.home.articles_fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tensai.grenius.R;
import com.tensai.grenius.model.Articles;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tensai.grenius.util.AppConstants.GRENIUS_IMAGE_PREFIX;

/**
 * Created by rishabhpanwar on 25/06/17.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    Context ctx;
    List<Articles> articles;

    public ArticlesAdapter(Context context, List<Articles> articles) {
        this.ctx = context;
        this.articles = articles;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_articles, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvArticleTitle.setText(articles.get(position).getTitle());
        String URL= GRENIUS_IMAGE_PREFIX+articles.get(position).getImagePath();

        Picasso.with(ctx)
                .load(URL)
                .into(holder.ivArticleBckgrnd);

        articles.get(position).getImagePath();

    }

    @Override
    public int getItemCount() {
        return articles.size();
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
}
