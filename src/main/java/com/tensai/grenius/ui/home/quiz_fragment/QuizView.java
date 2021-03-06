package com.tensai.grenius.ui.home.quiz_fragment;

import com.tensai.grenius.model.Question;
import com.tensai.grenius.ui.base.MvpView;

import java.util.List;

/**
 * Created by Pavilion on 30-06-2017.
 */

public interface QuizView extends MvpView {
    void refreshQuestionnaire(List<Question> questionList);
    void reloadQuestionnaire(List<Question> questionList);

    void showResult(int correct, int incorrect,int unattempted);
}
