package com.example.phishing.Assemblers;

import com.example.phishing.Implementations.ApiHandlerRetroFitImpl;
import com.example.phishing.Interfaces.ApiDelegate;
import com.example.phishing.Interfaces.ApiHandler;

public class ApiHandlerAssemler {
    public static ApiHandler createInstance(ApiDelegate delegate) {
        return new ApiHandlerRetroFitImpl(delegate);
    }
}
