package com.hellmoney.thca.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by len on 2017. 6. 13..
 */

public class SingleEstimateRes {

    @SerializedName("msg")
    private String message;

    @SerializedName("data")
    private Estimate mEstimates;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Estimate getEstimates() {
        return mEstimates;
    }

    public void setEstimates(Estimate estimates) {
        mEstimates = estimates;
    }
}