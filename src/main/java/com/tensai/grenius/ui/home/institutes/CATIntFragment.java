package com.tensai.grenius.ui.home.institutes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Institute;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CATIntFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CATIntFragment extends Fragment {

    @BindView(R.id.rv_cat_institute)
    RecyclerView rvCatInstitute;
    Unbinder unbinder;

    ArrayList<Institute> institutes;
    //ArrayList<Institute> trunc_institutes= new ArrayList<>();

    private OnFragmentInteractionListener mListener;

    public CATIntFragment() {
        // Required empty public constructor
    }

    public static CATIntFragment newInstance(String param1, String param2) {
        CATIntFragment fragment = new CATIntFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insti_cat, container, false);
        Bundle args = getArguments();
        institutes = args.getParcelableArrayList("institutelist");
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvCatInstitute.setLayoutManager(layoutManager);

        InstituteAdapter instituteAdapter = new InstituteAdapter(getActivity(), institutes);

        try {
            rvCatInstitute.setAdapter(instituteAdapter);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
