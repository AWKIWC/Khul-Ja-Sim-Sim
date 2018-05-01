package com.awkiwc.dheemanth.wavrecord.views.layouts;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.awkiwc.dheemanth.wavrecord.R;
import com.awkiwc.dheemanth.wavrecord.util.AwkiwcUtils;
import com.awkiwc.dheemanth.wavrecord.views.widgets.CustomButton;

import java.util.concurrent.ThreadLocalRandom;

public class LoginLayoutView extends BaseLayoutView{

    private EditText usernameTextView;
    private EditText passwordTextView;
    private CustomButton loginButton;

    private TextView tokenString;
    private ImageButton micButton;

    public LoginLayoutView(LayoutInflater inflater){
        super(inflater);
    }

    @Override
    public void initializeView(LayoutInflater inflater) {
        rootView = (ViewGroup)inflater.inflate(R.layout.content_login, null);
        initializeViewFields();
    }

    private void initializeViewFields(){
        usernameTextView =(EditText)rootView.findViewById(R.id.phoneNumberView);
        passwordTextView = (EditText)rootView.findViewById(R.id.passwordView);
        tokenString = (TextView) rootView.findViewById(R.id.tokenString);
        tokenString.setText("Never forget tomorrow is a new day");
        micButton = (ImageButton)rootView.findViewById(R.id.authStart);
        loginButton = (CustomButton)rootView.findViewById(R.id.loginButton);
    }

    public EditText getUsernameTextView() {
        return usernameTextView;
    }


    public EditText getPasswordTextView() {
        return passwordTextView;
    }


    public CustomButton getLoginButton() {
        return loginButton;
    }

    public ImageButton getMicButton() {
        return micButton;
    }

    public String isInputValidated(){
        if(AwkiwcUtils.isNullOrEmpty(usernameTextView)){
            return "Please enter your registered phone number";
        }
        if(AwkiwcUtils.isNullOrEmpty(passwordTextView)){
            return "Please enter password for a successful login";
        }
        return null;
    }

}
