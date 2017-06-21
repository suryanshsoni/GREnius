package com.tensai.grenius.ui.base;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.tensai.grenius.R;
import com.tensai.grenius.util.MenuComponent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Pavilion on 21-06-2017.
 */

public class MenuFragment extends DialogFragment {
    @BindView(R.id.button_container)
    LinearLayout menuLayout;

    private List<MenuComponent> menuComponents;
    private MenuCallback menuCallback;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Menu Fragment", "OnCreate");
    }

    private android.os.Handler handler = new android.os.Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wip, container);
        Log.d("Menu Fragment", "OnCreateView");
        Dialog dialog = getDialog();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        final Window window = dialog.getWindow();

        // set "origin" to top left corner, so to speak
        window.setGravity(Gravity.TOP|Gravity.START);

        // after that, setting values for x and y works "naturally"
        DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;
        Log.d("height", "Hei" + height);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams wmlp = getDialog().getWindow().getAttributes();
        wmlp.gravity = Gravity.FILL_HORIZONTAL;
        ButterKnife.bind(this, view);
        Bundle args = getArguments();

        if(menuComponents != null){
            addButtons();
        }
        return view;
    }


    @OnClick(R.id.close_btn)
    void closeRequest(View v){
        dismiss();
    }
    private void addButtons(){
        for(final MenuComponent mc: menuComponents){
            LinearLayout.LayoutParams lprams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            Button tv1 = new Button(getActivity());
            tv1.setText(mc.label);
            tv1.setId(mc.id);
            lprams.setMargins(0,20,0,20);
            tv1.setTextColor(Color.parseColor("#757575"));
            tv1.setBackgroundResource(R.drawable.menu_button_selector);
            tv1.setLayoutParams(lprams);

            if(menuCallback != null){
                tv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        menuCallback.onClick(mc);
                        dismiss();
                    }
                });
            }
            menuLayout.addView(tv1, lprams);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setWindowAnimations(R.style.MenuAnimation);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    public static MenuFragment getInstance(){
        Bundle args = new Bundle();
        MenuFragment fragment = new MenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void setMenuComponentList(List<MenuComponent> menuComponents){
        this.menuComponents = menuComponents;

    }


    public void setMenuCallback(MenuCallback callback){
        this.menuCallback = callback;
    }
    public interface MenuCallback{
        public void onClick(MenuComponent mc);
    }
}
