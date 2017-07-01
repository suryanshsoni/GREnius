package com.tensai.grenius.ui.home.words_all_fragment.words_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordsFragment extends BaseFragment implements WordsView {
    @BindView(R.id.rv_wordlist)
    RecyclerView rv_Wordlist;
    Unbinder unbinder;

    ArrayList<Word> wordlist;


    @Inject
    WordsPresenter<WordsView> presenter;


    public WordsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_words, container, false);
        getActivityComponent().inject(this);
        presenter.onAttach(this);
        unbinder = ButterKnife.bind(this, view);
        Bundle args = getArguments();
        wordlist = args.getParcelableArrayList("wordlist");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv_Wordlist.setLayoutManager(layoutManager);
        WordsFragmentAdapter wordsAdapter = new WordsFragmentAdapter(getActivity(),wordlist);
        rv_Wordlist.setAdapter(wordsAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
