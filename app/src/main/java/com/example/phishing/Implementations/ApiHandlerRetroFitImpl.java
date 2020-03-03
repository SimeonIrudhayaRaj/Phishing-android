package com.example.phishing.Implementations;

import com.example.phishing.Interfaces.ApiHandler;

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


        return null;
    }

    @Override
    public void makeFeedbackCall(String url, Boolean highChance) {

    }
}
