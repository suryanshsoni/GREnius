package com.tensai.grenius.ui.base;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;
import com.tensai.grenius.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Pavilion on 21-06-2017.
 */

public class PhotoViewFragment extends DialogFragment {
    private static final String ARG_URL = "ARG_URL";

    @BindView(R.id.photo_view)
    PhotoView photoView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("PhotoView Fragment", "OnCreate");
    }

    private android.os.Handler handler = new android.os.Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photo_view, container);
        Log.d("Menu Fragment", "OnCreateView");
        Dialog dialog = getDialog();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        final Window window = dialog.getWindow();

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        WindowManager.LayoutParams wmlp = getDialog().getWindow().getAttributes();
        wmlp.gravity = Gravity.FILL_HORIZONTAL;
        ButterKnife.bind(this, view);
        Bundle args = getArguments();
        if(args != null){
            String url = args.getString(ARG_URL);
            Picasso.with(getActivity()).load(url)
                    .into(photoView);

        }
        return view;
    }

    public static PhotoViewFragment getInstance(String url){
        Bundle args = new Bundle();
        args.putString(ARG_URL, url);
        PhotoViewFragment fragment = new PhotoViewFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
