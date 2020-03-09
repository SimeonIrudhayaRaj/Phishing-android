package com.example.phishing.Implementations;

import android.telephony.data.ApnSetting;
import android.util.Patterns;
import android.webkit.URLUtil;

import com.example.phishing.Interfaces.ApiHandler;
import com.example.phishing.Interfaces.MainView;
import com.example.phishing.Interfaces.ViewModel;

import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.http.Url;

public class ViewModelImpl implements ViewModel {
    ApiHandler apiHandler;
    MainView view;
    public ViewModelImpl(ApiHandler apiHandler, MainView view) {
        this.apiHandler = apiHandler;
        this.view = view;
    }
    @Override
    public void verifyButtonPressed(String urlString) {
        if (!isVailUrl(urlString)) {
            this.view.makeInvalidUrlAlert();
            return;
        }
        apiHandler.makeVerifyRequest(urlString);
        view.showVerifyResult("ok", urlString);
    }

    @Override
    public void feedBackButtonPressed(String url) {
        if (!isVailUrl(url)) {
            this.view.makeInvalidUrlAlert();
            return;
        }
        view.showFeedBackAlert();
    }

    @Override
    public void feedbackAlertButtonpressed(Boolean chance) {
        apiHandler.makeFeedbackCall(view.getUrl(), chance);
    }

    private boolean isVailUrl(String urlString) {
        return URLUtil.isValidUrl(urlString) && Patterns.WEB_URL.matcher(urlString).matches();
    }
}
