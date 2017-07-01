package com.tensai.grenius.ui.home.quiz_fragment;

import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.tensai.grenius.R;
import com.tensai.grenius.model.Question;

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

        private Question mQuestion;

        public QuestionCard(Question question) {
            mQuestion=question;
        }
    @Resolve
    private void onResolved() {

        mQuestionTextView.setText(mQuestion.getQuestion());

        for (int i = 0; i < 4   ; i++) {
            Button button = null;
            switch (i) {
                case 0:
                    button = mOption1Button;
                    break;
                case 1:
                    button = mOption2Button;
                    break;
                case 2:
                    button = mOption3Button;
                    break;
                case 3:
                    button = mOption4Button;
                    break;
            }

            if (button != null)
                button.setText(mQuestion.getIncorrect_1());

        }
    }

    private void showCorrectOptions() {
        for (int i = 0; i < 4; i++) {
            //Option option = mQuestion.getOptionList().get(i);
            Button button = null;
            switch (i) {
                case 0:
                    button = mOption1Button;
                    break;
                case 1:
                    button = mOption2Button;
                    break;
                case 2:
                    button = mOption3Button;
                    break;
                case 3:
                    button = mOption4Button;
                    break;
            }
            if (button != null) {
              //  if (option.isCorrect()) {
                    button.setBackgroundColor(Color.GREEN);
                //} else {
                  //  button.setBackgroundColor(Color.RED);
                //}
            }
        }
    }
    @Click(R.id.btn_option_1)
    public void onOption1Click() {
        showCorrectOptions();
    }

    @Click(R.id.btn_option_2)
    public void onOption2Click() {
        showCorrectOptions();
    }

    @Click(R.id.btn_option_3)
    public void onOption3Click() {
        showCorrectOptions();
    }
    @Click(R.id.btn_option_4)
    public void onOption4Click() {
        showCorrectOptions();
    }


}
