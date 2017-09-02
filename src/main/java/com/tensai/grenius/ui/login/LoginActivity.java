package com.tensai.grenius.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.facebook.CallbackManager;
import com.tensai.grenius.R;
import com.tensai.grenius.ui.base.BaseActivity;
import com.tensai.grenius.ui.home.HomeActivity;
import com.tensai.grenius.ui.login.login_page.forgot_pwd.ForgotPwdActivity;
import com.tensai.grenius.ui.login.login_page.LoginFragment;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Pavilion on 22-06-2017.
 */

public class LoginActivity extends BaseActivity implements LoginView, WelcomeFragment.OnFragmentInteractionListener, RegistrationFragment.OnFragmentInteractionListener, LoginFragment.OnFragmentInteractionListener {

    @Inject
    LoginPresenter<LoginView> presenter;


    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;

    FragmentManager fragmentManager;
    CallbackManager callbackManager;

    public static Intent getIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        getActivityComponent().inject(this);
        presenter.onAttach(LoginActivity.this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        //shows welcome slide fragment
        if (!presenter.getTutorial()) {
            showFragment(new WelcomeFragment());
        } else {
            changeStatusBarColor();
            showLoginPage();
        }
    }

    @Override
    public void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    @Override
    public void showLoginPage() {
        showFragment(new LoginFragment());
    }



    public void showFragment(Fragment fragment){
        fragmentManager.beginTransaction()
                .replace(R.id.rl_login, fragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void openHomeActivity() {
        Log.i("Demo:", "In home activity");
        startActivity(HomeActivity.getStartIntent(LoginActivity.this));
        finish();
    }

    @Override
    public void showRegisterFragment() {
        showFragment(new RegistrationFragment());
    }

    @Override
    public void openForgotPwdActivity() {
        Intent forgotIntent = new Intent(this, ForgotPwdActivity.class);
        startActivity(forgotIntent);
        finish();
    }


}
