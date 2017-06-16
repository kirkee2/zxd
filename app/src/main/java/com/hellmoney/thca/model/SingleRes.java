package com.hellmoney.thca.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by len on 2017. 6. 16..
 */

public class SingleRes {

    @SerializedName("msg")
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
