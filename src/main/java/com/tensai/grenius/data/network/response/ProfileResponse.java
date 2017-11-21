package com.tensai.grenius.data.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ishitabhandari on 10/11/17.
 */

public class ProfileResponse {

    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("status")
    @Expose
    String status;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
