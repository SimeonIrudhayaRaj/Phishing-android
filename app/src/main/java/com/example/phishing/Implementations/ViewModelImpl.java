package com.example.phishing.Implementations;

import android.telephony.data.ApnSetting;

import com.example.phishing.Interfaces.ApiHandler;
import com.example.phishing.Interfaces.MainView;
import com.example.phishing.Interfaces.ViewModel;

public class ViewModelImpl implements ViewModel {
    ApiHandler apiHandler;
    MainView view;
    public ViewModelImpl(ApiHandler apiHandler, MainView view) {
        this.apiHandler = apiHandler;
        this.view = view;
    }
    @Override
    public void verifyButtonPressed(String url) {
        apiHandler.makeVerifyRequest(url);
        view.showVerifyResult("ok");
    }

    @Override
    public void feedBackButtonPressed() {
        view.showFeedBackAlert();
    }

    @Override
    public void feedbackAlertButtonpressed(Boolean chance) {
        apiHandler.makeFeedbackCall(view.getUrl(), chance);
    }
}
