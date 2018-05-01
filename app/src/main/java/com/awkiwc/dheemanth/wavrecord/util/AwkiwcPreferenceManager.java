package com.awkiwc.dheemanth.wavrecord.util;

import android.content.Context;
import android.content.SharedPreferences;

public class AwkiwcPreferenceManager {

    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;

    private static final String NAME = "AwkiwcApplication";
    public static final String USER_PHONE = "phone";
    public static final String PASSWORD = "password";

    public AwkiwcPreferenceManager(Context context){
        sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveUserPhone(String phoneNumber){
        editor.putString(USER_PHONE, phoneNumber).commit();
    }

    public void saveUserPassword(String phoneNumber){
        editor.putString(PASSWORD, phoneNumber).commit();
    }

    public String getUserPhone(){
        return sharedPreferences.getString(USER_PHONE, "");
    }

    public String getUserPassword(){
        return sharedPreferences.getString(PASSWORD, "");
    }

}
