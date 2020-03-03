package com.example.phishing.DataModels;

import com.google.gson.annotations.SerializedName;

public class ResponseModel {
    @SerializedName("status")
    private String status;
    public ResponseModel(String status) {
        this.status = status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
