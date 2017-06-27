package com.tensai.grenius.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.tensai.grenius.util.MyDatabase;


@Table(database = MyDatabase.class, insertConflict = ConflictAction.REPLACE)

public class Word extends BaseModel {

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
    public Word() {
    }

    public Word(String sno, String word, String meaning, String synonym, String pzn, String pos,String example) {
        this.example = example;
        this.sno = sno;
        this.word = word;
        this.meaning = meaning;
        this.synonym = synonym;
        this.pzn = pzn;
        this.pos = pos;
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


}
