package com.example.yuxuehai.medicalassistan.dao;

import android.view.MotionEvent;

/**
 * Created by yuxuehai on 17-2-19.
 * 触摸回调
 */

public interface AbOnTouchListener {
    /**
     * 描述：Touch事件.
     *
     * @param event 触摸手势
     */
    public void onTouch(MotionEvent event);
}
