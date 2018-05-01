package com.awkiwc.dheemanth.wavrecord.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.awkiwc.dheemanth.wavrecord.activity.MainActivity;

import org.json.JSONObject;

abstract public class VoiceItEnrollUser extends AsyncTask<Void, Void, JSONObject> {
    private ProgressDialog dialog;
    String userID;
    String password;
    Context activity;

    public VoiceItEnrollUser(Context activity, String userID, String password) {
        this.activity = activity;
        dialog = new ProgressDialog(activity);
        this.userID = userID;
        this.password = password;
    }

    @Override
    protected void onPreExecute() {
        dialog.setMessage("Please wait while enrolling the user.");
        dialog.show();
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {

        VoiceIt myVoiceIt = new VoiceIt(MainActivity.developerId);

        try {
            return new JSONObject(myVoiceIt.createUser(userID, password));
        } catch (Exception ex) {

        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject response) {
        super.onPostExecute(response);
        if (dialog.isShowing()) {
            dialog.dismiss();
        }

        try {
            if (response.getString("ResponseCode").equals("SUC")) {
                onSuccess("User Enrollment Successful.\nPlease complete the voice enrollment.");
            } else if (response.getString("ResponseCode").equals("UAE")) {
                onSuccess("User already exits with this phone number.\nPlease complete the voice enrollment.");
            } else {
                onFailure(response);
            }
        } catch (Exception ex) {

        }
    }

    abstract public void onSuccess(String msg);
    abstract public void onFailure(JSONObject response);

}
