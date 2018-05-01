package com.awkiwc.dheemanth.wavrecord.views.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.awkiwc.dheemanth.wavrecord.util.AwkiwcUtils;

public class CustomImageTextView extends LinearLayout {


    CustomTextView textView;
    CustomImageView imageView;

    public CustomImageTextView(Context context) {
        super(context);
        initializeViews();
    }

    public CustomImageTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeViews();
    }

    public CustomImageTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews();
    }

    public void initializeViews() {

        textView = new CustomTextView(getContext());
        imageView = new CustomImageView(getContext());
        addView(imageView);
        addView(textView);
        setGravity(Gravity.CENTER);
        setOrientation(LinearLayout.VERTICAL);
        setClickable(true);
    }

    public void setText(String text) {
        textView.setText(text);
    }

    public void setTextSize(int size) {
        textView.setTextSize(size);
    }

    /**
     * This adds a view of the same spacing size in dp, in between the image and text.
     *
     * @param spacing
     */
    public void setImageTextSpacing(int spacing) {
        View spaceView = new View(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(1, AwkiwcUtils.dpToPx(getContext(), spacing));
        spaceView.setLayoutParams(params);
        addView(spaceView, 1);
    }

    public void setTextColor(int color) {
        textView.setTextColor(color);
    }

    public void setImageDrawable(int imageDrawable) {
        imageView.setImageResource(imageDrawable);
    }

    public CustomTextView getTextView() {
        return textView;
    }

    public CustomImageView getImageView() {
        return imageView;
    }

    /**
     * Takes input in dp. To set in px. set isInPx to true
     * @param width
     * @param height
     * @param isInPx
     */
    public void setImageViewWidthHeight(int width, int height, Boolean isInPx) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (height != -1) {
            params.height = !isInPx ? AwkiwcUtils.dpToPx(getContext(),height): height;
        }
        if (width != -1) {
            params.width = !isInPx ? AwkiwcUtils.dpToPx(getContext(),width) : width;
        }
        imageView.setLayoutParams(params);
    }

    public void setImageViewLayoutParams(LinearLayout.LayoutParams params) {
        imageView.setLayoutParams(params);
    }

    public void setTextViewLayoutParams(LinearLayout.LayoutParams params) {
        textView.setLayoutParams(params);
    }

}
