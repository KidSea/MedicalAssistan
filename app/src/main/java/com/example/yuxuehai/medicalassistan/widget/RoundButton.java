package com.example.yuxuehai.medicalassistan.widget;

import android.content.Context;
import android.graphics.Outline;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;

/**
 * Created by yuxuehai on 17-2-16.
 */

public class RoundButton extends Button {

    public RoundButton(Context context) {
        super(context);
        init();
    }

    public RoundButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        setClipToOutline(true);
        setOutlineProvider(new CircleOutlineProvider());
    }

    private class CircleOutlineProvider extends ViewOutlineProvider {
        @Override
        public void getOutline(View view, Outline outline) {
            int width = view.getWidth();
            int height = view.getHeight();
            outline.setRoundRect(0, 0, width, height, height/2);
        }
    }
}
