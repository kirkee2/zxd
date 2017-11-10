package com.hellmoney.thca.module.network.networkData;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NoticeRes {
    @SerializedName("msg")
    private String message;
    @SerializedName("data")
    private List<Notice> notices;
    @SerializedName("paging")
    private Pager pager;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Notice> getNotices() {
        return notices;
    }

    public void setNotices(List<Notice> notices) {
        this.notices = notices;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }
}
