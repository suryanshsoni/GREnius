package com.tensai.grenius.data.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ishitabhandari on 04/12/17.
 */

public class ProfileDetailResponse {
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("status")
    @Expose
    String status;
    @SerializedName("gender")
    @Expose
    String gender;
    @SerializedName("motive")
    @Expose
    String motive;
    @SerializedName("dob")
    @Expose
    String dob;
    @SerializedName("work")
    @Expose
    String work;
    @SerializedName("mobile")
    @Expose
    String mobile;
    @SerializedName("city")
    @Expose
    String city;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public String getGender() {
        return gender;
    }

    public String getMotive() {
        return motive;
    }

    public String getDob() {
        return dob;
    }

    public String getWork() {
        return work;
    }

    public String getMobile() {
        return mobile;
    }

    public String getCity() {
        return city;
    }
}
