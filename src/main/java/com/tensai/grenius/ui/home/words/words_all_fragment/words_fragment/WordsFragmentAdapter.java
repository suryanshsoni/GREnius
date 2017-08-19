package com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.view.SlideTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ishitabhandari on 29/06/17.
 */

public class WordsFragmentAdapter extends RecyclerView.Adapter<WordsFragmentAdapter.ViewHolder> {
    Context ctx;
    List<Word> wordlist;
    Callback callback;

    public WordsFragmentAdapter(Context context, Callback callback, List<Word> wordlist) {
        this.ctx = context;
        this.wordlist = wordlist;
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_words, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

            holder.tv_word_sno.setText(Integer.toString(position + 1) + ".");
            holder.tv_word_title.setText(callback.capitalize(wordlist.get(position).getWord()));

        /*switch (wordlist.get(position).getPos()) {

            case "A":
                holder.tv_word_pos.setText("(" + ctx.getResources().getString(R.string.adjective) + ")");
                break;
            case "N":
                holder.tv_word_pos.setText("(" + ctx.getResources().getString(R.string.noun) + ")");
                break;
            case "V":
                holder.tv_word_pos.setText("(" + ctx.getResources().getString(R.string.verb) + ")");
                break;
            default:
                holder.tv_word_pos.setText("(" + wordlist.get(position).getPos() + ")");

        }*/

            holder.tvWordMeaning.setText("-"+wordlist.get(position).getMeaning());
            holder.ll_word_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClickWord(position);
                }
            });

    }

    @Override
    public int getItemCount() {
        return wordlist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_word_sno)
        SlideTextView tv_word_sno;
        @BindView(R.id.tv_word_title)
        SlideTextView tv_word_title;
        @BindView(R.id.tv_word_pos)
        SlideTextView tv_word_pos;
        @BindView(R.id.ll_word_container)
        LinearLayout ll_word_container;
        @BindView(R.id.tv_word_meaning)
        SlideTextView tvWordMeaning;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface Callback {
        void onClickWord(int position);
        String capitalize(String text);
    }
}
