package com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.flash_card;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tensai.grenius.R;
import com.tensai.grenius.view.SlideTextView;

import javax.inject.Singleton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by ishitabhandari on 24/07/17.
 */

public class TutorialDialogFragment extends DialogFragment {

    TutorialCallback tutorialCallback;

    @BindView(R.id.tv_dialog_dismiss)
    ImageView ivDialogDismiss;
    @BindView(R.id.tv_top_colour)
    TextView tvTopColour;
    @BindView(R.id.tv_bottom_colour)
    TextView tvBottomColour;
    @BindView(R.id.circle_positive)
    ImageView circlePositive;
    @BindView(R.id.circle_neutral)
    ImageView circleNeutral;
    @BindView(R.id.circle_negative)
    ImageView circleNegative;
    @BindView(R.id.tv_dialog_text)
    SlideTextView tvDialogText;
    @BindView(R.id.ll_color)
    RelativeLayout llColor;
    Unbinder unbinder;

    public TutorialDialogFragment(){
        //reqd empty constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                        Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.dialog_card_color_tutorial, new RelativeLayout(getActivity()), false);
        Log.i("Tut:","in dialog fragment ");
        final Dialog dialog = getDialog();
        final Window window = dialog.getWindow();
        WindowManager.LayoutParams wmlp = getDialog().getWindow().getAttributes();
        wmlp.gravity = Gravity.CENTER_HORIZONTAL;
        dialog.setTitle(getString(R.string.tutorial_color_title));
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        Log.i("Tut","dialog build");
        dialog.setCanceledOnTouchOutside(false);

        unbinder=ButterKnife.bind(this, view);

        // Build dialog
       // final Dialog builder = new Dialog(getActivity());
        //builder.setTitle(getString(R.string.tutorial_color_title));
        Log.i("Tut","dialog build2");
        tvTopColour.setBackgroundColor(getResources().getColor(R.color.positive_bg));
        tvBottomColour.setBackgroundColor(getResources().getColor(R.color.positive_bg));
        tvDialogText.setText(getResources().getString(R.string.tutorial_color_blue));


        /// builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        //builder.setContentView(view);

        ivDialogDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                tutorialCallback.dialogDismissed();
            }
        });
        return view;

    }


    /*public static TutorialDialogFragment newInstance() {
        TutorialDialogFragment f = new TutorialDialogFragment();
        return f;
    }*/

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        tutorialCallback = (TutorialCallback) activity;
    }

    @Override
    public void onDetach() {
        tutorialCallback = null;
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.circle_positive, R.id.circle_neutral, R.id.circle_negative})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.circle_positive:
                tvTopColour.setBackgroundColor(getResources().getColor(R.color.positive_bg));
                tvBottomColour.setBackgroundColor(getResources().getColor(R.color.positive_bg));
                tvDialogText.setText(getResources().getString(R.string.tutorial_color_blue));
                break;
            case R.id.circle_neutral:
                tvTopColour.setBackgroundColor(getResources().getColor(R.color.neutral_bg));
                tvBottomColour.setBackgroundColor(getResources().getColor(R.color.neutral_bg));
                tvDialogText.setText(getResources().getString(R.string.tutorial_color_yellow));
                break;
            case R.id.circle_negative:
                tvTopColour.setBackgroundColor(getResources().getColor(R.color.negative_bg));
                tvBottomColour.setBackgroundColor(getResources().getColor(R.color.negative_bg));
                tvDialogText.setText(getResources().getString(R.string.tutorial_color_red));
                break;
        }
    }


    public interface TutorialCallback {
        void dialogDismissed();
    }
}
