package com.hellmoney.thca.module.network.networkData;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by len on 2017. 6. 15..
 */

public class Estimate implements Parcelable {

    public Estimate(Parcel in) {
        readFromParcel(in);
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.msg);
        dest.writeSerializable(this.scheduledTime);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.fixedLoanAmount);
        dest.writeInt(this.estimateId);
        dest.writeString(this.status);
        dest.writeSerializable(this.endTime);
        dest.writeString(this.address1);
        dest.writeString(this.address2);
        dest.writeString(this.address3);
        dest.writeString(this.aptName);
        dest.writeString(this.kbId);
        dest.writeString(this.price);
        dest.writeFloat(this.supplySize);
        dest.writeFloat(this.exclusiveSize);
        dest.writeInt(this.requestId);
        dest.writeString(this.agentId);
        dest.writeString(this.registerTime);
        dest.writeString(this.itemBank);
        dest.writeString(this.itemName);
        dest.writeString(this.interestRate);
        dest.writeString(this.interestRateType);
        dest.writeString(this.repaymentType);
        dest.writeString(this.overdueInterestRate1);
        dest.writeString(this.overdueInterestRate2);
        dest.writeString(this.overdueInterestRate3);
        dest.writeString(this.overdueTime1);
        dest.writeString(this.overdueTime2);
        dest.writeString(this.overdueTime3);
        dest.writeString(this.earlyRepaymentFee);
        dest.writeString(this.jobType);
        dest.writeString(this.overdueRecord);
        dest.writeString(this.loanType);
        dest.writeString(this.loanAmount);
        dest.writeString(this.selectedEstimateId);
    }

    private void readFromParcel(Parcel in){
        this.msg = in.readString();
        this.scheduledTime = (java.util.Date) in.readSerializable();
        this.phoneNumber = in.readString();
        this.fixedLoanAmount = in.readString();
        this.estimateId = in.readInt();
        this.status = in.readString();
        this.endTime = (java.util.Date) in.readSerializable();
        this.address1 = in.readString();
        this.address2 = in.readString();
        this.address3 = in.readString();
        this.aptName = in.readString();
        this.kbId = in.readString();
        this.price = in.readString();
        this.supplySize = in.readFloat();
        this.exclusiveSize = in.readFloat();
        this.requestId = in.readInt();
        this.agentId = in.readString();
        this.registerTime = in.readString();
        this.itemBank = in.readString();
        this.itemName = in.readString();
        this.interestRate = in.readString();
        this.interestRateType = in.readString();
        this.repaymentType = in.readString();
        this.overdueInterestRate1 = in.readString();
        this.overdueInterestRate2 = in.readString();
        this.overdueInterestRate3 = in.readString();
        this.overdueTime1 = in.readString();
        this.overdueTime2 = in.readString();
        this.overdueTime3 = in.readString();
        this.earlyRepaymentFee = in.readString();
        this.jobType = in.readString();
        this.overdueRecord = in.readString();
        this.loanType = in.readString();
        this.loanAmount = in.readString();
        this.selectedEstimateId = in.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Estimate createFromParcel(Parcel in) {
            return new Estimate(in);
        }

        @Override
        public Estimate[] newArray(int size) {
            return new Estimate[size];
        }
    };
}
