package com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.flash_card;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tensai.grenius.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ishitabhandari on 24/07/17.
 */

public class TutorialDialogFragment extends DialogFragment {

    TutorialCallback tutorialCallback;

    @BindView(R.id.tv_dialog_dismiss)
    TextView tvDialogDismiss;
    Unbinder unbinder;

    public TutorialDialogFragment(){
        //reqd empty constructor
    }

    public TutorialDialogFragment(TutorialCallback tcallback){
        this.tutorialCallback = tcallback;
    }

    private Handler handler = new Handler();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_card_color_tutorial, new RelativeLayout(getActivity()), false);
        unbinder=ButterKnife.bind(this, view);

        // Build dialog
        final Dialog builder = new Dialog(getActivity());
        builder.setTitle(getString(R.string.tutorial_color_title));
       /// builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setContentView(view);

        tvDialogDismiss.setOnClickListener(new View.OnClickListener() {
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

    public interface TutorialCallback {
        void DialogDismissed();
    }
}
