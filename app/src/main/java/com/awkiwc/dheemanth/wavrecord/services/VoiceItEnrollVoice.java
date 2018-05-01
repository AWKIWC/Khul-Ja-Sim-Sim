package com.awkiwc.dheemanth.wavrecord.services;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.awkiwc.dheemanth.wavrecord.activity.MainActivity;

import org.json.JSONObject;

abstract public class VoiceItEnrollVoice extends AsyncTask<Void, Void, JSONObject> {
    private ProgressDialog dialog;
    String userID;
    String password;
    String filePath;

    public VoiceItEnrollVoice(MainActivity activity, String userID, String password, String filePath) {
        dialog = new ProgressDialog(activity);
        this.userID = userID;
        this.password = password;
        this.filePath = filePath;
    }

    @Override
    protected void onPreExecute() {
        dialog.setMessage("Please wait while enrolling the voice.");
        dialog.show();
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {
        try {
            VoiceIt myVoiceIt = new VoiceIt(MainActivity.developerId);
            return new JSONObject(myVoiceIt.createEnrollment(userID, password, filePath, "en-US"));
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
            if (response != null && response.get("ResponseCode").equals("SUC")) {
                onSuccess();
            } else {
                onFailure(response);
            }
        } catch (Exception ex) {

        }
    }

    abstract public void onSuccess();
    abstract public void onFailure(JSONObject response);
}
