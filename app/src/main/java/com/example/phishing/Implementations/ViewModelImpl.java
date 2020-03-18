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
        view.showProgressBar();
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
        view.showProgressBar();
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
    public void processCheckResult(int result, String url) {
        view.hideProgressBar();
        switch (result) {
            case -1:
                view.showUnsafeUrlAlert(url);
                break;
            case 1:
                view.showSafeUrlAlert(url);
                break;
            default:
                view.showErrorAlert();
        }
    }

    @Override
    public void processFeedbackResult(Boolean success) {
        view.hideProgressBar();
        if (success ){
            view.showSucessFeedback();
        } else {
            view.showErrorAlert();
        }
    }
}
