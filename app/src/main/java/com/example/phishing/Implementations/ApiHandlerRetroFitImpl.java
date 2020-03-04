package com.example.phishing.Implementations;

import com.example.phishing.DataModels.ResponseModel;
import com.example.phishing.Interfaces.ApiHandler;
import com.example.phishing.Interfaces.ApiServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHandlerRetroFitImpl implements ApiHandler {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    @Override
    public String makeVerifyRequest(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(this.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices api = retrofit.create(ApiServices.class);
        Call<List<ResponseModel>> call = api.verify(url);
        call.enqueue(new Callback<List<ResponseModel>>() {
            @Override
            public void onResponse(Call<List<ResponseModel>> call, Response<List<ResponseModel>> response) {

            }

            @Override
            public void onFailure(Call<List<ResponseModel>> call, Throwable t) {

            }
        });

        return null;
    }

    @Override
    public void makeFeedbackCall(String url, Boolean highChance) {

    }
}
