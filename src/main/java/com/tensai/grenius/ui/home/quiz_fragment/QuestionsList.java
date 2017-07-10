package com.tensai.grenius.ui.home.quiz_fragment;

import android.util.Log;

import com.tensai.grenius.model.Question;
import com.tensai.grenius.model.Word;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by rishabhpanwar on 01/07/17.
 */

public class QuestionsList{

    public static final Random gen = new Random();

    List<Question> questions;
    List<Word> words,only_words;
    List<Integer> sno;
    int first_pos;
    int arr[],i,j;
    Callback callback;

    public QuestionsList(int first_pos, List<Word> words, Callback callback) {
        this.first_pos = first_pos;
        this.callback = callback;
        this.words=words;
        questions=new ArrayList<Question>();
    }

    public void getQuestions(){
        arr=new int[10];
        int z;
        Log.d("Demo","in get questions");
        for (int k : printRandomNumbers(5)) {
            j=k;
            i=0;
            while(i<3){

                z= gen.nextInt(2500);
                if(z!=j){
                    arr[i]= z;
                    i++;
                }
            }

            Log.d("Demo:", String.valueOf(j));
            Question question=new Question();
            question.setQuestion(words.get(j).getWord());
            question.setAnswer(words.get(j).getMeaning());
            question.setExample(words.get(j).getExample());
            question.setIncorrect_1(words.get(arr[0]).getMeaning());
            question.setIncorrect_2(words.get(arr[1]).getMeaning());
            question.setIncorrect_3(words.get(arr[2]).getMeaning());

            questions.add(question);
        }

        callback.call(questions);
    }

    public interface Callback{
        void call(List<Question> questions);
    }

    private int[] printRandomNumbers(int n) {

        int[] result = new int[n];
        int maxRange=0;
        Set<Integer> used = new HashSet<Integer>();
        int ctr=0;
        if(first_pos==-1){
            maxRange=words.size();
            ctr=1;
        }
        else {
            int size= words.size()-first_pos;
            if(size<50){
                maxRange=size;
            }
            else{
                maxRange=50;
            }
        }


        for (int i = 0; i < n; i++) {

            int newRandom;
            do {
                newRandom = gen.nextInt(maxRange);
            } while (used.contains(newRandom));
            result[i] = newRandom+first_pos+ctr;
            used.add(newRandom);
        }
        return result;
    }
}
