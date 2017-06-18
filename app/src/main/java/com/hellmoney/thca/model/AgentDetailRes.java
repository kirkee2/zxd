package com.hellmoney.thca.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by semistone on 2017-06-18.
 */

public class AgentDetailRes {
    @SerializedName("msg")
    private String message;
    @SerializedName("data")
    private Agent agent;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}
