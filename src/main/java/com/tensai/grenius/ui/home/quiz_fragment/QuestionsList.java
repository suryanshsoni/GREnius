package com.tensai.grenius.ui.home.quiz_fragment;

import com.tensai.grenius.model.Question;
import com.tensai.grenius.model.Word;

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
    }

    public void getQuestions(){

        for(i=0;i<3;i++){
            arr[i]= gen.nextInt(2500);
        }
        for (int k : printRandomNumbers(20,50)) {

            j=first_pos+k;
            questions.get(j).setQuestion(words.get(j).getWord());
            questions.get(j).setAnswer(words.get(j).getMeaning());
            questions.get(j).setExample(words.get(j).getExample());
            questions.get(j).setIncorrect_1(words.get(arr[0]).getMeaning());
            questions.get(j).setIncorrect_2(words.get(arr[1]).getMeaning());
            questions.get(j).setIncorrect_3(words.get(arr[2]).getMeaning());
        }

        callback.call(questions);
    }

    public interface Callback{
        void call(List<Question> questions);
    }

    private static int[] printRandomNumbers(int n, int maxRange) {

        int[] result = new int[n];
        Set<Integer> used = new HashSet<Integer>();

        for (int i = 0; i < n; i++) {

            int newRandom;
            do {
                newRandom = gen.nextInt(maxRange);
            } while (used.contains(newRandom));
            result[i] = newRandom;
            used.add(newRandom);
        }
        return result;
    }
}
