package com.awkiwc.dheemanth.wavrecord.controllers;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.awkiwc.dheemanth.wavrecord.util.AwkiwcUtils;
import com.awkiwc.dheemanth.wavrecord.views.layouts.RegistrationLayoutView;

abstract public class RegistrationController extends BaseController {

    public RegistrationController(Activity activity) {
        super(activity);
        initLayoutView(activity.getLayoutInflater());
    }

    @Override
    public void initLayoutView(LayoutInflater inflater) {
        layoutView = new RegistrationLayoutView(inflater);
        setOnClickListener((RegistrationLayoutView) layoutView);
    }

    private void setOnClickListener(final RegistrationLayoutView layoutView) {
        layoutView.setOnSignUpClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewEnabled(false);
                String errorText = layoutView.isInputValidated();
                if (AwkiwcUtils.isNullOrEmpty(errorText)) {
                    String phoneNumber = layoutView.getnameTextView().getText().toString();
                    String password = layoutView.getPasswordTextView().getText().toString();
                    initiateRegistrationRequest(phoneNumber, password);
                } else {
                    setViewEnabled(true);
                    AwkiwcUtils.showErrorMessageOnToast(context, errorText);
                }
            }
        });
    }

    abstract public void initiateRegistrationRequest(String phoneNumber, String password);
}
