package com.example.phishing.Interfaces;

public interface ApiHandler {
    String makeVerifyRequest(String url);
    void makeFeedbackCall(String url, Boolean highChance);
}
