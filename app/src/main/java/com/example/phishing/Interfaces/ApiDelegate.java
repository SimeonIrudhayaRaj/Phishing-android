package com.example.phishing.Interfaces;

public interface ApiDelegate {
    public void processCheckResult(int result, String url);
    public void processFeedbackResult(Boolean success);
}
