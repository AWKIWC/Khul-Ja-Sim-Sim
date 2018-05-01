package com.awkiwc.dheemanth.wavrecord.views.layouts;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.awkiwc.dheemanth.wavrecord.R;

public class VoiceLayoutView extends BaseLayoutView {

    TextView tokenString;
    ImageButton micButton;

    public VoiceLayoutView(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    public void initializeView(LayoutInflater inflater) {
        rootView = (ViewGroup)inflater.inflate(R.layout.enroll_mic, null);
        tokenString = (TextView) rootView.findViewById(R.id.tokenString);
        tokenString.setText("Never forget tomorrow is a new day");
        micButton = (ImageButton)rootView.findViewById(R.id.recordStart);
    }

    public ImageButton getMicButton() {
        return micButton;
    }

}
