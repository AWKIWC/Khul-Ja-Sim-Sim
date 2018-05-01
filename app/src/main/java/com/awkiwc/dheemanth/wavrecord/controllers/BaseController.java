package com.awkiwc.dheemanth.wavrecord.controllers;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.awkiwc.dheemanth.wavrecord.views.layouts.BaseLayoutView;

abstract public class BaseController {
    Context context;
    @Nullable
    BaseLayoutView layoutView;

    public BaseController(Activity activity) {
        this.context = activity.getApplicationContext();
        initLayoutView(activity.getLayoutInflater());
    }

    abstract public void initLayoutView(LayoutInflater inflater);

    public ViewGroup getBaseView() {
        return layoutView.getRootView();
    }

    public void setViewEnabled(boolean isEnabled) {
        if (layoutView != null) {
            enableDisableAllViews(layoutView.getRootView(), isEnabled);
        }
    }

    public void enableDisableAllViews(ViewGroup layoutView, boolean isEnabled) {
        if(layoutView != null) {
            for (int i = 0; i < layoutView.getChildCount(); i++) {
                View view = layoutView.getChildAt(i);
                if (view != null) {
                    if (view instanceof ViewGroup) {
                        enableDisableAllViews((ViewGroup) view, isEnabled);
                    } else {
                        view.setEnabled(isEnabled);
                    }
                }
            }
            layoutView.setEnabled(isEnabled);
        }
    }

    public boolean isViewEnabled(){
        return layoutView.getRootView().isEnabled();
    }

}
