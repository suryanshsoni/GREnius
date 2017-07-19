package com.tensai.grenius.ui.home.quiz_fragment;


import android.graphics.Color;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.tensai.grenius.R;
import com.tensai.grenius.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * Created by Pavilion on 01-07-2017.
 */
@NonReusable
@Layout(R.layout.quiz_card_layout)
public class QuestionCard {
    private static final String TAG = "QuestionCard";

    @View(R.id.tv_question)
    private TextView mQuestionTextView;

    @View(R.id.btn_option_1)
    private Button mOption1Button;

    @View(R.id.btn_option_2)
    private Button mOption2Button;

    @View(R.id.btn_option_3)
    private Button mOption3Button;

    @View(R.id.btn_option_4)
    private Button mOption4Button;

    @View(R.id.tv_question_no)
    private TextView tvQuestionNo;

    private Question mQuestion;
    private int answer_pos;
    Button buttons[] = new Button[4];
    int correct = 0, incorrect = 0;

    Callback callback;

    public QuestionCard(Question question, Callback callback) {
        mQuestion = question;
        this.callback = callback;
    }

    @Resolve
    private void onResolved() {

        tvQuestionNo.setText(mQuestion.getSno());
        Log.i("QWERTY",""+mQuestion.getSno());
        mQuestionTextView.setText(mQuestion.getQuestion());
        List<Integer> set = new ArrayList<Integer>();
        int pos;

        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    buttons[i] = mOption1Button;
                    break;
                case 1:
                    buttons[i] = mOption2Button;
                    break;
                case 2:
                    buttons[i] = mOption3Button;
                    break;
                case 3:
                    buttons[i] = mOption4Button;
                    break;
            }
        }
        for (int j = 0; j <= 3; j++)
            set.add(j);
        Random r = new Random();
        while (!set.isEmpty()) {
            pos = r.nextInt(set.size());

            if (set.size() == 4) {
                answer_pos = pos;
                buttons[set.get(pos)].setText(mQuestion.getAnswer());
            } else if (set.size() == 3) {
                buttons[set.get(pos)].setText(mQuestion.getIncorrect_1());
            } else if (set.size() == 2) {
                buttons[set.get(pos)].setText(mQuestion.getIncorrect_2());
            } else {
                buttons[set.get(pos)].setText(mQuestion.getIncorrect_3());
            }
            set.remove(pos);

        }

    }

    private void showCorrectOptions(int clicked) {
        if (clicked == answer_pos) {
            buttons[clicked].setTextColor(Color.GREEN);
            correct++;
        } else {
            incorrect++;
            buttons[clicked].setTextColor(Color.RED);
            buttons[answer_pos].setTextColor(Color.GREEN);
        }

        for (int i = 0; i < 4; i++) {
            buttons[i].setEnabled(false);
        }
        callback.call(correct);
    }

    @SwipeOut
    @SwipeIn
    public void markUnattempted() {
        Log.d("Demo", "marking unattempted " + correct);
        if (correct == 0 && incorrect == 0) {
            callback.callUnattempted();
        }

    }

    @Click(R.id.btn_option_1)
    public void onOption1Click() {
        showCorrectOptions(0);
    }

    @Click(R.id.btn_option_2)
    public void onOption2Click() {
        showCorrectOptions(1);
    }

    @Click(R.id.btn_option_3)
    public void onOption3Click() {
        showCorrectOptions(2);
    }

    @Click(R.id.btn_option_4)
    public void onOption4Click() {
        showCorrectOptions(3);
    }

    public interface Callback {
        public void call(int correct);

        public void callUnattempted();

    }


}
