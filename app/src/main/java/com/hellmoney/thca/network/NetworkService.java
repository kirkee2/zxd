package com.hellmoney.thca.network;

import com.hellmoney.thca.model.EstimateRes;
import com.hellmoney.thca.model.ItemDetailRes;
import com.hellmoney.thca.model.ItemRes;
import com.hellmoney.thca.model.LikeRes;
import com.hellmoney.thca.model.NoticeDetailRes;
import com.hellmoney.thca.model.NoticeRes;
import com.hellmoney.thca.model.Request;
import com.hellmoney.thca.model.RequestRes;
import com.hellmoney.thca.model.SingleEstimateRes;
import com.hellmoney.thca.model.SingleRequestRes;
import com.hellmoney.thca.model.User;

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
    Call<ItemRes> getItemsByAgentId(@Query("agentId") String agentId);

    @FormUrlEncoded
    @POST("/consultants/items")
    Call<String> AddItem(@Field("agentId") String agentId,
                         @Field("itemBank") String itemBank,
                         @Field("itemName") String itemName,
                         @Field("minInterestrate") Float minInterestrate,
                         @Field("maxInterestrate") Float maxInterestrate,
                         @Field("interestRateType") String interestRateType,
                         @Field("repaymentType") String repaymentType,
                         @Field("overdueInterestRate01") Float overdueInterestRate01,
                         @Field("overdueInterestRate02") Float overdueInterestRate02,
                         @Field("overdueInterestRate03") Float overdueInterestRate03,
                         @Field("overdueTime01") String overdueTime01,
                         @Field("overdueTime02") String overdueTime02,
                         @Field("overdueTime03") String overdueTime03,
                         @Field("earlyRepaymentFee") Float earlyRepaymentFee);

    @GET("/consultants/items/{itemId}")
    Call<ItemDetailRes> getItem(@Path("itemId") int itemId);

    @FormUrlEncoded
    @PUT("/consultants/items/{itemId}")
    Call<String> updateItem(@Path("itemId") int itemId,
                            @Field("itemBank") String itemBank,
                            @Field("itemName") String itemName,
                            @Field("minInterestrate") Float minInterestrate,
                            @Field("maxInterestrate") Float maxInterestrate,
                            @Field("interestRateType") String interestRateType,
                            @Field("repaymentType") String repaymentType,
                            @Field("overdueInterestRate01") Float overdueInterestRate01,
                            @Field("overdueInterestRate02") Float overdueInterestRate02,
                            @Field("overdueInterestRate03") Float overdueInterestRate03,
                            @Field("overdueTime01") String overdueTime01,
                            @Field("overdueTime02") String overdueTime02,
                            @Field("overdueTime03") String overdueTime03,
                            @Field("earlyRepaymentFee") Float earlyRepaymentFee);

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


}
