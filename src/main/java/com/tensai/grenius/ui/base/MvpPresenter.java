package com.tensai.grenius.ui.base;

/**
 * Created by Pavilion on 21-06-2017.
 */

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V mvpView);

    void onDetach();

    void handleApiError(Object error);

    void setUserAsLoggedOut();

}
