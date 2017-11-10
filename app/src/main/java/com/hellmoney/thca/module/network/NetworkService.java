package com.hellmoney.thca.module.network;

import com.hellmoney.thca.module.network.networkData.AgentDetailRes;
import com.hellmoney.thca.module.network.networkData.EstimateRes;
import com.hellmoney.thca.module.network.networkData.ItemDetailRes;
import com.hellmoney.thca.module.network.networkData.ItemRes;
import com.hellmoney.thca.module.network.networkData.LikeRes;
import com.hellmoney.thca.module.network.networkData.NoticeDetailRes;
import com.hellmoney.thca.module.network.networkData.NoticeRes;
import com.hellmoney.thca.module.network.networkData.Request;
import com.hellmoney.thca.module.network.networkData.RequestRes;
import com.hellmoney.thca.module.network.networkData.SingleEstimateRes;
import com.hellmoney.thca.module.network.networkData.SingleRequestRes;
import com.hellmoney.thca.module.network.networkData.SingleRes;
import com.hellmoney.thca.module.network.networkData.User;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkService {
    @FormUrlEncoded
    @POST("/consultants/agents/joinSvc")
    Call<SingleRes> signupLocal(@Field("name") String name,
                           @Field("nickname") String nickname,
                           @Field("greeting") String greeting,
                           @Field("bankCode") int bankCode,
                           @Field("companyName") String companyName,
                           @Field("registerNumber") int registerNumber,
                           @Field("region1") String region1,
                           @Field("region2") String region2);

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
                             @Field("repaymentType") String repaymentType
                             );

//    @Field("overdueInterestRate1") String overdueInterestRate1,
//    @Field("overdueInterestRate2") String overdueInterestRate2,
//    @Field("overdueInterestRate3") String overdueInterestRate3,
//    @Field("overdueTime1") String overdueTime1,
//    @Field("overdueTime2") String overdueTime2,
//    @Field("overdueTime3") String overdueTime3,
//    @Field("earlyRepaymentFee") String earlyRepaymentFee

    @FormUrlEncoded
    @POST("/consultant/logout")
    Call<User> signout();

    @GET("/consultants/requests/agent/{agentId}")
    Call<RequestRes> getRequests(@Path("agentId") String agentId);

    @GET("/consultants/requests/{requestId}/{agentId}")
    Call<SingleRequestRes> getRequest(@Path("requestId") String requestId,
                                      @Path("agentId") String agentId);

    //
    @GET("/clients/estimates")
    Call<RequestRes> getLoanRate(@Query("requestId") String requestId);


    @GET("/commons/notices")
    Call<NoticeRes> getNotices();

//    /consultants/requests/:requestId/:agentId

    @GET("/commons/notices/{noticeId}")
    Call<NoticeDetailRes> getNotice(@Path("noticeId") int noticeId);

    @GET("/consultants/items")
    Call<ItemRes> getItemsByAgentId(@Query("agentId") String agentId, @Query("loanType") String loanType);

    @FormUrlEncoded
    @POST("/consultants/items")
    Call<String> AddItem(@Field("agentId") String agentId,
                         @Field("itemBank") String itemBank,
                         @Field("itemName") String itemName,
                         @Field("minInterestRate") Float minInterestRate,
                         @Field("maxInterestRate") Float maxInterestRate,
                         @Field("interestRateType") String interestRateType,
                         @Field("repaymentType") String repaymentType,
                         @Field("overdueInterestRate1") Float overdueInterestRate1,
                         @Field("overdueInterestRate2") Float overdueInterestRate2,
                         @Field("overdueInterestRate3") Float overdueInterestRate3,
                         @Field("overdueTime1") String overdueTime1,
                         @Field("overdueTime2") String overdueTime2,
                         @Field("overdueTime3") String overdueTime3,
                         @Field("earlyRepaymentFee") Float earlyRepaymentFee,
                         @Field("loanType") String loanType);

    @GET("/consultants/items/{itemId}")
    Call<ItemDetailRes> getItem(@Path("itemId") int itemId);

    @FormUrlEncoded
    @PUT("/consultants/items/{itemId}")
    Call<String> updateItem(@Path("itemId") int itemId,
                            @Field("itemBank") String itemBank,
                            @Field("itemName") String itemName,
                            @Field("minInterestRate") Float minInterestRate,
                            @Field("maxInterestRate") Float maxInterestRate,
                            @Field("interestRateType") String interestRateType,
                            @Field("repaymentType") String repaymentType,
                            @Field("overdueInterestRate1") Float overdueInterestRate1,
                            @Field("overdueInterestRate2") Float overdueInterestRate2,
                            @Field("overdueInterestRate3") Float overdueInterestRate3,
                            @Field("overdueTime1") String overdueTime1,
                            @Field("overdueTime2") String overdueTime2,
                            @Field("overdueTime3") String overdueTime3,
                            @Field("earlyRepaymentFee") Float earlyRepaymentFee,
                            @Field("loanType") String loanType);

    @DELETE("/consultants/items/{itemId}")
    Call<String> deleteItem(@Path("itemId") int itemId);

    @POST("/consultants/likes/agents/{agentId}/requests/{requestId}")
    Call<LikeRes> like(@Path("agentId") String agentId,
                       @Path("requestId") int requestId);

    @DELETE("/consultants/likes/agents/{agentId}/requests/{requestId}")
    Call<LikeRes> unlike(@Path("agentId") String agentId,
                         @Path("requestId") int requestId);

    @GET("/consultants/estimates")
    Call<EstimateRes> getMyEstimate(@Query("agentId") String agentId);

    @GET("/consultants/estimates/{estimateId}")
    Call<SingleEstimateRes> getSingleMyEstimte(@Path("estimateId") String estimateId);

    @FormUrlEncoded
    @PUT("/consultants/estimates/{estimateId}")
    Call<SingleRes> setStatus(@Path("estimateId") int estimateId,
                              @Field("status") String status);

    @GET("/clients/agents/{agentId}")
    Call<AgentDetailRes> getAgent(@Path("agentId") String agentId);
}
