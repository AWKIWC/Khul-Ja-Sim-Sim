package com.awkiwc.dheemanth.wavrecord.controllers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import com.awkiwc.dheemanth.wavrecord.util.AwkiwcUtils;
import com.awkiwc.dheemanth.wavrecord.views.layouts.LoginLayoutView;
import com.awkiwc.dheemanth.wavrecord.views.layouts.SplashLayoutView;

abstract public class LoginController extends BaseController{

    public static final int LOGIN_CONTEXT = 1;
    public static final int SPLASH_CONTEXT = 2;
    public LoginController(Activity activity){
        super(activity);
    }

    @Override
    public void initLayoutView(LayoutInflater inflater) {
        if(getViewContext() == LOGIN_CONTEXT){
            layoutView = new LoginLayoutView(inflater);
            setOnClickListeners((LoginLayoutView) layoutView);
            setMicButtonOnTouchListener((LoginLayoutView) layoutView);
        } else {
            layoutView = new SplashLayoutView(inflater);
            provideSplashClickListeners((SplashLayoutView)layoutView);
        }
    }

    private void provideSplashClickListeners(SplashLayoutView layoutView){
        layoutView.setSignupClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processSignupClick();
            }
        });

        layoutView.setLoginClickListener(getSplashLoginClickListener());
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setMicButtonOnTouchListener(LoginLayoutView layoutView) {

        layoutView.getMicButton().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                recordVoice(motionEvent);
                return false;
            }
        });

    }
    private void setOnClickListeners(final LoginLayoutView layoutView){

        layoutView.getLoginButton().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AwkiwcUtils.hideKeyboardOnButtonPress(context, v);
                String errorText = layoutView.isInputValidated();
                if (AwkiwcUtils.isNullOrEmpty(errorText)) {
                    String username = (String) layoutView.getUsernameTextView().getText().toString();
                    String password = (String) layoutView.getPasswordTextView().getText().toString();
                    layoutView.getRootView().setEnabled(false);
                    initiateLoginRequest(username, password);
                } else {
                    AwkiwcUtils.showErrorMessageOnToast(context, errorText);
                }

            }
        });
    }

    abstract public void processSignupClick();

    abstract public void initiateLoginRequest(String username, String password);

    abstract public int getViewContext();

    abstract  public View.OnClickListener getSplashLoginClickListener();

    abstract public void recordVoice(MotionEvent motionEvent);
}
