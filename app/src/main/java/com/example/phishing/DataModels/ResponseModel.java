package com.example.phishing.DataModels;

import com.google.gson.annotations.SerializedName;

public class ResponseModel {
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;

    public ResponseModel(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public  int getStatus() {
        return  status;
    }
}