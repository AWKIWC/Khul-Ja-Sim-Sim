package com.awkiwc.dheemanth.wavrecord.views.layouts;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.awkiwc.dheemanth.wavrecord.R;

public class HomeLayoutView extends BaseLayoutView{

    public HomeLayoutView(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    public void initializeView(LayoutInflater inflater) {
        rootView = (ViewGroup)inflater.inflate(R.layout.content_home, null);
    }

}
