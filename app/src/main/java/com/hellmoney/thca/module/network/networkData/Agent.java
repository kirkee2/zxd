package com.hellmoney.thca.module.network.networkData;

import com.google.gson.annotations.SerializedName;

public class Agent {
    @SerializedName("agent_id")
    String agentId;
    @SerializedName("name")
    String name;
    @SerializedName("photo")
    String photo;
    @SerializedName("company_name")
    String companyName;

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
