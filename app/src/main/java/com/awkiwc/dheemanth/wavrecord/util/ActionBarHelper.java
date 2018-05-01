package com.awkiwc.dheemanth.wavrecord.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.awkiwc.dheemanth.wavrecord.R;

public class ActionBarHelper {

    public static final int HOME_PAGE_MORE_ICON = 0x04;
    public static final int SETTINGS_ICON = 0x08;
    public static final int TITLE = 0x16;
    private int flagValue = 0;

     ViewGroup customView;
     TextView titleText;
     ImageView logoView;


    public ViewGroup getActionBarView(LayoutInflater inflater, int flag){
        if(customView == null) {
            customView = (ViewGroup) inflater.inflate(R.layout.actionbar_view, null);
        }
        logoView = (ImageView)customView.findViewById(R.id.logo_menu);

        titleText = (TextView)customView.findViewById(R.id.header_text);
        if((flag & TITLE)  == TITLE){
            titleText.setVisibility(View.VISIBLE);
        } else {
            titleText.setVisibility(View.GONE);
        }

        flagValue = flag;
        return customView;
    }


    public  void setActionBarTitle(String text){
       titleText.setText(text);
    }

}
