package com.example.pritesh.myslideup;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SL extends RelativeLayout {
    public SL(Context context) {
        super(context);
    }

    public SL(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SL(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
