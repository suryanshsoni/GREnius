package com.tensai.grenius.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.tensai.grenius.R;
import com.tensai.grenius.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends BaseActivity {

    String userName, fbToken;
    int resourceId;
    @BindView(R.id.iv_profile)
    ImageView ivProfile;
    @BindView(R.id.tv_name)
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        resourceId = intent.getIntExtra("resourceID", 0);
        userName = intent.getStringExtra("Name");
        fbToken = intent.getStringExtra("FBToken");
        if (resourceId == 0 && fbToken == null) {
            ivProfile.setImageResource(R.drawable.avatar_three);
        } else {
            setPicture(resourceId, fbToken);
        }
        tvName.setText(userName);
    }

    private void setPicture(int resourceId, final String fbToken) {
        //presenter.setResourceId(resourceId);
        if (fbToken != null) {
            final Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(80)
                    .oval(false)
                    .build();

            Picasso.with(this)
                    .load("https://graph.facebook.com/" + fbToken + "/picture?type=large")
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .fit()
                    .transform(transformation)
                    .into(ivProfile, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            // Try again online if cache failed
                            Picasso.with(ProfileActivity.this)
                                    .load("https://graph.facebook.com/" + fbToken + "/picture?type=large")
                                    .error(R.drawable.avatar_three)
                                    .fit()
                                    .transform(transformation)
                                    .into(ivProfile);
                        }
                    });
        } else {
            switch (resourceId) {

                case R.id.avatar_one:
                    try {
                        Picasso.with(getApplicationContext())
                                .load(R.drawable.avatar_one)
                                .into(ivProfile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case R.id.avatar_two:
                    try {
                        Picasso.with(getApplicationContext())
                                .load(R.drawable.avatar_two)
                                .into(ivProfile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case R.id.avatar_three:
                    try {
                        Picasso.with(getApplicationContext())
                                .load(R.drawable.avatar_three)
                                .into(ivProfile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case R.id.avatar_four:
                    try {
                        Picasso.with(getApplicationContext())
                                .load(R.drawable.avatar_four)
                                .into(ivProfile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
