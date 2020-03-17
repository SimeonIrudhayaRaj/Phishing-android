package com.example.phishing.Assemblers;

import com.example.phishing.Implementations.ViewModelImpl;
import com.example.phishing.Interfaces.MainView;
import com.example.phishing.Interfaces.ViewModel;

public class ViewModelAssembler {
    public static ViewModel createInstance(MainView view) {
        return new ViewModelImpl(view);
    }
}
