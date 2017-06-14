package com.hellmoney.thca.model;

import com.google.gson.annotations.SerializedName;

public class NoticeDetailRes {
    @SerializedName("msg")
    private String message;
    @SerializedName("data")
    private Notice notice;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    @Override
    public String toString() {
        return "NoticeDetailRes{" +
                "message='" + message + '\'' +
                ", notice=" + notice.toString() +
                '}';
    }
}
