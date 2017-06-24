package com.tensai.grenius.ui.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.tensai.grenius.R;
import com.tensai.grenius.ui.base.BaseActivity;
import com.tensai.grenius.ui.home.HomeActivity;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Pavilion on 22-06-2017.
 */

public class LoginActivity extends BaseActivity implements LoginView {

    @Inject
    LoginPresenter<LoginView> presenter;

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.layoutDots)
    LinearLayout layoutDots;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.btn_skip)
    Button btnSkip;

    ViewPagerAdapter viewPagerAdapter;
    TextView[] dots;
    int[] layouts;
    CallbackManager callbackManager;


    public static Intent getIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        layouts = new int[]{
                R.layout.welcome_slide1,
                R.layout.welcome_slide2,
                R.layout.welcome_slide3,
                R.layout.welcome_slide4
        };


        addBottomDots(0);
        changeStatusBarColor();

        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSkipClick();
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                presenter.onNextClick(getCurrentSlideIndex());
            }
        });

        callbackManager = CallbackManager.Factory.create();
        presenter.onAttach(LoginActivity.this);
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        layoutDots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            layoutDots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }


    public void onFbClicked(){
        presenter.onFbClicked();
    }

    public int getCurrentSlideIndex() {
        return viewPager.getCurrentItem();
    }

    @Override
    public void gotoSlide(int index) {
        viewPager.setCurrentItem(index);
    }

    @Override
    public void showNextSlide() {
        int current = getCurrentSlideIndex();
        if (current < layouts.length - 1) {
            // move to next screen
            viewPager.setCurrentItem(current+1);
            Log.i("Demo:","Change slide");
        } else {

        }
    }

    @Override
    public void openHomeActivity() {
        Log.i("Demo:","In home cativity");
        startActivity(HomeActivity.getStartIntent(LoginActivity.this));
        finish();
    }

    @Override
    public void initiateFbLogin() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email", "user_friends"));
    }

    @Override
    public void checkGooglePlayServices() {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void registerFacebookCallbackResult(FacebookCallback<LoginResult> loginResultFacebookCallback) {
        LoginManager.getInstance().registerCallback(callbackManager, loginResultFacebookCallback);
    }

    public class ViewPagerAdapter extends PagerAdapter {
        Button btn_register;
        ConstraintLayout cl_fb;
        private LayoutInflater layoutInflater;

        public ViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);


            if(position==3){
                cl_fb=(ConstraintLayout) view.findViewById(R.id.cl_fb);
                btn_register=(Button)view.findViewById(R.id.btn_register);
                cl_fb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onFbClicked();
                    }
                });
            }
            container.addView(view);

            return view;
        }


        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {

            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setText("GOT IT");
                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

}
