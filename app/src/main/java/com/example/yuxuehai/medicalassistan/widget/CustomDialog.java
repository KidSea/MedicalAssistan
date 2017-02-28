package com.example.yuxuehai.medicalassistan.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.yuxuehai.medicalassistan.R;


/**
 * 自定义Dialog 加载中
 * Created by yuxuehai on 2017/2/27.
 */
public class CustomDialog extends Dialog {
    /**
     * 定义模板
     * @param context
     * @param layout
     * @param style
     */
    public CustomDialog(Context context, int layout, int style) {
        this(context, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,layout,style, Gravity.CENTER);
    }
    /**
     * 定义属性
     * @param context
     * @param width
     * @param height
     * @param layout
     * @param style
     * @param gravity
     * @param anim
     */
    public CustomDialog(Context context, int width, int height, int layout, int style, int gravity, int anim){
        super(context,style);
        //设置属性
        setContentView(layout);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = gravity;
        window.setAttributes(layoutParams);
        window.setWindowAnimations(anim);
    }
    /**
     * 实例
     */
    public CustomDialog(Context context, int width, int height, int layout, int style, int gravity){
        this(context,width,height,layout,style,gravity, R.style.pop_anim_style);
    }
    /**
     * 其他相关资源
     */
    /*
    <!-- res/values/styles.xml 自定义Dialog -->
    <style name="pop_anim_style">
        <item name="android:windowEnterAnimation">@anim/pop_in</item>
        <item name="android:windowExitAnimation">@anim/pop_out</item>
    </style>
     */
    /*
    <?xml version="1.0" encoding="utf-8"?>
    <!-- res/anim/pop_in.xml -->
    <set xmlns:android="http://schemas.android.com/apk/res/android">
        <translate
            android:duration="@android:integer/config_shortAnimTime"
            android:fromXDelta="0"
            android:fromYDelta="100%"
            android:toXDelta="0"
            android:toYDelta="0"/>
    </set>
     */
    /*
    <?xml version="1.0" encoding="utf-8"?>
    <!-- res/anim/pop_out.xml -->
    <set xmlns:android="http://schemas.android.com/apk/res/android">
        <translate
            android:duration="@android:integer/config_shortAnimTime"
            android:fromXDelta="0"
            android:fromYDelta="0"
            android:toXDelta="0"
            android:toYDelta="100%"/>
    </set>
    */
}
