package com.awkiwc.dheemanth.wavrecord.controllers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import com.awkiwc.dheemanth.wavrecord.views.layouts.VoiceLayoutView;

abstract public class VoiceController extends BaseController {
    public VoiceController(Activity activity) {
        super(activity);
        initLayoutView(activity.getLayoutInflater());
    }

    @Override
    public void initLayoutView(LayoutInflater inflater) {
        layoutView = new VoiceLayoutView(inflater);
        setMicButtonOnTouchListener((VoiceLayoutView) layoutView);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setMicButtonOnTouchListener(VoiceLayoutView layoutView) {

        layoutView.getMicButton().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                recordVoice(motionEvent);
                return false;
            }
        });

    }

    abstract public void recordVoice(MotionEvent motionEvent);
}
