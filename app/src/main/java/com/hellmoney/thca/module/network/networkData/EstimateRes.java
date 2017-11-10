package com.hellmoney.thca.module.network.networkData;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by len on 2017. 6. 15..
 */

public class EstimateRes {

    @SerializedName("msg")
    private String message;
    @SerializedName("data")
    private List<Estimate> mEstimates;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Estimate> getEstimates() {
        return mEstimates;
    }

    public void setEstimates(List<Estimate> requests) {
        mEstimates = requests;
    }
}
