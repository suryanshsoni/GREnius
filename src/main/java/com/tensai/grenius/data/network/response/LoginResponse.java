package com.tensai.grenius.data.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pavilion on 23-06-2017.
 */

public class LoginResponse {
    @SerializedName("message")
    @Expose
    String sessionId;
    @SerializedName("status")
    @Expose
    String status;
    @SerializedName("name")
    @Expose
    String name;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
