package com.tensai.grenius.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.hbb20.CountryCodePicker;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.tensai.grenius.R;
import com.tensai.grenius.ui.base.BaseActivity;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.TextUtils.isEmpty;

public class ProfileActivity extends BaseActivity implements ProfileView {

    String userName, fbToken, gender;
    int resourceId;
    List<Integer> arr_colour = new ArrayList<Integer>();

    @BindView(R.id.iv_profile)
    ImageView ivProfile;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.spinner_motive)
    MaterialBetterSpinner spinner_motive;
    ArrayList<String> SPINNERLIST = new ArrayList<String>();
    @BindView(R.id.btn_update_profile)
    Button btnUpdateProfile;
    ArrayAdapter<String> arrayAdapter;

    @Inject
    ProfilePresenter<ProfileView> presenter;
    @BindView(R.id.et_email_register)
    EditText etEmailRegister;
    @BindView(R.id.et_num_register)
    EditText etNumRegister;
    @BindView(R.id.et_city_register)
    EditText etCityRegister;
    @BindView(R.id.rg_gender_profile)
    RadioGroup rgGenderProfile;
    @BindView(R.id.ccp)
    CountryCodePicker ccp;
    @BindView(R.id.progress_profile)
    PieChart progressProfile;
    @BindView(R.id.et_age_profile)
    EditText etAgeProfile;
    @BindView(R.id.et_insti_profile)
    EditText etInstiProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        presenter.onAttach(this);

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

        SPINNERLIST.add("Hobby");
        SPINNERLIST.add("GRE/GMAT/SAT/IELTS/TOEFL");
        SPINNERLIST.add("CAT/XAT/NMAT");
        SPINNERLIST.add("Others");

        Legend legend = progressProfile.getLegend();
        legend.setEnabled(false);
        progressProfile.setDrawHoleEnabled(true);
        progressProfile.setHoleRadius(82f);
        progressProfile.setDescription("");
        arr_colour.add(R.color.colorPrimary);
        arr_colour.add(R.color.transparent);

        presenter.getProfile();

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int progress = 2;
                if (isNetworkConnected()) {
                    if (gender != null) {
                        progress++;
                    }
                    if (!isEmpty(etAgeProfile.getText())) {
                        progress++;
                    }
                    if (!isEmpty(etInstiProfile.getText())) {
                        progress++;
                    }
                    if (!isEmpty(spinner_motive.getText())) {
                        progress++;
                    }
                    if (isEmpty(etNumRegister.getText())) {
                        Log.i("MNB: ", "is Empty");
                        presenter.updateProfile(progress, etEmailRegister.getText().toString(), gender,etAgeProfile.getText().toString(), null, etCityRegister.getText().toString(), spinner_motive.getText().toString(),etInstiProfile.getText().toString());
                    } else {
                        Log.i("MNB: ", "is not Empty");
                        progress++;
                        presenter.updateProfile(progress, etEmailRegister.getText().toString(), gender, etAgeProfile.getText().toString(), ccp.getSelectedCountryCodeWithPlus() + "-" + etNumRegister.getText().toString(), etCityRegister.getText().toString(), spinner_motive.getText().toString(), etInstiProfile.getText().toString());
                    }
                } else {
                    showToast(getString(R.string.network_error));
                }
            }
        });
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

    @Override
    public void showProfile(int progress, String email, String gender, String age, String mobile, String city, String motive, String insti) {
        showProgress(progress);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        spinner_motive.setAdapter(arrayAdapter);
        spinner_motive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //item selected
            }
        });
        if (motive != null) {
            spinner_motive.setText(motive);
        }
        this.gender = gender;
        etAgeProfile.setText(age);
        etInstiProfile.setText(insti);
        etEmailRegister.setText(email);
        etCityRegister.setText(city);
        if (mobile != null) {
            ccp.setCountryForPhoneCode(Integer.parseInt(mobile.split("-")[0]));
            etNumRegister.setText(mobile.split("-")[1]);
        }
        if (gender != null) {
            switch (gender) {
                case "Male":
                    rgGenderProfile.check(R.id.rbtn_male);
                    break;

                case "Female":
                    rgGenderProfile.check(R.id.rbtn_female);
                    break;

                case "Other":
                    rgGenderProfile.check(R.id.rbtn_other);
                    break;
            }
        }

    }

    @Override
    public void showProgress(int progress) {
        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("");
        xVals.add("");
        yvalues.add(new Entry(progress, 0));
        yvalues.add(new Entry(7 - progress, 1));
        PieDataSet dataSet = new PieDataSet(yvalues, "");
        int arr[] = new int[arr_colour.size()];
        int i = 0;
        for (Integer n : arr_colour) {
            arr[i++] = n;
        }
        dataSet.setColors(arr, this);
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(.001f);
        progressProfile.setData(data);
        progressProfile.setVisibility(View.VISIBLE);
        progressProfile.animateY(2000);
    }

    public void genderSelected(View btn) {
        switch (btn.getId()) {
            case R.id.rbtn_male:
                gender = "Male";
                break;

            case R.id.rbtn_female:
                gender = getString(R.string.gender_f);
                break;

            case R.id.rbtn_other:
                gender = getString(R.string.gender_o);
                break;

            default:
                gender = null;
                break;
        }
    }
}
