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
}
