package com.example.phishing.Implementations;

import android.telephony.data.ApnSetting;
import android.util.Log;
import android.util.Patterns;
import android.webkit.URLUtil;

import com.example.phishing.Assemblers.ApiHandlerAssemler;
import com.example.phishing.Interfaces.ApiDelegate;
import com.example.phishing.Interfaces.ApiHandler;
import com.example.phishing.Interfaces.MainView;
import com.example.phishing.Interfaces.ViewModel;

import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.http.Url;

public class ViewModelImpl implements ViewModel, ApiDelegate {
    ApiHandler apiHandler;
    MainView view;
    public ViewModelImpl(MainView view) {
        this.apiHandler = ApiHandlerAssemler.createInstance(this);
        this.view = view;
    }
    @Override
    public void verifyButtonPressed(String urlString) {
        if (!isVailUrl(urlString)) {
            this.view.makeInvalidUrlAlert();
            return;
        }
        apiHandler.makeVerifyRequest(urlString);
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
        if (chance) {
            apiHandler.makeFeedbackCall(view.getUrl(), -1);
        } else {
            apiHandler.makeFeedbackCall(view.getUrl(), 1);
        }
    }

    private boolean isVailUrl(String urlString) {
        return URLUtil.isValidUrl(urlString) && Patterns.WEB_URL.matcher(urlString).matches();
    }

    @Override
    public void processResult(int result, String url) {
        String message;
        Log.v("aaaaaaaaaaaa", result + url);
        switch (result) {
            case -1:
                message = "unSafe";
                break;
            case 1:
                message = "safe";
                break;
            default:
                message = "error";
        }
        view.showVerifyResult(message, url);
    }
}
