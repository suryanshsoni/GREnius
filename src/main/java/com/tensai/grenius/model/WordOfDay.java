package com.tensai.grenius.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;

/**
 * Created by Pavilion on 15-07-2017.
 */

public class WordOfDay implements Parcelable{
    @SerializedName("date")
    @Expose
    @PrimaryKey
    @Column
    private String date;
    @SerializedName("word")
    @Expose
    @Column
    private String word;
    @SerializedName("meaning")
    @Expose
    @Column
    private String meaning;
    @SerializedName("synonym")
    @Expose
    @Column
    private String synonym;
    @SerializedName("pzn")
    @Expose
    @Column
    private String pzn;
    @SerializedName("pos")
    @Expose
    @Column
    private String pos;
    @SerializedName("example")
    @Expose
    @Column
    private String example;
    @SerializedName("imagePath")
    @Expose
    @Column
    private String imagePath;
    @SerializedName("translated")
    @Expose
    @Column
    private String translated;

    protected WordOfDay(Parcel in) {
        date = in.readString();
        word = in.readString();
        meaning = in.readString();
        synonym = in.readString();
        pzn = in.readString();
        pos = in.readString();
        example = in.readString();
        imagePath = in.readString();
        translated = in.readString();
    }

    public static final Creator<WordOfDay> CREATOR = new Creator<WordOfDay>() {
        @Override
        public WordOfDay createFromParcel(Parcel in) {
            return new WordOfDay(in);
        }

        @Override
        public WordOfDay[] newArray(int size) {
            return new WordOfDay[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    public String getPzn() {
        return pzn;
    }

    public void setPzn(String pzn) {
        this.pzn = pzn;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTranslated() {
        return translated;
    }

    public void setTranslated(String translated) {
        this.translated = translated;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(word);
        dest.writeString(meaning);
        dest.writeString(synonym);
        dest.writeString(pzn);
        dest.writeString(pos);
        dest.writeString(example);
        dest.writeString(imagePath);
    }
}
