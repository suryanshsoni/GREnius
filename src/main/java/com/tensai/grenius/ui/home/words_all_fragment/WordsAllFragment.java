package com.tensai.grenius.ui.home.words_all_fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tensai.grenius.R;
import com.tensai.grenius.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordsAllFragment extends BaseFragment implements WordsAllView {
    List<String> wordlists;

    @Inject
    WordsAllPresenter<WordsAllView> presenter;

    @BindView(R.id.rv_wordlist)
    RecyclerView rv_wordlist;
    Unbinder unbinder;

    public WordsAllFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_words_all, container, false);
        getActivityComponent().inject(this);
        unbinder = ButterKnife.bind(this, view);
        presenter.onAttach(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv_wordlist.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getWordlist();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setPresenter(WordsAllPresenter<WordsAllView> presenter){
        this.presenter=presenter;
    }

    @Override
    public void showWordlists(List<String> wordlists) {
        Log.i("Demo:", wordlists.toString());
        WordsAllAdapter wordsAllAdapter = new WordsAllAdapter(getActivity(), wordlists);
        rv_wordlist.setAdapter(wordsAllAdapter);
    }
}
