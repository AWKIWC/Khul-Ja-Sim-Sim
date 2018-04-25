package com.awkiwc.dheemanth.wavrecord;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.json.JSONObject;

public class VoiceItInit extends AsyncTask<Void, Void, VoiceIt> {
    public AsyncResponseV responseHandler = null;
    private ProgressDialog dialog;

    VoiceItInit(MainActivity activity) {
        dialog = new ProgressDialog(activity);
    }

    @Override
    protected void onPreExecute() {
        dialog.setMessage("Initializing Voice Engine.");
        dialog.show();
    }

    @Override
    protected VoiceIt doInBackground(Void... voids) {

        VoiceIt myVoiceIt = new VoiceIt(MainActivity.developerId);
        String userID = MainActivity.userID;
        String password = MainActivity.password;

        try {
            JSONObject response = new JSONObject(myVoiceIt.getUser(userID, password));
            return myVoiceIt;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(VoiceIt result) {
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
