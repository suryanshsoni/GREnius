package com.tensai.grenius.ui.home.articles_fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.tensai.grenius.R;
import com.tensai.grenius.model.Articles;
import com.tensai.grenius.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesFragment extends BaseFragment implements ArticlesView, ArticlesAdapter.Callback{

    List<Articles> articles;

    @Inject
    ArticlesPresenter<ArticlesView> presenter;
    @BindView(R.id.rv_articles)
    RecyclerView rvArticles;
    Unbinder unbinder;

    private OnFragmentInteractionListener mListener;


    public ArticlesFragment() {
        // Required empty public constructor
    }

    public static ArticlesFragment getInstance(){
        ArticlesFragment fragment = new ArticlesFragment();
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
        if (mListener != null) {
            mListener.onFragmentInteraction("Articles");
        }
        View view = inflater.inflate(R.layout.fragment_articles, container, false);
        //BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) getActivity().findViewById(R.id.bottom_navigation);
        //bottomNavigationViewEx.setCurrentItem(4);
        getActivityComponent().inject(this);
        presenter.onAttach(this);
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvArticles.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getArticles();
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
    public void showArticles(List<Articles> articles) {
        ArticlesAdapter articlesAdapter = new ArticlesAdapter(getActivity(), articles);

        try {
            rvArticles.setAdapter(articlesAdapter);
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onArticleClick(String title, String imagePath, String desc) {
        ArticlesFragmentSingle articlesFragmentSingle = new ArticlesFragmentSingle();
        Bundle bundle = new Bundle();

        bundle.putString("title", ""+title);
        bundle.putString("imagePath",""+imagePath);
        bundle.putString("desc",""+desc);

        articlesFragmentSingle.setArguments(bundle);

        FragmentManager fragmentmanager = getActivity().getSupportFragmentManager();
        fragmentmanager.beginTransaction()
                .replace(R.id.mainFrame,articlesFragmentSingle)
                .addToBackStack(null)
                .commit();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String title);
    }
}
