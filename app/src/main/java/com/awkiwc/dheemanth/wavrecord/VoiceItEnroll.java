package com.awkiwc.dheemanth.wavrecord;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.json.JSONObject;

public class VoiceItEnroll extends AsyncTask<Void, Void, String> {
    public AsyncResponse responseHandler = null;
    private ProgressDialog dialog;
    VoiceIt myVoiceIt;
    String filePath;

    VoiceItEnroll(VoiceIt myVoiceIt, String filePath, MainActivity activity) {
        this.myVoiceIt = myVoiceIt;
        this.filePath = filePath;
        dialog = new ProgressDialog(activity);
    }

    @Override
    protected void onPreExecute() {
        dialog.setMessage("Please wait while enrolling the voice.");
        dialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {

            if (myVoiceIt == null) {
                return "Voice Engine not initialized";
            }
            String userID = MainActivity.userID;
            String password = MainActivity.password;

            JSONObject response = new JSONObject(myVoiceIt.createEnrollment(userID, password, filePath, "en-US"));
            if (response.get("ResponseCode").equals("SUC")) {
                return "Thank You! Enrollment successful.";
            } else {
                return response.getString("Result");
            }
        } catch (Exception ex) {
            return "ERROR: " + ex.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        try {
            responseHandler.handelResponse(result);
        } catch (Exception ex) {
            responseHandler.handelResponse(null);
        }
    }
}
