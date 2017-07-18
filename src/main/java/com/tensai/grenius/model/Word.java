package com.tensai.grenius.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.tensai.grenius.util.MyDatabase;



@Table(database = MyDatabase.class, insertConflict = ConflictAction.REPLACE)

public class Word extends BaseModel implements Parcelable {

    @SerializedName("sno")
    @Expose
    @PrimaryKey
    @Column
    private String sno;
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
    @SerializedName("hf")
    @Expose
    @Column
    private String hf;

    public Word(Parcel in) {
        sno = in.readString();
        word = in.readString();
        meaning = in.readString();
        synonym = in.readString();
        pzn = in.readString();
        pos = in.readString();
        example = in.readString();
        imagePath = in.readString();
        hf=in.readString();
    }

    public Word(){
        //empty constructor
    }

    public Word(String sno, String word, String meaning, String synonym, String pzn, String pos, String example, String imagePath, String hf) {
        this.sno = sno;
        this.word = word;
        this.meaning = meaning;
        this.synonym = synonym;
        this.pzn = pzn;
        this.pos = pos;
        this.example = example;
        this.imagePath = imagePath;
        this.hf=hf;

    }


    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
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

    public String getImagePath() { return imagePath; }

    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public String getHf() {
        return hf;
    }

    public void setHf(String hf) {
        this.hf = hf;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sno);
        dest.writeString(word);
        dest.writeString(meaning);
        dest.writeString(synonym);
        dest.writeString(pzn);
        dest.writeString(pos);
        dest.writeString(example);
        dest.writeString(imagePath);
        dest.writeString(hf);
    }

    public static final Parcelable.Creator<Word> CREATOR = new Parcelable.Creator<Word>() {
                public Word createFromParcel(Parcel in) {
                    return new Word(in);
                }

                public Word[] newArray(int size) {
                    return new Word[size];
                }};
}
