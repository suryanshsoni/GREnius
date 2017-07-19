package com.tensai.grenius.ui.home.marked_fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.WordsFragmentAdapter;
import com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.flash_card.FlashCardActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarkedWordsFragment extends BaseFragment implements MarkedWordsView,WordsFragmentAdapter.Callback {

    @Inject
    MarkedWordsPresenter<MarkedWordsView> presenter;

    List<Word> markedwords= new ArrayList<Word>();
    protected ArrayList<Word> markedlist;
    static SharedPreferences sharedPreferences;
    @BindView(R.id.rv_wordlist)
    RecyclerView rvWordlist;
    Unbinder unbinder;

    private OnFragmentInteractionListener mListener;

    public MarkedWordsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_words, container, false);
        if (mListener != null) {
            mListener.onFragmentInteraction("Bookmarks");
        }

        getActivityComponent().inject(this);
        presenter.onAttach(this);

        unbinder = ButterKnife.bind(this, view);
        presenter.getMarkedWords();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClickWord(int position) {
        Intent intent = new Intent(getContext(), FlashCardActivity.class);
        intent.putParcelableArrayListExtra("markedlist", markedlist);
        intent.putExtra("position",position);
        startActivity(intent);
    }

    @Override
    public void setView(List<Word> list) {
        try{
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            rvWordlist.setLayoutManager(layoutManager);
            WordsFragmentAdapter wordsAdapter = new WordsFragmentAdapter(getActivity(), this , list);
            rvWordlist.setAdapter(wordsAdapter);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String title);
    }
}
