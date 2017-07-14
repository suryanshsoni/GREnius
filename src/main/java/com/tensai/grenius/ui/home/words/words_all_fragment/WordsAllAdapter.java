package com.tensai.grenius.ui.home.words.words_all_fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Word;

import java.util.List;

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
        final int index = position * 50;
        holder.tvWordlistTitle.setText("Wordlist #" + Integer.toString(position + 1));
        holder.tvWordlistDescFirst.setText(all_words.get(index).getWord() + " to " + all_words.get(index + 49).getWord());

        if (position % 4 == 0) {
            holder.wlColourLeftMargin.setBackgroundResource(R.color.wl_purple);
            holder.wlColourRightMargin.setBackgroundResource(R.color.wl_purple);
        } else if (position % 4 == 1) {
            holder.wlColourLeftMargin.setBackgroundResource(R.color.wl_green);
            holder.wlColourRightMargin.setBackgroundResource(R.color.wl_green);

        } else if (position % 4 == 2) {
            holder.wlColourLeftMargin.setBackgroundResource(R.color.wl_yellow);
            holder.wlColourRightMargin.setBackgroundResource(R.color.wl_yellow);
        } else {
            holder.wlColourLeftMargin.setBackgroundResource(R.color.wl_red);
            holder.wlColourRightMargin.setBackgroundResource(R.color.wl_red);
        }


        holder.wordlist_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClickEvent(index, index + 50);
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
        return all_words.size() / 50;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_wordlist_desc_first)
        TextView tvWordlistDescFirst;
        @BindView(R.id.tv_wordlist_title)
        TextView tvWordlistTitle;
        @BindView(R.id.wordlist_container)
        RelativeLayout wordlist_container;
        @BindView(R.id.btn_quiz)
        TextView quiz_container;
        @BindView(R.id.wl_colour_left_margin)
        View wlColourLeftMargin;
        @BindView(R.id.wl_colour_right_margin)
        View wlColourRightMargin;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface Callback {
        void onClickEvent(int position1, int position2);

        void onClickQuiz(int pos1);
    }
}
