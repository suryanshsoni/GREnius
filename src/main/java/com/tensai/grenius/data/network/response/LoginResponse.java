package com.tensai.grenius.data.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pavilion on 23-06-2017.
 */

public class LoginResponse {
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("status")
    @Expose
    String status;
    @SerializedName("name")
    @Expose
    String name;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
