package com.tensai.grenius.ui.home.institutes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Institute;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class GREIntFragment extends Fragment {

    @BindView(R.id.rv_gre_institute)
    RecyclerView rvGreInstitute;
    Unbinder unbinder;

    private OnFragmentInteractionListener mListener;

    ArrayList<Institute> institutes;
    ArrayList<Institute> trunc_institutes= new ArrayList<>();

    public GREIntFragment() {
        // Required empty public constructor
    }

    public static GREIntFragment newInstance(String param1, String param2) {
        GREIntFragment fragment = new GREIntFragment();
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
        View view = inflater.inflate(R.layout.fragment_insti_gre, container, false);
        Bundle args = getArguments();
        institutes = args.getParcelableArrayList("institutelist");
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvGreInstitute.setLayoutManager(layoutManager);

        for(int i=0; i<institutes.size();i++){
            if(institutes.get(i).getType().equalsIgnoreCase("GRE")){
               trunc_institutes.add(institutes.get(i));
            }
        }

        InstituteAdapter instituteAdapter = new InstituteAdapter(getActivity(), trunc_institutes);

        try {
            rvGreInstitute.setAdapter(instituteAdapter);
        }
        catch (NullPointerException e){
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
