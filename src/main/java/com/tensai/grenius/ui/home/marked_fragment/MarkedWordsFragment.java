package com.tensai.grenius.ui.home.marked_fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class MarkedWordsFragment extends BaseFragment implements MarkedWordsView, WordsFragmentAdapter.Callback {

    @Inject
    MarkedWordsPresenter<MarkedWordsView> presenter;
    @BindView(R.id.tv_marked_words)
    TextView tvMarkedWords;

    private OnFragmentInteractionListener mListener;
    protected ArrayList<Word> markedlist;
    @BindView(R.id.rv_wordlist)
    RecyclerView rvWordlist;
    Unbinder unbinder;


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
        View view = inflater.inflate(R.layout.fragment_marked_words, container, false);
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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClickWord(int position) {
        Intent intent = new Intent(getContext(), FlashCardActivity.class);
        intent.putParcelableArrayListExtra("markedlist", markedlist);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
    public void setView(List<Word> list) {
        try {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            rvWordlist.setLayoutManager(layoutManager);
            WordsFragmentAdapter wordsAdapter = new WordsFragmentAdapter(getActivity(), this, list);
            rvWordlist.setAdapter(wordsAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setView() {
        rvWordlist.setVisibility(View.INVISIBLE);
        tvMarkedWords.setText("No Marked Words!");
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String title);
    }
}
