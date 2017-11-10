package com.hellmoney.thca.module.network.networkData;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by len on 2017. 6. 13..
 */

public class RequestRes {

    @SerializedName("msg")
    private String message;
    @SerializedName("data")
    private List<Request> mRequests;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Request> getRequests() {
        return mRequests;
    }

    public void setRequests(List<Request> requests) {
        mRequests = requests;
    }
}

/*
{
  "msg": "success",
  "data": [
    {
      "count": 5,
      "favorite": 0,
      "request_id": 1,
      "customer_id": "1",
      "selected_estimate_id": 1,
      "loan_type": "주택담보대출",
      "loan_amount": 53400,
      "scheduled_time": "2017-04-04T15:00:00.000Z",
      "overdue_record": "no",
      "interest_rate_type": null,
      "loan_period": 30,
      "loan_reason": null,
      "register_time": "2017-06-07T01:44:08.000Z",
      "start_time": null,
      "end_time": null,
      "extra": null,
      "job_type": null,
      "status": null,
      "region_1": null,
      "region_2": null,
      "region_3": null,
      "apt_name": null,
      "apt_kb_id": null,
      "apt_price": null,
      "apt_size_supply": null,
      "apt_size_exclusive": null,
      "estimate_id": 4,
      "agent_id": "test1@naver.com",
      "item_bank": null,
      "item_name": null,
      "interest_rate": null,
      "repayment_type": null,
      "overdue_interest_rate_1": null,
      "overdue_inertest_rate_2": null,
      "overdue_inertest_rate_3": null,
      "overdue_time_1": null,
      "overdue_time_2": null,
      "overdue_time_3": null,
      "early_repayment_fee": null
    },
    {
      "count": 1,
      "favorite": 0,
      "request_id": 2,
      "customer_id": "2",
      "selected_estimate_id": 8,
      "loan_type": "주택담보대출2",
      "loan_amount": 534000,
      "scheduled_time": "2017-04-04T15:00:00.000Z",
      "overdue_record": "no",
      "interest_rate_type": null,
      "loan_period": 30,
      "loan_reason": null,
      "register_time": "2017-06-07T01:44:08.000Z",
      "start_time": null,
      "end_time": null,
      "extra": null,
      "job_type": null,
      "status": null,
      "region_1": null,
      "region_2": null,
      "region_3": null,
      "apt_name": null,
      "apt_kb_id": null,
      "apt_price": null,
      "apt_size_supply": null,
      "apt_size_exclusive": null,
      "estimate_id": 12,
      "agent_id": "test2@naver.com",
      "item_bank": null,
      "item_name": null,
      "interest_rate": null,
      "repayment_type": null,
      "overdue_interest_rate_1": null,
      "overdue_inertest_rate_2": null,
      "overdue_inertest_rate_3": null,
      "overdue_time_1": null,
      "overdue_time_2": null,
      "overdue_time_3": null,
      "early_repayment_fee": null
    },
    {
      "count": 7,
      "favorite": 0,
      "request_id": 3,
      "customer_id": "3",
      "selected_estimate_id": 14,
      "loan_type": "주택담보대출3",
      "loan_amount": 5340001,
      "scheduled_time": "2017-04-04T15:00:00.000Z",
      "overdue_record": "no",
      "interest_rate_type": null,
      "loan_period": 30,
      "loan_reason": null,
      "register_time": "2017-06-07T01:44:08.000Z",
      "start_time": null,
      "end_time": null,
      "extra": null,
      "job_type": null,
      "status": null,
      "region_1": null,
      "region_2": null,
      "region_3": null,
      "apt_name": null,
      "apt_kb_id": null,
      "apt_price": null,
      "apt_size_supply": null,
      "apt_size_exclusive": null,
      "estimate_id": 13,
      "agent_id": "test3@naver.com",
      "item_bank": null,
      "item_name": null,
      "interest_rate": null,
      "repayment_type": null,
      "overdue_interest_rate_1": null,
      "overdue_inertest_rate_2": null,
      "overdue_inertest_rate_3": null,
      "overdue_time_1": null,
      "overdue_time_2": null,
      "overdue_time_3": null,
      "early_repayment_fee": null
    }
  ]
}
 */