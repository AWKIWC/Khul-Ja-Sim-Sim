package com.awkiwc.dheemanth.wavrecord.views.layouts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.awkiwc.dheemanth.wavrecord.R;
import com.awkiwc.dheemanth.wavrecord.util.AwkiwcUtils;
import com.awkiwc.dheemanth.wavrecord.views.widgets.CustomButton;
import com.awkiwc.dheemanth.wavrecord.views.widgets.CustomEditTextView;

public class RegistrationLayoutView extends BaseLayoutView {

    private CustomEditTextView passwordTextView;
    private CustomEditTextView nameTextView;
    private CustomEditTextView confirmPasswordTextView;
    private CustomButton registerButton;

    public RegistrationLayoutView(LayoutInflater inflater){
        super(inflater);
    }

    @Override
    public void initializeView(LayoutInflater inflater) {
        rootView = (ViewGroup)inflater.inflate(R.layout.content_registration, null);
        nameTextView = (CustomEditTextView)rootView.findViewById(R.id.nameView);
        passwordTextView = (CustomEditTextView)rootView.findViewById(R.id.newPasswordView);
        confirmPasswordTextView = (CustomEditTextView)rootView.findViewById(R.id.confirmPasswordView);
        registerButton = (CustomButton)rootView.findViewById(R.id.signupButton);
    }

    public void setOnSignUpClick(View.OnClickListener onSignupClick){
        registerButton.setOnClickListener(onSignupClick);
    }

    public CustomEditTextView getnameTextView() {
        return nameTextView;
    }

    public CustomEditTextView getPasswordTextView() {
        return passwordTextView;
    }

    public String isInputValidated(){
        if(AwkiwcUtils.isNullOrEmpty(nameTextView)){
            return "Please enter your phone number";
        }
        if(AwkiwcUtils.isNullOrEmpty(passwordTextView)){
            return "Please enter a password";
        }
        if(!AwkiwcUtils.isPasswordStrong(passwordTextView.getText().toString())){
            return "Password must be atleast 6 digits and should contain 1 lower case, 1 uppercase, 1 number and 1 special character";
        }
        if(AwkiwcUtils.isNullOrEmpty(confirmPasswordTextView)){
            return "Confirm your password by typing it again here";
        }
        if(!confirmPasswordTextView.getText().toString().equals(passwordTextView.getText().toString())){
            return "Passwords do not match.";
        }

        return null;
    }
}
