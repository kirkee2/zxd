package com.hellmoney.thca.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemRes {
    @SerializedName("msg")
    private String message;
    @SerializedName("data")
    private List<Item> items;
    @SerializedName("paging")
    private Pager pager;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }
}
