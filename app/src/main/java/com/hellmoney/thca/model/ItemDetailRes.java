package com.hellmoney.thca.model;

import com.google.gson.annotations.SerializedName;

public class ItemDetailRes {
    @SerializedName("msg")
    private String message;
    @SerializedName("data")
    private Item item;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
