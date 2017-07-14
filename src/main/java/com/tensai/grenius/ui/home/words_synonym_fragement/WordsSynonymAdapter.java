package com.tensai.grenius.ui.home.words_synonym_fragement;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tensai.grenius.R;

import butterknife.BindView;

/**
 * Created by rishabhpanwar on 13/07/17.
 */

public class WordsSynonymAdapter extends RecyclerView.Adapter<WordsSynonymAdapter.ViewHolder> {

    public WordsSynonymAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_synonyms, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


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

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.wl_colour_left_margin)
        View wlColourLeftMargin;
        @BindView(R.id.tv_word)
        TextView tvWord;
        @BindView(R.id.wl_colour_right_margin)
        View wlColourRightMargin;
        @BindView(R.id.card_layout)
        CardView cardLayout;


        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
