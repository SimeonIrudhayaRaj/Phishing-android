package com.example.phishing.Implementations;

import com.example.phishing.Constants.UrlConstants;
import com.example.phishing.DataModels.ResponseModel;
import com.example.phishing.Interfaces.ApiDelegate;
import com.example.phishing.Interfaces.ApiHandler;
import com.example.phishing.Interfaces.ApiServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiHandlerRetroFitImpl implements ApiHandler {
    private static Retrofit retrofit;
    ApiDelegate delegate;
    private static final String BASE_URL = UrlConstants.url;
    public ApiHandlerRetroFitImpl(ApiDelegate delegate) {
        this.delegate = delegate;
    }
    @Override
    public String makeVerifyRequest(final String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(this.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices api = retrofit.create(ApiServices.class);
        Call<ResponseModel> call = api.verify(url);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                delegate.processCheckResult(response.body().getStatus(), url);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                delegate.processCheckResult(0, url);

            }
        });

        return null;
    }

    @Override
    public void makeFeedbackCall(String url, int chance) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(this.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        ApiServices api = retrofit.create(ApiServices.class);
        Call<String> call = api.feedback(url, chance);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                delegate.processFeedbackResult(true);
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                delegate.processFeedbackResult(false);
            }
        });
    }
}
