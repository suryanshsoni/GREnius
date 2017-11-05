package com.tensai.grenius.ui.login.login_page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.tensai.grenius.R;
import com.tensai.grenius.ui.base.BaseFragment;
import com.tensai.grenius.view.SlideTextView;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class LoginFragment extends BaseFragment implements LoginPageView {

    @Inject
    LoginPagePresenter<LoginPageView> presenter;

    @BindView(R.id.cl_login)
    ConstraintLayout clLogin;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_username)
    EditText etEmail;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_fb_login)
    TextView tvFbLogin;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.register_here)
    SlideTextView registerHere;
    Unbinder unbinder;

    CallbackManager callbackManager;
    String emailID, password;
    @BindView(R.id.tv_forgot_pwd)
    SlideTextView tvForgotPwd;
    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        callbackManager = CallbackManager.Factory.create();
        getActivityComponent().inject(this);
        presenter.onAttach(this);

        tvFbLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkConnected()) {
                    presenter.onFbClicked();
                } else {
                    showSnackbar(clLogin, getResources().getString(R.string.network_error));
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailID = etEmail.getText().toString();
                password = etPassword.getText().toString();
                if (isNetworkConnected()) {
                    presenter.signIn(emailID, password);

                } else {
                    showSnackbar(clLogin, getResources().getString(R.string.network_error));
                }
            }
        });

        registerHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.showRegisterFragment();
            }
        });

        tvForgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.openForgotPwdActivity();
            }
        });
        return view;
    }

    @Override
    public void registerFacebookCallbackResult(FacebookCallback<LoginResult> loginResultFacebookCallback) {
        LoginManager.getInstance().registerCallback(callbackManager, loginResultFacebookCallback);
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


    @Override
    public void initiateFbLogin() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email", "user_friends","user_location"));
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void openHomeActivity() {
        mListener.openHomeActivity();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void showRegisterFragment();
        void openForgotPwdActivity();
        void openHomeActivity();
    }
}
