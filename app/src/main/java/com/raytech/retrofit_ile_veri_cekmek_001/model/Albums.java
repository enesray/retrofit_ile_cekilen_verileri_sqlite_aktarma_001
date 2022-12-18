package com.raytech.retrofit_ile_veri_cekmek_001.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Albums {

    @SerializedName("userId")
    @Expose
     Integer userId;
    @SerializedName("id")
    @Expose
     Integer id;
    @SerializedName("title")
    @Expose
     String title;


    public Integer getUserId() {
        return userId;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
