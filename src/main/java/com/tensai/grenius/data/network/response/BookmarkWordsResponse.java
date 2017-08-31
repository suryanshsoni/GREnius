package com.tensai.grenius.data.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tensai.grenius.model.Word;

import java.util.ArrayList;

/**
 * Created by rishabhpanwar on 27/08/17.
 */

public class BookmarkWordsResponse{
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("status")
    @Expose
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}