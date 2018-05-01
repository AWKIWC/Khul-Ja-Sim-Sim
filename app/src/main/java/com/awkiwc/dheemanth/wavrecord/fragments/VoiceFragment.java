package com.awkiwc.dheemanth.wavrecord.fragments;

import android.annotation.SuppressLint;
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
import com.awkiwc.dheemanth.wavrecord.controllers.VoiceController;
import com.awkiwc.dheemanth.wavrecord.enums.FragmentType;
import com.awkiwc.dheemanth.wavrecord.interfaces.Counter;
import com.awkiwc.dheemanth.wavrecord.interfaces.FragmentInterface;
import com.awkiwc.dheemanth.wavrecord.services.VoiceItEnrollVoice;
import com.awkiwc.dheemanth.wavrecord.util.AwkiwcUtils;
import com.awkiwc.dheemanth.wavrecord.util.WavRecorder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class VoiceFragment extends BaseFragment implements FragmentInterface {
    VoiceController controller;
    WavRecorder wavRecorder = new WavRecorder();
    Counter count;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
        initController();
        count = (MainActivity) getActivity();
        return controller.getBaseView();
    }

    @Override
    public void initController() {
        controller = new VoiceController(getActivity()) {

            @Override
            public void recordVoice(MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Toast.makeText(getActivity(), "Started Recording...", Toast.LENGTH_SHORT).show();
                    wavRecorder.startRecording();
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    Toast.makeText(getActivity(), "Done Recording...", Toast.LENGTH_SHORT).show();
                    wavRecorder.stopRecording();

                    String userId = AwkiwcUtils.preferenceManager.getUserPhone();
                    String password = AwkiwcUtils.preferenceManager.getUserPassword();

                    @SuppressLint("StaticFieldLeak")
                    VoiceItEnrollVoice voiceEn = new VoiceItEnrollVoice((MainActivity) getActivity(), userId, password, wavRecorder.getFilename()) {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(getActivity(), "Thank You! Voice Enrollment successful.", Toast.LENGTH_SHORT).show();
                            File voiceFile = new File(wavRecorder.getFilename());
                            if (voiceFile.exists()) {
                                voiceFile.delete();
                            }
                            count.increment();
                            ((MainActivity) getActivity()).openRelevantFragment(FragmentType.VOICE, null, true);
                        }

                        @Override
                        public void onFailure(JSONObject response) {
                            try {
                                Toast.makeText(getActivity(), "User Enrollment failed.\n" + response.getString("Result"), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    voiceEn.execute();

                }
            }
        };
    }
}
