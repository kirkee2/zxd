package com.hellmoney.thca.module.network.networkData;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kirkee on 2017. 11. 10..
 */

public class SingleResData {

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private int data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
