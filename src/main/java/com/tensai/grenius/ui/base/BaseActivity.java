package com.tensai.grenius.ui.base;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tensai.grenius.GREniusApplication;
import com.tensai.grenius.di.component.ActivityComponent;
import com.tensai.grenius.di.component.DaggerActivityComponent;
import com.tensai.grenius.di.module.ActivityModule;
import com.tensai.grenius.util.MenuComponent;

import java.util.List;

/**
 * Created by Pavilion on 21-06-2017.
 */

public class BaseActivity extends AppCompatActivity implements MvpView,BaseFragment.Callback {
    private ProgressDialog progressDialog;

    private ActivityComponent activityComponent;
    private DialogFragment dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        dialog = new MenuFragment();
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((GREniusApplication) getApplication()).getApplicationComponent())
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void showLoading(String text) {

        progressDialog.setMessage(text);
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.cancel();
        }
    }

    public void showToast(String message){
        Toast.makeText(BaseActivity.this, message, Toast.LENGTH_LONG).show();

    }

    public void showSnackbar(ViewGroup viewGroup, String message){
        Snackbar snackbar = Snackbar
                .make(viewGroup, ""+message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }



    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onError(@StringRes int resId) {
        onError(getString(resId));
    }

    @Override
    public void onError(String message) {
        showToast(message);
    }

    @Override
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) // connected to wifi
            {
                //Toast.makeText(this, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
            }
            else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) // connected to the mobile provider's data plan
            {
                //Toast.makeText(this, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void showMenuOverlay(List<MenuComponent> menuComponents, MenuFragment.MenuCallback callback) {
        if(dialog!= null){
            if( dialog.isVisible())
                dialog.dismiss();
        }
        MenuFragment fragment = MenuFragment.getInstance();
        fragment.setMenuComponentList(menuComponents);
        fragment.setMenuCallback(callback);
        dialog = fragment;
        dialog.show(getFragmentManager(),"Menu_Fragment");
    }

    @Override
    public void hideMenuOverlay() {

    }

    @Override
    public void showPhotoOverlay(String url) {
        if(dialog!= null){
            if( dialog.isVisible())
                dialog.dismiss();
        }
        dialog = PhotoViewFragment.getInstance(url);
        dialog.show(getFragmentManager(),"PHOTO_FRAGMENT");
    }

    @Override
    public void hidePhotoOverlay() {

    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
    @Override
    public void share(String text) {
        Intent sendIntent= new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,text);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public String capitalize(String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

}
