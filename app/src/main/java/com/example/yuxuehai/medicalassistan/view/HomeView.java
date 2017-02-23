package com.example.yuxuehai.medicalassistan.view;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 17-2-20.
 */

public interface HomeView {

    public void startAd(ArrayList<View> allListView);
    public void showClick(int position);

    public void jumptoActivity(Class clazz);
}
