package com.tensai.grenius.ui.home.marked_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.BaseActivity;
import com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.WordsFragmentAdapter;
import com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.flash_card.FlashCardActivity;
import com.tensai.grenius.view.SlideTextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarkedWordsActivity extends BaseActivity implements MarkedWordsView, WordsFragmentAdapter.Callback {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_marked_words)
    SlideTextView tvMarkedWords;
    @BindView(R.id.rv_wordlist)
    RecyclerView rvWordlist;

    protected ArrayList<Word> markedlist;

    @Inject
    MarkedWordsPresenter<MarkedWordsView> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marked_words);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        getActivityComponent().inject(this);
        presenter.onAttach(this);

        markedlist = (ArrayList<Word>) presenter.getMarkedWords();
        if (markedlist !=null && markedlist.size()!=0){
            setListView();
        }else {
            setView();
        }
    }

    @Override
    public void setListView() {
        try {
            Log.i("Mark:","IN setListview");
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            rvWordlist.setLayoutManager(layoutManager);
            WordsFragmentAdapter wordsAdapter = new WordsFragmentAdapter(this, this, markedlist);
            rvWordlist.setAdapter(wordsAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setView() {
        Log.i("Mark:","IN else");
        tvMarkedWords.setText("Oops! Looks like you haven't practiced enough!");
        rvWordlist.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClickWord(int position) {
        Intent intent = new Intent(this, FlashCardActivity.class);
        intent.putParcelableArrayListExtra("wordlist", markedlist);
        intent.putExtra("position", position);
        startActivity(intent);

    }
}
