package com.hellmoney.thca.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Notice {
    @SerializedName("notice_id")
    private int noticeId;
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("type")
    private String type;
    @SerializedName("register_time")
    private Date registerTime;

    public int getNoticeId() {
        return noticeId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }

    public Date getRegisterTime() {
        return registerTime;
    }
}
