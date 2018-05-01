package com.awkiwc.dheemanth.wavrecord.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.awkiwc.dheemanth.wavrecord.R;
import com.awkiwc.dheemanth.wavrecord.activity.MainActivity;
import com.awkiwc.dheemanth.wavrecord.controllers.LoginController;
import com.awkiwc.dheemanth.wavrecord.enums.FragmentType;
import com.awkiwc.dheemanth.wavrecord.interfaces.FragmentInterface;
import com.awkiwc.dheemanth.wavrecord.services.VoiceItAuth;
import com.awkiwc.dheemanth.wavrecord.util.AwkiwcUtils;
import com.awkiwc.dheemanth.wavrecord.util.WavRecorder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class LoginFragment extends BaseFragment implements FragmentInterface {

    public static final String VIEW_CONTEXT = "view-context";
    ProgressDialog progressDialog = null;
    LoginController controller;
    File recordFile = new File("");
    WavRecorder wavRecorder = new WavRecorder();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
        initController();
        return controller.getBaseView();
    }

    public void initController() {
        controller = new LoginController(getActivity()) {

            @Override
            public int getViewContext() {
                if (getArguments() != null) {
                    int context = getArguments().getInt(VIEW_CONTEXT);
                    getArguments().putInt(VIEW_CONTEXT, context);
                    return context;
                }
                return SPLASH_CONTEXT;
            }


            @Override
            public View.OnClickListener getSplashLoginClickListener() {
                return new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(VIEW_CONTEXT, 1);
                        ((MainActivity) getActivity()).openRelevantFragment(FragmentType.LOGIN, bundle, true);
                    }
                };
            }

            @Override
            public void recordVoice(MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Toast.makeText(getActivity(), "Started Recording...", Toast.LENGTH_SHORT).show();
                    wavRecorder.startRecording();
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    Toast.makeText(getActivity(), "Done Recording...", Toast.LENGTH_SHORT).show();
                    wavRecorder.stopRecording();
                    recordFile = new File(wavRecorder.getFilename());
                }
            }

            @Override
            public void processSignupClick() {
                progressDialog = AwkiwcUtils.getProgressDialog(getActivity());
                Bundle bundle = new Bundle();
                bundle.putInt(VIEW_CONTEXT, 1);
                bundle.putBoolean("forgotPassword", false);
                ((MainActivity) getActivity()).openRelevantFragment(FragmentType.SIGNUP, bundle, true);
                progressDialog.dismiss();
            }

            @Override
            public void initiateLoginRequest(final String username, String password) {
                if (!recordFile.exists()) {
                    Toast.makeText(getActivity(), "Please record your voice before proceeding.", Toast.LENGTH_SHORT).show();
                    return;
                }

                @SuppressLint("StaticFieldLeak")
                VoiceItAuth voiceItAuth = new VoiceItAuth(getActivity(), username, password, wavRecorder.getFilename()) {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getActivity(), "Thank You! Authentication successful.", Toast.LENGTH_SHORT).show();
                        ((MainActivity) getActivity()).openRelevantFragment(FragmentType.HOME, null, true);
                        AwkiwcUtils.preferenceManager.saveUserPhone(username);
                    }

                    @Override
                    public void onFailure(JSONObject response) {
                        try {
                            Toast.makeText(getActivity(), response.getString("Result"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                voiceItAuth.execute();
            }
        };
    }
}


