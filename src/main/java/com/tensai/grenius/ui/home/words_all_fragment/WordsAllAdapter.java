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
import com.tensai.grenius.model.Word;
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
    List<Word> all_words;


    public WordsAllAdapter(Context context, Callback callback, List<Word> tResults) {
        this.ctx = context;
        this.callback = callback;
        this.all_words = tResults;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_wordlist, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final int index = position*50;
        holder.tvWordlistTitle.setText("Wordlist "+ Integer.toString(position+1));
        holder.tvWordlistDesc.setText(all_words.get(index).getWord()+" - "+all_words.get(index+49).getWord());

        holder.wordlist_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClickEvent(index,index+50);
            }
        });

        holder.quiz_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClickQuiz(index);
            }
        });
    }

    @Override
    public int getItemCount() {

        return all_words.size()/50;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_wordlist_desc)
        TextView tvWordlistDesc;
        @BindView(R.id.tv_wordlist_title)
        TextView tvWordlistTitle;
        @BindView(R.id.rl_wordlist_container)
        RelativeLayout wordlist_container;
        @BindView(R.id.rl_take_quiz)
        RelativeLayout quiz_container;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface Callback{
         void onClickEvent(int position1, int position2);
         void onClickQuiz(int pos1);
    }
}
