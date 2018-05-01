package com.awkiwc.dheemanth.wavrecord.views.widgets;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.awkiwc.dheemanth.wavrecord.R;


/**
 * Created by udit on 8/24/2016.
 */
public class ActionBarNotificationView {

    RelativeLayout layout;
    CircularTextView circularTextView;

    public ActionBarNotificationView(RelativeLayout relativeLayout){
        this.layout = relativeLayout;
        circularTextView = (CircularTextView)relativeLayout.findViewById(R.id.notification_text);
    }


    public void setNotificationText(int number){
        circularTextView.setText(number + "");
    }

    public void setDisplayState(boolean showImage, boolean showText){
        circularTextView.setVisibility(View.GONE);
        if(showText){
            circularTextView.setVisibility(View.VISIBLE);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        layout.setOnClickListener(listener);
    }
}
