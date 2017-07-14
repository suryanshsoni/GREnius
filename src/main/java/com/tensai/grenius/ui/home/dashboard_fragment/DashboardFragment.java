package com.tensai.grenius.ui.home.dashboard_fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;
import com.tensai.grenius.R;
import com.tensai.grenius.model.Articles;
import com.tensai.grenius.ui.base.BaseFragment;
import com.tensai.grenius.util.ScreenUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.os.Looper.getMainLooper;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends BaseFragment implements DashboardView {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    @Inject
    DashboardPresenter<DashboardView> presenter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.articlesView)
    PlaceHolderView articlesView;
    Unbinder unbinder;



    private OnFragmentInteractionListener mListener;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mListener != null) {
            mListener.onFragmentInteraction("Home");
        }
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        getActivityComponent().inject(this);
        presenter.onAttach(this);
        unbinder = ButterKnife.bind(this, view);
        articlesView.addView(new WordOfDay());
        articlesView.setNestedScrollingEnabled(false);
        presenter.getDashboardArticles();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void showDashboardArticles(List<Articles> articlesList) {
        try{
            for (Articles articles : articlesList) {
                if (articles != null) {
                    articlesView.addView(new ArticlesDashboard(getContext(), articles));
                }
            }

        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String title);
    }
}
