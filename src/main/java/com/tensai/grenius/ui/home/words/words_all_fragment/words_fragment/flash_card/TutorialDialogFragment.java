package com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.flash_card;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tensai.grenius.R;
import com.tensai.grenius.view.SlideTextView;

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

    public TutorialDialogFragment() {
        //reqd empty constructor
    }

    public TutorialDialogFragment(TutorialCallback tcallback) {
        this.tutorialCallback = tcallback;
    }

    private Handler handler = new Handler();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_card_color_tutorial, new RelativeLayout(getActivity()), false);
        unbinder = ButterKnife.bind(this, view);

        // Build dialog
        final Dialog builder = new Dialog(getActivity());
        builder.setTitle(getString(R.string.tutorial_color_title));

        tvTopColour.setBackgroundColor(getResources().getColor(R.color.positive_bg));
        tvBottomColour.setBackgroundColor(getResources().getColor(R.color.positive_bg));
        tvDialogText.setText(getResources().getString(R.string.tutorial_color_blue));


        /// builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setContentView(view);

        ivDialogDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                tutorialCallback.DialogDismissed();
            }
        });

        return builder;
    }

    public static TutorialDialogFragment newInstance() {
        TutorialDialogFragment f = new TutorialDialogFragment();
        return f;
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
        void DialogDismissed();
    }
}
