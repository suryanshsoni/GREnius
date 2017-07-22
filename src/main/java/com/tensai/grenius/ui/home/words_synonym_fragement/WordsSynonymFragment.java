package com.tensai.grenius.ui.home.words_synonym_fragement;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Category;
import com.tensai.grenius.ui.base.BaseFragment;
import com.tensai.grenius.ui.home.words_synonym_fragement.WordSynonymSingle.WordsSynonymFragmentSingle;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordsSynonymFragment extends BaseFragment implements WordsSynonymView, WordsSynonymAdapter.Callback{

    @Inject
    WordsSynonymPresenter<WordsSynonymView> presenter;
    @BindView(R.id.rv_categories)
    RecyclerView rvCategories;
    Unbinder unbinder;

    private OnFragmentInteractionListener mListener;


    public WordsSynonymFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (mListener != null) {
            mListener.onFragmentInteraction("Categories");
        }
        View view = inflater.inflate(R.layout.fragment_words_synonym, container, false);
        getActivityComponent().inject(this);
        presenter.onAttach(this);
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvCategories.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void showCategories(List<Category> categories) {
        WordsSynonymAdapter wordsSynonymAdapter=new WordsSynonymAdapter(getActivity(),this,categories);
        try {
            rvCategories.setAdapter(wordsSynonymAdapter);
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.getCategories();
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
    public void onCategoryClicked(String sno, String category, String synonym, String meaning) {
        WordsSynonymFragmentSingle wordsSynonymFragmentSingle = new WordsSynonymFragmentSingle();
        Bundle bundle = new Bundle();

        bundle.putString("sno", ""+sno);
        bundle.putString("category",""+category);
        bundle.putString("synonym",""+synonym);
        bundle.putString("meaning",""+meaning);

        wordsSynonymFragmentSingle.setArguments(bundle);

        FragmentManager fragmentmanager = getActivity().getSupportFragmentManager();
        fragmentmanager.beginTransaction()
                .replace(R.id.mainFrame, wordsSynonymFragmentSingle)
                .addToBackStack(null)
                .commit();
        mListener.pushCategoryOntoStack();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String title);
        void pushCategoryOntoStack();
    }
}
