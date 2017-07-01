package com.tensai.grenius.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pavilion on 30-06-2017.
 */
public class Question {

    @SerializedName("sno")
    @Expose
    private String sno;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("incorrect_1")
    @Expose
    private String incorrect_1;
    @SerializedName("incorrect_2")
    @Expose
    private String incorrect_2;
    @SerializedName("incorrect_3")
    @Expose
    private String incorrect_3;
    @SerializedName("example")
    @Expose
    private String example;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getIncorrect_1() {
        return incorrect_1;
    }

    public void setIncorrect_1(String incorrect_1) {
        this.incorrect_1 = incorrect_1;
    }

    public String getIncorrect_2() {
        return incorrect_2;
    }

    public void setIncorrect_2(String incorrect_2) {
        this.incorrect_2 = incorrect_2;
    }

    public String getIncorrect_3() {
        return incorrect_3;
    }

    public void setIncorrect_3(String incorrect_3) {
        this.incorrect_3 = incorrect_3;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
