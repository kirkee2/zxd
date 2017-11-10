package com.hellmoney.thca.module.network.networkData;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by len on 2017. 6. 15..
 */

public class Estimate {

    @SerializedName("msg")
    private String msg;

    @SerializedName("scheduled_time")
    private Date scheduledTime;

    public String getFixedLoanAmount() {
        return fixedLoanAmount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @SerializedName("phone_number")
    private String phoneNumber;

    @SerializedName("fixed_loan_amount")
    private String fixedLoanAmount;

    @SerializedName("estimate_id")
    private int estimateId;

    @SerializedName("status")
    private String status;

    @SerializedName("end_time")
    private Date endTime;

    @SerializedName("region_1")
    private String address1;

    @SerializedName("region_2")
    private String address2;

    @SerializedName("region_3")
    private String address3;

    @SerializedName("apt_name")
    private String aptName;

    @SerializedName("apt_kb_id")
    private String kbId;

    @SerializedName("apt_price")
    private String price;

    @SerializedName("apt_size_supply")
    private Float supplySize;

    @SerializedName("apt_size_exclusive")
    private Float exclusiveSize;

    @SerializedName("request_id")
    private int requestId;

    @SerializedName("agent_id")
    private String agentId;

    @SerializedName("register_time")
    private String registerTime;

    @SerializedName("item_bank")
    private String itemBank;

    @SerializedName("item_name")
    private String itemName;

    @SerializedName("interest_rate")
    private String interestRate;

    @SerializedName("interest_rate_type")
    private String interestRateType;

    @SerializedName("repayment_type")
    private String repaymentType;

    @SerializedName("overdue_interest_rate_1")
    private String overdueInterestRate1;

    @SerializedName("overdue_interest_rate_2")
    private String overdueInterestRate2;

    @SerializedName("overdue_interest_rate_3")
    private String overdueInterestRate3;

    @SerializedName("overdue_time_1")
    private String overdueTime1;

    @SerializedName("overdue_time_2")
    private String overdueTime2;

    @SerializedName("overdue_time_3")
    private String overdueTime3;

    @SerializedName("early_repayment_fee")
    private String earlyRepaymentFee;

    @SerializedName("job_type")
    private String jobType;

    @SerializedName("overdue_record")
    private String overdueRecord;

    @SerializedName("loan_type")
    private String loanType;

    @SerializedName("loan_amount")
    private String loanAmount;


    @SerializedName("selected_estimate_id")
    private String selectedEstimateId;

    public String getSelectedEstimateId() {
        return selectedEstimateId;
    }


    public Date getScheduledTime() {
        return scheduledTime;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public Double getLimiteAmount() {

        return Double.parseDouble(loanAmount) * 0.7;
    }

    public String getLoanType() {
        return loanType;
    }

    public String getOverdueRecord() {
        return overdueRecord;
    }

    public String getJobType() {
        return jobType;
    }

    public String getStatus() {
        return status;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getTotalAddress() {
        return address1 + " " + address2 + " " + address3;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getAddress3() {
        return address3;
    }

    public String getAptName() {
        return aptName;
    }

    public String getKbId() {
        return kbId;
    }

    public String getPrice() {
        return price;
    }

    public Float getSupplySize() {
        return supplySize;
    }

    public Float getExclusiveSize() {
        return exclusiveSize;
    }

    public String getSize() {
        return supplySize + "\u33A1" + " / " + exclusiveSize + "\u33A1";
    }

    public String getMsg() {
        return msg;
    }

    public int getEstimateId() {
        return estimateId;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getAgentId() {
        return agentId;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public String getItemBank() {
        return itemBank;
    }

    public String getItemName() {
        return itemName;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public String getInterestRateType() {
        return interestRateType;
    }

    public String getRepaymentType() {
        return repaymentType;
    }

    public String getOverdueInterestRate1() {
        return overdueInterestRate1;
    }

    public String getOverdueInterestRate2() {
        return overdueInterestRate2;
    }

    public String getOverdueInterestRate3() {
        return overdueInterestRate3;
    }

    public String getOverdueTime1() {
        return overdueTime1;
    }

    public String getOverdueTime2() {
        return overdueTime2;
    }

    public String getOverdueTime3() {
        return overdueTime3;
    }

    public String getEarlyRepaymentFee() {
        return earlyRepaymentFee;
    }
}
