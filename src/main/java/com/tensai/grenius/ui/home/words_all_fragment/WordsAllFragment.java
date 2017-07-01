package com.tensai.grenius.ui.home.words_all_fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.BaseFragment;
import com.tensai.grenius.ui.home.quiz_fragment.QuizFragment;
import com.tensai.grenius.ui.home.words_all_fragment.words_fragment.WordsFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordsAllFragment extends BaseFragment implements WordsAllView, WordsAllAdapter.Callback {

    @Inject
    WordsAllPresenter<WordsAllView> presenter;

    @BindView(R.id.rv_all_wordlists)
    RecyclerView rv_all_wordlists;
    Unbinder unbinder;

    public WordsAllFragment() {
        // Required empty public constructor
    }

    public static WordsAllFragment getInstance(){
        WordsAllFragment fragment = new WordsAllFragment();
        return fragment;
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
        presenter.onAttach(this);
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv_all_wordlists.setLayoutManager(layoutManager);
        presenter.getAllWords();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @Override
    public void setPresenter(WordsAllPresenter<WordsAllView> presenter){
        this.presenter=presenter;

    }

    @Override
    public void showWordlists(List<Word> tResult) {
        WordsAllAdapter wordsAllAdapter = new WordsAllAdapter(getActivity(),this, tResult);
        rv_all_wordlists.setAdapter(wordsAllAdapter);
    }

    @Override
    public void onClickEvent(int position1, int position2) {
       Bundle args = new Bundle();
        ArrayList<Word> wordlist = presenter.getWordlist(position1, position2);
        args.putParcelableArrayList("wordlist", wordlist);
        WordsFragment wordsFragment = new WordsFragment();
        wordsFragment.setArguments(args);
        android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                       .replace(R.id.mainFrame, wordsFragment)
                       .commit();
    }

    @Override
    public void onClickQuiz(int pos1) {
        Bundle quiz = new Bundle();
        quiz.putInt("position",pos1);
        QuizFragment quizFragment = new QuizFragment();
        quizFragment.setArguments(quiz);
        android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainFrame, quizFragment)
                .commit();
    }
}
