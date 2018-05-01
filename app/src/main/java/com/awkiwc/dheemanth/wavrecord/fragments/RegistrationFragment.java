package com.awkiwc.dheemanth.wavrecord.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.awkiwc.dheemanth.wavrecord.R;
import com.awkiwc.dheemanth.wavrecord.activity.MainActivity;
import com.awkiwc.dheemanth.wavrecord.controllers.RegistrationController;
import com.awkiwc.dheemanth.wavrecord.enums.FragmentType;
import com.awkiwc.dheemanth.wavrecord.interfaces.FragmentInterface;
import com.awkiwc.dheemanth.wavrecord.services.VoiceItEnrollUser;
import com.awkiwc.dheemanth.wavrecord.util.AwkiwcUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationFragment extends BaseFragment implements FragmentInterface {

    RegistrationController controller;

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
        controller = new RegistrationController(getActivity()) {
            @Override
            public void initiateRegistrationRequest(final String phoneNumber, final String password) {
                @SuppressLint("StaticFieldLeak")
                VoiceItEnrollUser voiceItEnrollUser = new VoiceItEnrollUser(getActivity(), phoneNumber, password) {
                    @Override
                    public void onSuccess(String msg) {
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

                        ((MainActivity) getActivity()).openRelevantFragment(FragmentType.VOICE, null, true);
                        AwkiwcUtils.preferenceManager.saveUserPhone(phoneNumber);
                        AwkiwcUtils.preferenceManager.saveUserPassword(password);
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
                voiceItEnrollUser.execute();
            }

        };
    }
}
