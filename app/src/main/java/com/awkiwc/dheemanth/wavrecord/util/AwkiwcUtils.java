package com.awkiwc.dheemanth.wavrecord.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.awkiwc.dheemanth.wavrecord.R;
import com.awkiwc.dheemanth.wavrecord.enums.FragmentType;

public class AwkiwcUtils {

    public static FragmentType fragmentType;
    public static AwkiwcPreferenceManager preferenceManager;

    public static boolean isNullOrEmpty(TextView textView) {
        return textView == null || textView.getText() == null || textView.getText().toString().trim().isEmpty();
    }

    public static boolean isNullOrEmpty(String string){
        return string== null|| string.isEmpty()? true : false;
    }
    
    public static void showErrorMessageOnToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void hideKeyboardOnButtonPress(Context context, View view) {
        try {
            InputMethodManager inputManager = (InputMethodManager)
                    context.getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException e) {

        }
    }

    public static void hideKeyboardOnButtonPress(Activity activity) {
        try {
            InputMethodManager inputManager = (InputMethodManager)
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException e) {

        }
    }
    
    public static ProgressDialog getProgressDialog(Activity activity){
        return getProgressDialog(activity, "Loading...");
    }
    
    public static ProgressDialog getProgressDialog(Activity activity, String message) {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(message);
        if (Build.VERSION.SDK_INT >= 21) {
            progressDialog.setIndeterminateDrawable(activity.getResources().getDrawable(R.drawable.progress, null));
        } else {
            progressDialog.setIndeterminateDrawable(activity.getResources().getDrawable(R.drawable.progress));
        }
        progressDialog.show();
        return progressDialog;
    }

    public static boolean isPasswordStrong(String password) {

        if(password.length() > 1){
            return true;
        }
        if (password.length() < 6) {
            return false;
        }
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasSpecialChars = false;
        boolean hasNumbers = false;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') {
                hasUpperCase = true;
            } else if (password.charAt(i) >= 'a' && password.charAt(i) <= 'z') {
                hasLowerCase = true;
            } else if (password.charAt(i) >= '0' && password.charAt(i) <= '9') {
                hasNumbers = true;
            } else {
                hasSpecialChars = true;
            }
        }
        if (hasLowerCase && hasUpperCase && hasSpecialChars && hasNumbers) {
            return true;
        }
        return false;
    }

    public static int getColor(Context context, int color) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getResources().getColor(color, null);
        } else {
            return context.getResources().getColor(color);
        }
    }

    public static int dpToPx(Context context, int dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
    }
}
