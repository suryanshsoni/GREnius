package com.tensai.grenius.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.tensai.grenius.util.MyDatabase;

import java.util.List;

/**
 * Created by rishabhpanwar on 15/07/17.
 */
@Table(database = MyDatabase.class, insertConflict = ConflictAction.REPLACE)

public class Category extends BaseModel {

    @SerializedName("sno")
    @Expose
    @PrimaryKey
    @Column
    private String sno;
    @SerializedName("category")
    @Expose
    @Column
    private String category;
    @SerializedName("synonym")
    @Expose
    @Column
    private String synonym;
    @SerializedName("meaning")
    @Expose
    @Column
    private String meaning;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }


}
