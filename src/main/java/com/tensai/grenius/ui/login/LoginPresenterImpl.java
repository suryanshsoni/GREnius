package com.tensai.grenius.ui.login;


import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.ui.base.BasePresenter;
import javax.inject.Inject;

/**
 * Created by Pavilion on 22-06-2017.
 */

public class LoginPresenterImpl<V extends LoginView> extends BasePresenter<V> implements LoginPresenter<V>{


    @Inject
    public LoginPresenterImpl(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public boolean getTutorial() { return getDataManager().getTutorial("dashboard"); }

}