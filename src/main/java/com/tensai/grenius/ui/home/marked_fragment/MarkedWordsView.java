package com.tensai.grenius.ui.home.marked_fragment;

import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.MvpView;

import java.util.List;

/**
 * Created by ishitabhandari on 18/07/17.
 */

public interface MarkedWordsView extends MvpView{
    void setView(List<Word> list);
}
