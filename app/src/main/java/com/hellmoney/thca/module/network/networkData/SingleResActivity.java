package com.hellmoney.thca.module.network.networkData;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kirkee on 2017. 11. 10..
 */

public class SingleResActivity {
    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private Activitys activitys;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Activitys getActivitys() {
        return activitys;
    }

    public void setActivitys(Activitys activitys) {
        this.activitys = activitys;
    }
}
