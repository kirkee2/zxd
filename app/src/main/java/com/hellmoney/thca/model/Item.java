package com.hellmoney.thca.model;

import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("item_id")
    private int itemId;
    @SerializedName("item_name")
    private String itemName;
    @SerializedName("interest_rate_type")
    private String interestRateType;
    @SerializedName("repayment_type")
    private String repaymentType;
    @SerializedName("min_interest_rate")
    private Float minInterestRate;
    @SerializedName("max_interest_rate")
    private Float maxInterestRate;
    @SerializedName("early_repayment_fee")
    private Float earlyRepaymentFee;
    @SerializedName("overdue_interest_rate_1")
    private Float overdueInterestRate1;
    @SerializedName("overdue_time_1")
    private String overdueTime1;
    @SerializedName("overdue_interest_rate_2")
    private Float overdueInterestRate2;
    @SerializedName("overdue_time_2")
    private String overdueTime2;
    @SerializedName("overdue_interest_rate_3")
    private Float overdueInterestRate3;
    @SerializedName("overdue_time_3")
    private String overdueTime3;
    @SerializedName("loan_type")
    private String loanType;

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getInterestRateType() {
        return interestRateType;
    }

    public void setInterestRateType(String interestRateType) {
        this.interestRateType = interestRateType;
    }

    public String getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType;
    }

    public Float getMinInterestRate() {
        return minInterestRate;
    }

    public void setMinInterestRate(Float minInterestRate) {
        this.minInterestRate = minInterestRate;
    }

    public Float getMaxInterestRate() {
        return maxInterestRate;
    }

    public void setMaxInterestRate(Float maxInterestRate) {
        this.maxInterestRate = maxInterestRate;
    }

    public Float getEarlyRepaymentFee() {
        return earlyRepaymentFee;
    }

    public void setEarlyRepaymentFee(Float earlyRepaymentFee) {
        this.earlyRepaymentFee = earlyRepaymentFee;
    }

    public Float getOverdueInterestRate1() {
        return overdueInterestRate1;
    }

    public void setOverdueInterestRate1(Float overdueInterestRate1) {
        this.overdueInterestRate1 = overdueInterestRate1;
    }

    public String getOverdueTime1() {
        return overdueTime1;
    }

    public void setOverdueTime1(String overdueTime1) {
        this.overdueTime1 = overdueTime1;
    }

    public Float getOverdueInterestRate2() {
        return overdueInterestRate2;
    }

    public void setOverdueInterestRate2(Float overdueInterestRate2) {
        this.overdueInterestRate2 = overdueInterestRate2;
    }

    public String getOverdueTime2() {
        return overdueTime2;
    }

    public void setOverdueTime2(String overdueTime2) {
        this.overdueTime2 = overdueTime2;
    }

    public Float getOverdueInterestRate3() {
        return overdueInterestRate3;
    }

    public void setOverdueInterestRate3(Float overdueInterestRate3) {
        this.overdueInterestRate3 = overdueInterestRate3;
    }

    public String getOverdueTime3() {
        return overdueTime3;
    }

    public void setOverdueTime3(String overdueTime3) {
        this.overdueTime3 = overdueTime3;
    }
}
