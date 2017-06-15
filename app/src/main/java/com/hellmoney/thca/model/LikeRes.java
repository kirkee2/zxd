package com.hellmoney.thca.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by len on 2017. 6. 15..
 */

public class LikeRes {

    @SerializedName("msg")
    private String like;

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }
}
