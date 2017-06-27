package com.tensai.grenius.ui.home.words_all_fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tensai.grenius.R;
import com.tensai.grenius.ui.home.words_all_fragment.words_fragment.WordsFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ishitabhandari on 25/06/17.
 */

public class WordsAllAdapter extends RecyclerView.Adapter<WordsAllAdapter.ViewHolder> {
    Context ctx;
    Callback callback;
    List<String> wordlists;


    public WordsAllAdapter(Context context, Callback callback, List<String> wordlists) {
        this.ctx = context;
        this.callback = callback;
        this.wordlists = wordlists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_wordlist, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvWordlistTitle.setText("Word "+ Integer.toString(position+1));
        holder.tvWordlistDesc.setText(wordlists.get(position));
        holder.wordlist_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onEvent(1,2);
            }
        });
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
        @BindView(R.id.rl_wordlist_container)
        RelativeLayout wordlist_container;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface Callback{
        public void onEvent(int position1, int position2);
    }
}
