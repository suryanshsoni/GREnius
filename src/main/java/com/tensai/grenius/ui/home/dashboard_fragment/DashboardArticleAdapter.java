package com.tensai.grenius.ui.home.dashboard_fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tensai.grenius.R;
import com.tensai.grenius.model.Articles;
import com.tensai.grenius.ui.home.articles_fragment.ArticleSingleActivity;
import com.tensai.grenius.view.SlideTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tensai.grenius.util.AppConstants.API_BASE_URL;

/**
 * Created by rishabhpanwar on 24/07/17.
 */

public class DashboardArticleAdapter extends RecyclerView.Adapter<DashboardArticleAdapter.ViewHolder> {

    Context ctx;
    List<Articles> articlesList;
    Callback callback;
    private String imagePath,title_text;


    public DashboardArticleAdapter(Context context, List<Articles> articlesList, Callback callback) {
        this.ctx = context;
        this.articlesList=articlesList;
        this.callback=callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_article_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        title_text = articlesList.get(position).getTitle();

        imagePath = API_BASE_URL + articlesList.get(position).getImagePath();
        holder.tvArticleTitles.setText(title_text);

        Picasso.with(ctx)
                .load(imagePath)
                .into(holder.ivDashboardArticleBckgrnd);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ctx, ArticleSingleActivity.class);
                intent.putExtra("title",articlesList.get(position).getTitle());
                intent.putExtra("imagePath",""+imagePath);
                intent.putExtra("desc",""+articlesList.get(position).getDesc());
                ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        int size =0;
        try{
           size = articlesList.size();
        }catch (Exception e){
            e.printStackTrace();
        }
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_dashboard_article_bckgrnd)
        ImageView ivDashboardArticleBckgrnd;
        @BindView(R.id.tv_article_titles)
        SlideTextView tvArticleTitles;
        @BindView(R.id.card_view)
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface Callback{
        String capitalize(String text);
    }
}
