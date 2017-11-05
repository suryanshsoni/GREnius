package com.tensai.grenius.ui.home.dashboard_fragment.word_of_day;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tensai.grenius.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SponsorActivity extends AppCompatActivity {

    String link;
    @BindView(R.id.wv_sponsor)
    WebView wvSponsor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_sponsor);
        ButterKnife.bind(this);
        startWebView(intent.getStringExtra("link"));
    }

    private void startWebView(String url) {
        wvSponsor.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog;

            //If you will not use this method url links are opeen in new brower not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on url load
            public void onLoadResource(WebView view, String url) {
                if (progressDialog == null) {
                    progressDialog = new ProgressDialog(SponsorActivity.this);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                }
            }

            public void onPageFinished(WebView view, String url) {
                try {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        });

        // Settings enabled on webview
        wvSponsor.getSettings().setJavaScriptEnabled(true);
        wvSponsor.getSettings().setLoadWithOverviewMode(true);
        wvSponsor.getSettings().setUseWideViewPort(true);
        wvSponsor.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        wvSponsor.getSettings().setBuiltInZoomControls(true);
        wvSponsor.loadUrl(url);
    }
}
