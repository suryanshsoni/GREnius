package com.tensai.grenius.ui.home.quiz_fragment;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.tensai.grenius.R;
import com.tensai.grenius.ui.home.HomeActivity;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizCallerFragment extends DialogFragment implements AdapterView.OnItemSelectedListener {


    @BindView(R.id.btn_start)
    Button btnStart;
    ArrayList<String> SPINNERLIST = new ArrayList<String>();
    @BindView(R.id.android_material_design_spinner)
    MaterialBetterSpinner materialDesignSpinner;
    int choice = -1;
    @BindView(R.id.close_dialog)
    ImageView closeDialog;

    private Callback mListener;
    public QuizCallerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz_caller, container, false);
        final Dialog dialog = getDialog();

        dialog.setCanceledOnTouchOutside(false);
        final Window window = dialog.getWindow();


        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        WindowManager.LayoutParams wmlp = getDialog().getWindow().getAttributes();
        wmlp.gravity = Gravity.CENTER_HORIZONTAL;
        ButterKnife.bind(this, view);

        SPINNERLIST.add("All");
        int words=mListener.getWordCount();
        int count=words/50;

        Log.d("Demo","Word Count"+count);
        for (int i = 1; i <= count; i++) {
            SPINNERLIST.add(String.valueOf(i));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        materialDesignSpinner.setAdapter(arrayAdapter);
        materialDesignSpinner.setOnItemSelectedListener(this);

        closeDialog.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                dialog.cancel();
                //BottomNavigationView bnv=(BottomNavigationView) getActivity().findViewById(R.id.bottom_navigation);
                //bnv.
                mListener.checkBackStackOnQuizClose();
            }
        });
        return view;
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        Log.d("Demo", choice + " Here choice");
        String opt = (String) parent.getItemAtPosition(pos);
        if (opt.equalsIgnoreCase("All")) {
            choice = -1;
        } else
            choice = Integer.parseInt(opt);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        choice = -1;
    }

    @OnClick(R.id.btn_start)
    public void onViewClicked() {
        Bundle quiz = new Bundle();
        try {
            Editable e = materialDesignSpinner.getText();
            String opt = e.toString();
            if (opt.equalsIgnoreCase("All")) {
                choice = -1;
            } else
                choice = (Integer.parseInt(opt) - 1) * 50;
        } catch (Exception e) {
            choice = -1;
            System.out.print(e.toString());
        }
        Log.d("Demo", choice + " choice");
        quiz.putInt("position", choice);
        mListener.callQuiz(quiz);

        getDialog().cancel();
    }

    @Override
    public void onAttach(Activity activity) {
        mListener = (Callback) activity;
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        mListener.checkBackPressedOnQuiz();
        mListener = null;
        super.onDetach();

    }
    public interface Callback{
        void checkBackStackOnQuizClose();
        void checkBackPressedOnQuiz();
        void callQuiz(Bundle quiz);
        int getWordCount();
    }
}
