package com.hellmoney.thca.network;

import com.hellmoney.thca.model.NoticeDetailRes;
import com.hellmoney.thca.model.NoticeRes;
import com.hellmoney.thca.model.Request;
import com.hellmoney.thca.model.RequestRes;
import com.hellmoney.thca.model.SingleRequestRes;
import com.hellmoney.thca.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NetworkService {
    @FormUrlEncoded
    @POST("/consultant/login")
    Call<User> login(@Field("agent_id") String user_id,
                     @Field("password") int content_id);

    @FormUrlEncoded
    @POST("/consultant/signup")
    Call<User> signup(@Field("email") String email,
                      @Field("password") int password,
                      @Field("name") int name,
                      @Field("register_number") int registerNumber,
                      @Field("fcm_token") int fcmToken);

    @FormUrlEncoded
    @POST("/consultants/requests/agent/{agentId}")
    Call<Request> addRequest(@Path("agentId") String agentId,

                             @Field("fixedLoanAmount") String fixedLoanAmount,
                             @Field("requestId") int requestId,
                             @Field("agentId") String agentId2,
                             @Field("itemBank") String itemBank,
                             @Field("itemName") String itemName,
                             @Field("interestRate") String interestRate,
                             @Field("interestRateType") String interestRateType,
                             @Field("overdueInterestRate01") String overdueInterestRate01,
                             @Field("overdueInterestRate02") String overdueInterestRate02,
                             @Field("overdueInterestRate03") String overdueInterestRate03,
                             @Field("overdueTime01") String overdueTime01,
                             @Field("overdueTime02") String overdueTime02,
                             @Field("overdueTime03") String overdueTime03,
                             @Field("earlyRepaymentFee") String earlyRepaymentFee,
                             @Field("repaymentType") String repaymentType);

    @FormUrlEncoded
    @POST("/consultant/logout")
    Call<User> signout();

    @GET("/consultants/requests/agent/{agentId}")
    Call<RequestRes> getRequests(@Path("agentId") String agentId);

//    request_id:요청ID
//    itemBank:상품은행
//    itemName:상품명
//    interestRate : 금리
//    interestRateType : 금리 종류
//    repaymentType : 상환방식
//            overdueInterestRate01
//    overdueInterestRate02,
//    overdueInterestRate03,
//    overdueTime01,
//    overdueTime02,
//    overdueTime03,
//    earlyRepaymentFee : 중도 상환 수수료

    @GET("/consultants/requests/{requestId}/{agentId}")
    Call<SingleRequestRes> getRequest(@Path("requestId") String requestId,
                                      @Path("agentId") String agentId);

    @GET("/commons/notices")
    Call<NoticeRes> getNotices();

//    /consultants/requests/:requestId/:agentId

    @GET("/commons/notices/{noticeId}")
    Call<NoticeDetailRes> getNotice(@Path("noticeId") int noticeId);
}