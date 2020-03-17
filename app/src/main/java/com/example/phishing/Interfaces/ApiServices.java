package com.example.phishing.Interfaces;
import com.example.phishing.DataModels.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("check/")
    Call<ResponseModel> verify(@Query("url") String url);

    @GET("feedback/")
    Call<String> feedback(@Query("url") String url,
                          @Query("feedback") int chance);
}
