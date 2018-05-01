package com.awkiwc.dheemanth.wavrecord.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.awkiwc.dheemanth.wavrecord.enums.FragmentType;
import com.awkiwc.dheemanth.wavrecord.views.layouts.HomeLayoutView;

abstract public class HomeController extends BaseController{


    public HomeController(Activity activity){
        super(activity);
    }

    @Override
    public void initLayoutView(LayoutInflater layoutInflater) {
        layoutView = new HomeLayoutView(layoutInflater);
        setOnClickListeners((HomeLayoutView) layoutView);
    }

    private void setOnClickListeners(HomeLayoutView layoutView){

    }

    abstract public void openRelevantPage(FragmentType fragmentType, Bundle arguments, boolean isReplace);

}
