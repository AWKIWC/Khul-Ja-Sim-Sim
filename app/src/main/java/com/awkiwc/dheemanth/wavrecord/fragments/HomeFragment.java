package com.awkiwc.dheemanth.wavrecord.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.awkiwc.dheemanth.wavrecord.activity.MainActivity;
import com.awkiwc.dheemanth.wavrecord.controllers.HomeController;
import com.awkiwc.dheemanth.wavrecord.enums.FragmentType;
import com.awkiwc.dheemanth.wavrecord.interfaces.FragmentInterface;

public class HomeFragment extends BaseFragment implements FragmentInterface {

    HomeController controller;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        initController();
        return controller.getBaseView();
    }

    @Override
    public void initController() {
        controller = new HomeController(getActivity()) {
            @Override
            public void openRelevantPage(FragmentType fragmentType, Bundle arguments, boolean isReplace) {
                ((MainActivity)getActivity()).openRelevantFragment(fragmentType, arguments, isReplace);
            }
        };
    }

}
