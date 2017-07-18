package com.tensai.grenius.ui.home.words_synonym_fragement.WordSynonymSingle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tensai.grenius.R;
import com.tensai.grenius.ui.base.BaseFragment;
import com.tensai.grenius.ui.home.words_synonym_fragement.WordSynonymSingle.WordsSynonymSingleAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordsSynonymFragmentSingle extends Fragment {

    String sno, category, synonym, meaning;
    List<String> syn_list, mean_list;
    Unbinder unbinder;

    @BindView(R.id.rv_synonyms)
    RecyclerView rvSynonyms;

    WordsSynonymSingleAdapter wordsSynonymSingleAdapter;


    public WordsSynonymFragmentSingle() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_words_synonym_fragment_single, container, false);
        unbinder = ButterKnife.bind(this, view);

        Bundle args = getArguments();
        sno = args.getString("sno");
        category = args.getString("category");
        synonym = args.getString("synonym");
        meaning = args.getString("meaning");

        syn_list = new ArrayList<String>(Arrays.asList(synonym.split(",")));
        mean_list = new ArrayList<String>(Arrays.asList(meaning.split(",")));

        //tvSynTitle.setText(category);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvSynonyms.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("ABC:","In resume");
        wordsSynonymSingleAdapter=new WordsSynonymSingleAdapter(getActivity(),syn_list,mean_list);
        rvSynonyms.setAdapter(wordsSynonymSingleAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
