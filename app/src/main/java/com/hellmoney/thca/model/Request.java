package com.hellmoney.thca.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by len on 2017. 6. 13..
 */

public class Request {
    /*
    상세보기에 필요한 변수들은 모임
     */
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

    public Double getLimiteAmount() {
        return Double.parseDouble(loanAmount) * 0.7;
    }

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

    public int getEstiamteCount() {
        return estiamteCount;
    }

    public int getBankCount() {
        return bankCount;
    }

    public int getFavorite() {
        return favorite;
    }

    public String getCustomerId() {
        return customerId;
    }

    private String totalAddress;
    private String Size;

    public String getSize() {
        return getExclusiveSize() + "/" + getSupplySize() + "m3";
    }

    public String getTotalAddress() {
        return getAddress1() + " " + getAddress2() + " " + getAddress3();
    }

    public String getCountEstimate() {
        return "견적 " + countEstimate + "건";
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getSelectedEstimateId() {
        return selectedEstimateId;
    }

    public void setSelectedEstimateId(int selectedEstimateId) {
        this.selectedEstimateId = selectedEstimateId;
    }

    public String getOverdueRecord() {
        return overdueRecord;
    }

    public void setOverdueRecord(String overdueRecord) {
        this.overdueRecord = overdueRecord;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Date getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Date scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getInterestRateType() {
        return interestRateType;
    }

    public void setInterestRateType(String interestRateType) {
        this.interestRateType = interestRateType;
    }

    public String getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(String loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public String getLoanReason() {
        return loanReason;
    }

    public void setLoanReason(String loanReason) {
        this.loanReason = loanReason;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getEndTime() {

//        long time = System.currentTimeMillis();
//        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
//        String currentTime = dayTime.format(new Date(time));
//TODO 시간 해결해야함.
//        Log.d("LEN", currentTime + "GG" + endTime);
        return endTime;

    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAptName() {
        return aptName;
    }

    public void setAptName(String aptName) {
        this.aptName = aptName;
    }

    public String getKbId() {
        return kbId;
    }

    public void setKbId(String kbId) {
        this.kbId = kbId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Float getSupplySize() {
        return supplySize;
    }

    public void setSupplySize(Float supplySize) {
        this.supplySize = supplySize;
    }

    public Float getExclusiveSize() {
        return exclusiveSize;
    }

    public void setExclusiveSize(Float exclusiveSize) {
        this.exclusiveSize = exclusiveSize;
    }

    public int getEstimateId() {
        return estimateId;
    }

    public void setEstimateId(int estimateId) {
        this.estimateId = estimateId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getItemBank() {
        return itemBank;
    }

    public void setItemBank(String itemBank) {
        this.itemBank = itemBank;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType;
    }

    public String getOverdueInterestRate1() {
        return overdueInterestRate1;
    }

    public void setOverdueInterestRate1(String overdueInterestRate1) {
        this.overdueInterestRate1 = overdueInterestRate1;
    }

    public String getOverdueInterestRate2() {
        return overdueInterestRate2;
    }

    public void setOverdueInterestRate2(String overdueInterestRate2) {
        this.overdueInterestRate2 = overdueInterestRate2;
    }

    public String getOverdueInterestRate3() {
        return overdueInterestRate3;
    }

    public void setOverdueInterestRate3(String overdueInterestRate3) {
        this.overdueInterestRate3 = overdueInterestRate3;
    }

    public String getOverdueTime1() {
        return overdueTime1;
    }

    public void setOverdueTime1(String overdueTime1) {
        this.overdueTime1 = overdueTime1;
    }

    public String getOverdueTime2() {
        return overdueTime2;
    }

    public void setOverdueTime2(String overdueTime2) {
        this.overdueTime2 = overdueTime2;
    }

    public String getOverdueTime3() {
        return overdueTime3;
    }

    public void setOverdueTime3(String overdueTime3) {
        this.overdueTime3 = overdueTime3;
    }

    public String getEarlyRepaymentFee() {
        return earlyRepaymentFee;
    }

    public void setEarlyRepaymentFee(String earlyRepaymentFee) {
        this.earlyRepaymentFee = earlyRepaymentFee;
    }

    public String getFixedLoanAmount() {
        return fixedLoanAmount;
    }

    public void setFixedLoanAmount(String fixedLoanAmount) {
        this.fixedLoanAmount = fixedLoanAmount;
    }


    /*
    "count": 7,
            "favorite": 0,
            "request_id": 1,
            "customer_id": "00000000-3ee4-2457-ffff-ffffcb49f42d",
            "selected_estimate_id": 1,
            "loan_type": "주택담보대출",
            "loan_amount": 17000,
            "scheduled_time": "2017-07-15T00:00:00.000Z",
            "overdue_record": null,
            "interest_rate_type": "고정",
            "loan_period": 30,
            "loan_reason": null,
            "register_time": "0000-00-00 00:00:00",
            "start_time": "2017-06-12T17:00:00.000Z",
            "end_time": "2017-06-12T17:00:00.000Z",
            "extra": null,
            "job_type": "직장근로자",
            "status": "상담중",
            "region_1": "서울시",
            "region_2": "양천구",
            "region_3": "신정동",
            "apt_name": "건영",
            "apt_kb_id": "A120",
            "apt_price": 30000,
            "apt_size_supply": 112.54,
            "apt_size_exclusive": 84.95,
            "estimate_id": 1,
            "agent_id": "agent1@naver.com",
            "item_bank": "하나은행",
            "item_name": "하나으뜸모기지",
            "interest_rate": 3.3,
            "repayment_type": "원리금균등상환",
            "overdue_interest_rate_1": 5,
            "overdue_interest_rate_2": 6,
            "overdue_interest_rate_3": 7,
            "overdue_time_1": "30일 미만",
            "overdue_time_2": "90일 미만",
            "overdue_time_3": "90일 이상",
            "early_repayment_fee": 1.5,
            "fixed_loan_amount": 0
}
     */
}
