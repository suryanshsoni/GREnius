package com.tensai.grenius.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by rishabhpanwar on 05/11/17.
 */

public class Institute extends BaseModel implements Parcelable {

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("type")
    @Expose
    String type;

    @SerializedName("sno")
    @Expose
    @PrimaryKey
    String sno;

    @SerializedName("short_desc")
    @Expose
    String short_desc;

    @SerializedName("long_desc")
    @Expose
    String long_desc;

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

    public String getType() {
        return type;
    }

    public String getSno() {
        return sno;
    }

    public String getShort_desc() {
        return short_desc;
    }

    public String getLong_desc() {
        return long_desc;
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

    @Override
    public int describeContents() {
        return 0;
    }

    public Institute(String name, String type, String sno, String short_desc, String long_desc, String url, String imagePath, String location) {
        this.name = name;
        this.type = type;
        this.sno = sno;
        this.short_desc = short_desc;
        this.long_desc = long_desc;
        this.url = url;
        this.imagePath = imagePath;
        this.location = location;
    }

    public Institute(Parcel in) {
        name = in.readString();
        type = in.readString();
        sno = in.readString();
        short_desc = in.readString();
        long_desc = in.readString();
        url = in.readString();
        imagePath = in.readString();
        location=in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(sno);
        dest.writeString(short_desc);
        dest.writeString(long_desc);
        dest.writeString(url);
        dest.writeString(imagePath);
        dest.writeString(location);
    }

    public static final Parcelable.Creator<Institute> CREATOR = new Parcelable.Creator<Institute>() {
        public Institute createFromParcel(Parcel in) {
            return new Institute(in);
        }

        public Institute[] newArray(int size) {
            return new Institute[size];
        }};


    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Institute wordObj = (Institute) object;
            if (this.name.equals(wordObj.getName())) {
                result = true;
            }
        }
        return result;
    }
}
