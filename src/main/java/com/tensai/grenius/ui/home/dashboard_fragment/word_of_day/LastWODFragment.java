package com.tensai.grenius.ui.home.dashboard_fragment.word_of_day;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.tensai.grenius.R;
import com.tensai.grenius.model.WordOfDay;
import com.tensai.grenius.ui.base.BaseFragment;
import com.tensai.grenius.view.SlideTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class LastWODFragment extends BaseFragment {

    WordOfDay wordObj;

    @BindView(R.id.tv_word_date)
    SlideTextView tvWordDate;
    @BindView(R.id.rl_date)
    RelativeLayout rlDate;
    @BindView(R.id.tv_word_word)
    SlideTextView tvWordWord;
    @BindView(R.id.tv_word_pos)
    SlideTextView tvWordPos;
    @BindView(R.id.cv_word_bckgrnd)
    CardView cvWordBckgrnd;
    @BindView(R.id.tv_desc_meaning)
    SlideTextView tvDescMeaning;
    @BindView(R.id.tv_word_meaning)
    SlideTextView tvWordMeaning;
    @BindView(R.id.tv_desc_synonym)
    SlideTextView tvDescSynonym;
    @BindView(R.id.tv_word_synonym)
    SlideTextView tvWordSynonym;
    @BindView(R.id.tv_desc_example)
    SlideTextView tvDescExample;
    @BindView(R.id.tv_word_example)
    SlideTextView tvWordExample;
    Unbinder unbinder;


    OnFragmentInteractionListener mListener;

    public LastWODFragment() {
        // Required empty public constructor
    }

    static LastWODFragment show(WordOfDay object) {
        LastWODFragment subFragment = new LastWODFragment();
        Bundle args = new Bundle();
        args.putParcelable("wordObject", object);
        subFragment.setArguments(args);
        return subFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            wordObj = getArguments().getParcelable("wordObject");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_last_wod, container, false);
        unbinder = ButterKnife.bind(this, view);
        setView(wordObj);

        /*btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonLeft();
            }
        });*/
        return view;
    }

    public void setView(WordOfDay wordOfDay) {

        try {
            tvWordDate.setText(wordOfDay.getDate().split("T")[0]);
            tvWordWord.setText(capitalize(wordOfDay.getWord()));
            tvWordMeaning.setText(capitalize(wordOfDay.getMeaning()));
            tvWordSynonym.setText(wordOfDay.getSynonym());
            tvWordExample.setText(wordOfDay.getExample());
            tvWordPos.setText(wordOfDay.getPos());
        } catch (Exception e) {
            e.printStackTrace();
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
        void onButtonLeft();
    }
}
