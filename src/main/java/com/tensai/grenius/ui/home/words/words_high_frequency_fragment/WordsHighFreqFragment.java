package com.tensai.grenius.ui.home.words.words_high_frequency_fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.BaseFragment;
import com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.WordsFragmentAdapter;
import com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.flash_card.FlashCardActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WordsHighFreqFragment extends BaseFragment implements WordsHighFreqView, WordsFragmentAdapter.Callback {

    @Inject
    WordsHighFreqPresenter<WordsHighFreqView> presenter;
    @BindView(R.id.rv_highfrequency)
    RecyclerView rvHighfrequency;
    ArrayList<Word> freqWords;
    Unbinder unbinder;

    public WordsHighFreqFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_words_high_freq, container, false);
        getActivityComponent().inject(this);
        presenter.onAttach(this);
        presenter.getHighFrequentWords();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void showWords(List<Word> fWords) {

        freqWords=(ArrayList<Word>)fWords;
        Log.i("Demo:",fWords.toString());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        try {
            rvHighfrequency.setLayoutManager(layoutManager);
            WordsFragmentAdapter wordsAdapter = new WordsFragmentAdapter(getActivity(), this , fWords);
            rvHighfrequency.setAdapter(wordsAdapter);
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClickWord(int position) {

        Intent intent = new Intent(getContext(), FlashCardActivity.class);
        intent.putParcelableArrayListExtra("wordlist", freqWords);
        intent.putExtra("position",position);
        startActivity(intent);
    }
}
