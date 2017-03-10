package com.example.yuxuehai.medicalassistan.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.dao.OnQuickOptionformClick;

/**
 * Created by yuxuehai on 17-2-18.
 */

public class QuickOptionDialog extends Dialog implements View.OnClickListener{

    private OnQuickOptionformClick mListener;
    private ImageView mClose;


    public QuickOptionDialog(Context context) {
        this(context, R.style.quick_option_dialog);
    }

    protected QuickOptionDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    private QuickOptionDialog(Context context, int themeResId) {
        super(context, themeResId);
        View contentView = getLayoutInflater().inflate(
                R.layout.dialog_quick_option, null);
        contentView.findViewById(R.id.ly_quick_option_text).setOnClickListener(
                this);
        contentView.findViewById(R.id.ly_quick_option_album)
                .setOnClickListener(this);
        contentView.findViewById(R.id.ly_quick_option_clear)
                .setOnClickListener(this);
        mClose = (ImageView) contentView.findViewById(R.id.iv_close);

        // 设置旋转动画
        Animation operationgAnim = AnimationUtils.loadAnimation(getContext(),
                R.anim.quick_option_close);
        LinearInterpolator lin = new LinearInterpolator();
        operationgAnim.setInterpolator(lin);

        mClose.startAnimation(operationgAnim);

        mClose.setOnClickListener(this);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 设置触摸事件
        contentView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                // 当空白部分被触摸，消费触摸事件
                QuickOptionDialog.this.dismiss();
                return true;
            }
        });

        super.setContentView(contentView);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        getWindow().setGravity(Gravity.BOTTOM);

        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth();
        getWindow().setAttributes(p);
    }
    public void setOnQuickOptionformClickListener(OnQuickOptionformClick lis) {
        mListener = lis;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        int id = v.getId();
        if (mListener != null) {
            mListener.onQuickOptionClick(id);
        }
        dismiss();
    }
}
