package com.hellmoney.thca.network;


import com.hellmoney.thca.model.NoticeRes;
import com.hellmoney.thca.model.Request;
import com.hellmoney.thca.model.RequestRes;
import com.hellmoney.thca.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by len on 2017. 6. 13..
 */

public interface NetworkService {

    @FormUrlEncoded
    @POST("/consultant/login")
    Call<User> login(@Field("agent_id") String user_id,
                     @Field("password") int content_id);
    /*
    {
     msg:
             "Success Log-in"
    }
     */
    @FormUrlEncoded
    @POST("/consultant/signup")
    Call<User> signup(@Field("email") String email,
                     @Field("password") int password,
                     @Field("name") int name,
                     @Field("register_number") int registerNumber,
                     @Field("fcm_token") int fcmToken);

    @FormUrlEncoded
    @POST("/consultant/logout")
    Call<User> signout();

    @GET("/consultants/requests/agent/{agentId}")
    Call<RequestRes> getRequests(@Path("agentId") String agentId);

    @FormUrlEncoded
    @POST("/consultants/requests/agent/{agentId}")
    Call<Request> addRequest(@Path("agentId") int agentId,
                             @Field("request_id") int request_id,
                             @Field("itemBank") String itemBank,
                             @Field("itemName") String itemName,
                             @Field("interestRate") String interestRate,
                             @Field("repaymentType") String repaymentType,
                             @Field("overdueInterestRate01") String overdueInterestRate01,
                             @Field("overdueInterestRate02") String overdueInterestRate02,
                             @Field("overdueInterestRate03") String overdueInterestRate03,
                             @Field("overdueTime01") String overdueTime01,
                             @Field("overdueTime02") String overdueTime02,
                             @Field("overdueTime03") String overdueTime03,
                             @Field("earlyRepaymentFee") String earlyRepaymentFee,
                             @Field("repaymentType") String fcmToken);
/*
request_id:요청ID
itemBank:상품은행
itemName:상품명
interestRate : 금리
interestRateType : 금리 종류
repaymentType : 상환방식
overdueInterestRate01
overdueInterestRate02,
overdueInterestRate03,
overdueTime01,
overdueTime02,
overdueTime03,
earlyRepaymentFee : 중도 상환 수수료
 */

//    @GET("/consultants/requests/{requestId}/{agentId}")
//    Call<Request> getRequests(@Path("requestId") String requestId,
//                              @Path("agentId") String agentId);


    @GET("/commons/notices")
    Call<NoticeRes> getNotices();


}
