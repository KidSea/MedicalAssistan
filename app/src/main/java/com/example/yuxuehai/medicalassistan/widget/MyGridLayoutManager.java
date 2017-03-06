package com.example.yuxuehai.medicalassistan.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

/**
 * Created by yuxuehai on 17-3-6.
 */

public class MyGridLayoutManager extends GridLayoutManager {

    private boolean isScrollEnabled = true;

    public MyGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}
