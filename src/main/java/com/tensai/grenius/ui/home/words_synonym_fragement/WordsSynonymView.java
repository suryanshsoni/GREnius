package com.tensai.grenius.ui.home.words_synonym_fragement;

import com.tensai.grenius.model.Category;
import com.tensai.grenius.ui.base.MvpView;

import java.util.List;

/**
 * Created by rishabhpanwar on 16/07/17.
 */

public interface WordsSynonymView extends MvpView {

    void showCategories(List<Category> categories);
}
