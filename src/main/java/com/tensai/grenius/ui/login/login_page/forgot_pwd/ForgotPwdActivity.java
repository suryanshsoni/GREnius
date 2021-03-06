package com.tensai.grenius.ui.login.login_page.forgot_pwd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.tensai.grenius.R;
import com.tensai.grenius.ui.base.BaseActivity;
import com.tensai.grenius.ui.login.LoginActivity;
import com.tensai.grenius.view.SlideTextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotPwdActivity extends BaseActivity implements ForgotPwdView {

    @BindView(R.id.btn_forgot)
    Button btnForgot;
    @BindView(R.id.et_passkey_forgot)
    EditText etPasskeyForgot;
    @BindView(R.id.et_email_forgot)
    EditText etEmailForgot;
    @BindView(R.id.tv_heading_forgot)
    SlideTextView tvHeadingForgot;
    @BindView(R.id.rl_email_forgot)
    RelativeLayout rlEmailForgot;
    @BindView(R.id.tv_newpwd_forgot)
    SlideTextView tvNewpwdForgot;
    @BindView(R.id.et_newpwd_forgot)
    EditText etNewpwdForgot;
    @BindView(R.id.tv_confirmpwd_forgot)
    SlideTextView tvConfirmpwdForgot;
    @BindView(R.id.et_confirmpwd_forgot)
    EditText etConfirmpwdForgot;
    @BindView(R.id.rl_pwd_forgot)
    LinearLayout rlPwdForgot;

    @Inject
    ForgotPwdPresenter<ForgotPwdView> presenter;
    @BindView(R.id.rl_main_forgot)
    RelativeLayout rlMainForgot;
    @BindView(R.id.btn_update_forgot)
    Button btnUpdateForgot;

    String emailId, passkey, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pwd);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        presenter.onAttach(this);
        enterPasskey(false);
        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailId = etEmailForgot.getText().toString();
                if (isNetworkConnected()) {
                    if (!etPasskeyForgot.isEnabled()) {
                        //send passkey to email
                        presenter.generatePasskey(emailId);
                    } else {
                        //verify passkey
                        passkey = etPasskeyForgot.getText().toString();
                        presenter.verifyPasskey(emailId, passkey);
                    }
                } else {
                    showSnackbar(rlMainForgot, getResources().getString(R.string.network_error));
                }
            }
        });
        btnUpdateForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = etNewpwdForgot.getText().toString();
                if (password.equals(etConfirmpwdForgot.getText().toString())){
                    if (isNetworkConnected()) {
                        //update password
                        presenter.updatePassword(emailId,password,passkey);
                    }else {
                        showSnackbar(rlMainForgot, getResources().getString(R.string.network_error));
                    }
                }else {
                    etConfirmpwdForgot.requestFocus();
                    showToast("Passwords do not match!");
                }
            }
        });
    }

    @Override
    public void enterPasskey(boolean enter) {
        etEmailForgot.setEnabled(!enter);
        etPasskeyForgot.setEnabled(enter);
        if (enter) {
            btnForgot.setText("Submit Passkey");
        } else {
            btnForgot.setText("Send Passkey");
        }

    }

    @Override
    public void changePassword() {
        rlEmailForgot.setVisibility(View.GONE);
        rlPwdForgot.setVisibility(View.VISIBLE);
    }

    @Override
    public void redirectLogin() {
        Intent login = LoginActivity.getIntent(this);
        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(login);
        finish();
    }
}
