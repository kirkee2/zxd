package com.hellmoney.thca.module.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {
    private static final String baseUrl = "http://52.78.186.37:3000";
//    private static final String baseUrl = "http://127.0.0.1:3000";

    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .registerTypeAdapter(Date.class, new GsonUTCDateAdapter())
            .create();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public static NetworkService service = retrofit.create(NetworkService.class);
}
