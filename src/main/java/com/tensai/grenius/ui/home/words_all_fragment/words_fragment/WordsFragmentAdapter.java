package com.tensai.grenius.ui.home.words_all_fragment.words_fragment;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Word;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ishitabhandari on 29/06/17.
 */

public class WordsFragmentAdapter extends RecyclerView.Adapter<WordsFragmentAdapter.ViewHolder> {
     Context ctx;
    ArrayList<Word> wordlist;

    public WordsFragmentAdapter(Context context, ArrayList<Word> wordlist){
        this.ctx = context;
        this.wordlist = wordlist;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_words, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_word_sno.setText(Integer.toString(position+1)+".");
        holder.tv_word_title.setText(wordlist.get(position).getWord());
        holder.tv_word_pos.setText("("+wordlist.get(position).getPos()+")");
        holder.ll_word_container.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //callback
            }
        });
    }

    @Override
    public int getItemCount() {
        return wordlist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_word_sno)
        TextView tv_word_sno;
        @BindView(R.id.tv_word_title)
        TextView tv_word_title;
        @BindView(R.id.tv_word_pos)
        TextView tv_word_pos;
        @BindView(R.id.ll_word_container)
        LinearLayout ll_word_container;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface Callback{
        void onClickWord(int position);
    }
}
