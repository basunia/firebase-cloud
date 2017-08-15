package com.example.mahmud.digitedu.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.telecom.TelecomManager;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Mahmud Basunia on 7/19/2017.
 */

public class ArtistTextView extends TextView {

    public ArtistTextView(Context context) {
        super(context);
        style(context);
    }

    public ArtistTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        style(context);
    }

    public ArtistTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        style(context);
    }

    public ArtistTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        style(context);
    }


    private void style(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "font/siyam_rupali_ansi.ttf"));
        setTextSize(30);
    }
}
