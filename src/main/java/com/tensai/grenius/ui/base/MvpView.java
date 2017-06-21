package com.tensai.grenius.ui.base;

import android.support.annotation.StringRes;

import com.tensai.grenius.util.MenuComponent;

import java.util.List;

/**
 * Created by Pavilion on 21-06-2017.
 */

public interface MvpView {
    void showLoading(String text);

    void hideLoading();

    void openActivityOnTokenExpire();

    void onError(@StringRes int resId);

    void onError(String message);

    boolean isNetworkConnected();
    void showMenuOverlay(List<MenuComponent> menuComponents, MenuFragment.MenuCallback callback);
    void hideMenuOverlay();
    void showPhotoOverlay(String url);
    void hidePhotoOverlay();
}
