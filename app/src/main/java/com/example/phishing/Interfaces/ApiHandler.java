package com.example.phishing.Interfaces;

public interface ApiHandler {
    ApiDelegate delegate = null;
    String makeVerifyRequest(String url);
    void makeFeedbackCall(String url, int chance);
}
