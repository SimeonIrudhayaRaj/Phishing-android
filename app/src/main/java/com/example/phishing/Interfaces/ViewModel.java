package com.example.phishing.Interfaces;

public interface ViewModel {
    ApiHandler apiHandler = null;
    MainView view = null;
    void verifyButtonPressed(String url);
    void feedBackButtonPressed(String url);
    void feedbackAlertButtonpressed(Boolean chance);
}
