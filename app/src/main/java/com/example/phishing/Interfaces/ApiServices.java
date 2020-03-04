package com.example.phishing.Interfaces;
import com.example.phishing.DataModels.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("/verify")
    Call<List<ResponseModel>> verify(@Query("url") String url);
}
