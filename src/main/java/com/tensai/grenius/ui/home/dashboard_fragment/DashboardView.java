package com.tensai.grenius.ui.home.dashboard_fragment;

import com.tensai.grenius.model.*;
import com.tensai.grenius.ui.base.MvpView;

import java.util.List;

/**
 * Created by rishabhpanwar on 24/06/17.
 */

public interface DashboardView extends MvpView {

    void showDashboardArticles(List<Articles> articles);
    void showWordOfDay(com.tensai.grenius.model.WordOfDay wordOfDay);
    void showTitleSponsor(List<Titleinstitute> institute);
}
