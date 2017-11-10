package com.tensai.grenius.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;

/**
 * Created by rishabhpanwar on 05/11/17.
 */

public class Titleinstitute {

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("url")
    @Expose
    String url;

    @SerializedName("imagePath")
    @Expose
    String imagePath;

    @SerializedName("location")
    @Expose
    String location;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getLocation() {
        return location;
    }
}
