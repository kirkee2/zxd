package com.hellmoney.thca.module.network.networkData;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kirkee on 2017. 11. 10..
 */

public class SingleResCount {

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private Count count;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Count getCount() {
        return count;
    }

    public void setCount(Count count) {
        this.count = count;
    }
}
