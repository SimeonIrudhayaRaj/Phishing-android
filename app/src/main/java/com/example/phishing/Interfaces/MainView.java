package com.example.phishing.Interfaces;

public interface MainView {
    void showVerifyResult(String message,String url);
    void showFeedBackAlert();
    String getUrl();
    void makeInvalidUrlAlert();
}
