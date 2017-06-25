package com.tensai.grenius.ui.home.words_all_fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tensai.grenius.R;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ishitabhandari on 25/06/17.
 */

public class WordsAllAdapter extends RecyclerView.Adapter<WordsAllAdapter.ViewHolder> {
    Context ctx;
    List<String> wordlists;


    public WordsAllAdapter(Context context, List<String> wordlists) {
        this.ctx = context;
        this.wordlists = wordlists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_wordlist, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvWordlistTitle.setText("Word "+ Integer.toString(position+1));
        holder.tvWordlistDesc.setText(wordlists.get(position));
    }

    @Override
    public int getItemCount() {
        return wordlists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_wordlist_desc)
        TextView tvWordlistDesc;
        @BindView(R.id.tv_wordlist_title)
        TextView tvWordlistTitle;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
