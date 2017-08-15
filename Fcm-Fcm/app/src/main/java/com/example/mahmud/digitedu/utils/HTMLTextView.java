package com.example.mahmud.digitedu.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Mahmud Basunia on 8/10/2017.
 */

public class HTMLTextView extends TextView {

    public HTMLTextView(Context context) {
        super(context);
        init();
    }

    public HTMLTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HTMLTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public HTMLTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    void init() {
        setText(Html.fromHtml(getText().toString()));
    }
}
