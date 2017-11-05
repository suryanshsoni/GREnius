package com.tensai.grenius.ui.home.institutes;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.tensai.grenius.R;
import com.tensai.grenius.ui.base.BaseActivity;

import javax.inject.Inject;

public class InstitutesActivity extends BaseActivity implements InstituteView{

    @Inject
    InstitutePresenter<InstituteView> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        presenter.onAttach(InstitutesActivity.this);

        setContentView(R.layout.activity_institutes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

    }
}
