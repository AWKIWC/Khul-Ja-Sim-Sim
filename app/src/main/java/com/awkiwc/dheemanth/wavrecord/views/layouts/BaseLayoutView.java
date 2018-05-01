package com.awkiwc.dheemanth.wavrecord.views.layouts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

abstract public class BaseLayoutView {

    ViewGroup rootView;
    Context context;
    LayoutInflater inflater;

    public BaseLayoutView(LayoutInflater inflater){
        this.context = inflater.getContext();
        this.inflater = inflater;
        initializeView(inflater);
    }

    public ViewGroup getRootView(){
        return rootView;
    }

    abstract public void initializeView(LayoutInflater inflater);

}
