package com.tensai.grenius.ui.home.words_synonym_fragement;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Articles;
import com.tensai.grenius.model.Category;
import com.tensai.grenius.view.SlideTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rishabhpanwar on 13/07/17.
 */

public class WordsSynonymAdapter extends RecyclerView.Adapter<WordsSynonymAdapter.ViewHolder> {

    Context ctx;
    List<Category> categories;
    Callback callback;

    public WordsSynonymAdapter(Context context,Callback callback, List<Category> categories) {
        this.ctx = context;
        this.callback=callback;
        this.categories = categories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_synonyms, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tvWord.setText(categories.get(position).getCategory());

        if (position % 4 == 0) {
            holder.wlColourLeftMargin.setBackgroundResource(R.color.wl_red);
            holder.wlColourRightMargin.setBackgroundResource(R.color.wl_red);
        } else if (position % 4 == 1) {
            holder.wlColourLeftMargin.setBackgroundResource(R.color.wl_yellow);
            holder.wlColourRightMargin.setBackgroundResource(R.color.wl_yellow);
        } else if (position % 4 == 2) {
            holder.wlColourLeftMargin.setBackgroundResource(R.color.wl_green);
            holder.wlColourRightMargin.setBackgroundResource(R.color.wl_green);
        } else {
            holder.wlColourLeftMargin.setBackgroundResource(R.color.wl_purple);
            holder.wlColourRightMargin.setBackgroundResource(R.color.wl_purple);
        }

        holder.cardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onCategoryClicked(categories.get(position).getSno(),
                        categories.get(position).getCategory(),
                        categories.get(position).getSynonym(),
                        categories.get(position).getMeaning());
            }
        });

    }

    @Override
    public int getItemCount() {
        try{
            return categories.size();
        }catch (Exception e){
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.wl_colour_left_margin)
        View wlColourLeftMargin;
        @BindView(R.id.tv_word)
        SlideTextView tvWord;
        @BindView(R.id.wl_colour_right_margin)
        View wlColourRightMargin;
        @BindView(R.id.card_layout)
        CardView cardLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface Callback{
        public void onCategoryClicked(String sno, String category, String synonym, String meaning);
    }
}
