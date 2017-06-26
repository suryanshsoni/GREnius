package com.tensai.grenius.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rishabhpanwar on 25/06/17.
 */

public class Articles {

    @SerializedName("title")
    @Expose
    String title;

    @SerializedName("imagePath")
    @Expose
    String imagePath;

    @SerializedName("desc")
    @Expose
    String desc;

    public String getTitle() {
        return title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDesc() {
        return desc;
    }
}
