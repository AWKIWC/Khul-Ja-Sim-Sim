package com.awkiwc.dheemanth.wavrecord.views.layouts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.awkiwc.dheemanth.wavrecord.R;

public class SplashLayoutView extends BaseLayoutView {

    TextView loginButton;
    TextView signUpButton;

    public SplashLayoutView(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    public void initializeView(LayoutInflater inflater) {
        rootView = (ViewGroup)inflater.inflate(R.layout.splash_main, null);
        loginButton = (TextView)rootView.findViewById(R.id.login);
        signUpButton = (TextView)rootView.findViewById(R.id.signup);
    }

    public void setLoginClickListener(View.OnClickListener listener){
        loginButton.setOnClickListener(listener);
    }

    public void setSignupClickListener(View.OnClickListener listener){
        signUpButton.setOnClickListener(listener);
    }
}
