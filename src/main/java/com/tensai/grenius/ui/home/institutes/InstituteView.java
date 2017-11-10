package com.tensai.grenius.ui.home.institutes;

import com.tensai.grenius.model.Institute;
import com.tensai.grenius.ui.base.MvpView;

import java.util.List;

/**
 * Created by rishabhpanwar on 26/10/17.
 */

public interface InstituteView extends MvpView {

    void showInstitutes(List<Institute> institutes);
}
