package com.tensai.grenius.ui.home.quiz_fragment;

import com.tensai.grenius.model.Question;
import com.tensai.grenius.model.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rishabhpanwar on 01/07/17.
 */

public class QuestionsList{

    List<Question> questions;
    List<Word> words;
    int last_pos;
    int first_pos;
    Callback callback;

    public QuestionsList(int first_pos, List<Word> words, Callback callback) {
        this.first_pos = first_pos;
        this.callback = callback;
        this.words=words;
    }

    void getQuestions(){
        questions=new ArrayList<Question>();
        for(int i=0;i<50;i++){
            Question q=new Question(String.valueOf(i),"Test Question","Answer","Incorrect 1","Incorrect 2","Incorrect3","Incorrect3");
            questions.add(q);
        }
        callback.call(questions);
    }

    public interface Callback{
        void call(List<Question> questions);
    }
}
