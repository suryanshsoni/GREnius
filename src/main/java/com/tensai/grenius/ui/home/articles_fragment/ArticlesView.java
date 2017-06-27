package com.tensai.grenius.ui.home.articles_fragment;

import com.tensai.grenius.model.Articles;
import com.tensai.grenius.ui.base.MvpView;

import java.util.List;

/**
 * Created by rishabhpanwar on 25/06/17.
 */

public interface ArticlesView extends MvpView {

    void showArticles(List<Articles> articles);
}
