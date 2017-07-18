package com.tensai.grenius.ui.home.words_synonym_fragement.WordSynonymSingle;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tensai.grenius.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rishabhpanwar on 16/07/17.
 */

public class WordsSynonymSingleAdapter extends RecyclerView.Adapter<WordsSynonymSingleAdapter.ViewHolder> {

    Context ctx;
    List<String> synonym,meaning;

    public WordsSynonymSingleAdapter(Context context, List<String> synonym,List<String> meaning) {
        this.ctx = context;
        this.synonym=synonym;
        this.meaning=meaning;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single_syn, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        GradientDrawable bgShape = (GradientDrawable)holder.icBullet.getBackground();

        holder.tvSynWord.setText(synonym.get(position));

        if(synonym.size()==meaning.size())
            holder.tvWordMeaning.setText(meaning.get(position));

        if (position % 4 == 0) {
            bgShape.setColor(ctx.getResources().getColor(R.color.wl_red));
        } else if (position % 4 == 1) {
            bgShape.setColor(ctx.getResources().getColor(R.color.wl_yellow));
        } else if (position % 4 == 2) {
            bgShape.setColor(ctx.getResources().getColor(R.color.wl_green));
        } else {
            bgShape.setColor(ctx.getResources().getColor(R.color.wl_purple));
        }
    }

    @Override
    public int getItemCount() {
        return synonym.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ic_bullet)
        View icBullet;
        @BindView(R.id.tv_syn_word)
        TextView tvSynWord;
        @BindView(R.id.tv_word_meaning)
        TextView tvWordMeaning;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
