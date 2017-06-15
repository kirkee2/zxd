package com.hellmoney.thca.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by len on 2017. 6. 15..
 */

public class Estimate {

    @SerializedName("msg")
    private String msg;

    @SerializedName("estimate_count")
    private int estiamteCount;

    @SerializedName("bank_count")
    private int bankCount;

    @SerializedName("favorite")
    private int favorite;

    @SerializedName("customer_id")
    private String customerId;

    @SerializedName("count")
    private String countEstimate;

    @SerializedName("request_id")
    private int requestId;

    @SerializedName("selected_estimate_id")
    private int selectedEstimateId;

    @SerializedName("overdue_record")
    private String overdueRecord;

    @SerializedName("loan_type")
    private String loanType;

    @SerializedName("scheduled_time")
    private Date scheduledTime;

    @SerializedName("loan_amount")
    private String loanAmount;

    @SerializedName("interest_rate_type")
    private String interestRateType;

    @SerializedName("loan_period")
    private String loanPeriod;

    @SerializedName("loan_reason")
    private String loanReason;

    @SerializedName("register_time")
    private Date registerTime;

    @SerializedName("end_time")
    private String endTime;

    @SerializedName("extra")
    private String extra;

    @SerializedName("job_type")
    private String jobType;

    @SerializedName("status")
    private String status;

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

    @SerializedName("estimate_id")
    private int estimateId;

    @SerializedName("agent_id")
    private String agentId;

    @SerializedName("item_bank")
    private String itemBank;

    @SerializedName("interest_rate")
    private String interestRate;

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

    @SerializedName("fixed_loan_amount")
    private String fixedLoanAmount;
}
