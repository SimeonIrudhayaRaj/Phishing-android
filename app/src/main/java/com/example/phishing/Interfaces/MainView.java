package com.example.phishing.Interfaces;

public interface MainView {
    void showFeedBackAlert();
    String getUrl();
    void makeInvalidUrlAlert();
    void showErrorAlert();
    void showSafeUrlAlert(String url);
    void showUnsafeUrlAlert(String url);
    void showSucessFeedback();
    void hideProgressBar();
    void showProgressBar();
}
