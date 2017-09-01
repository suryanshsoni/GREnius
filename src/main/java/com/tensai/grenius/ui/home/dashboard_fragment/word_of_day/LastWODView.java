package com.tensai.grenius.ui.home.dashboard_fragment.word_of_day;

import com.tensai.grenius.model.WordOfDay;
import com.tensai.grenius.ui.base.MvpView;

import java.util.List;

/**
 * Created by rishabhpanwar on 01/09/17.
 */

public interface LastWODView extends MvpView {
    void setView(List<WordOfDay> wordOfDays);
}
