package com.example.yuxuehai.medicalassistan.view;

import android.view.View;

import com.example.yuxuehai.medicalassistan.bean.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuxuehai on 17-2-20.
 */

public interface HomeView {

    public void startAd(ArrayList<View> allListView);
    public void showClick(int position);

    public void jumptoActivity(Class clazz);

    public void setEventData(List<Event> events);

    public void showRefresh();

    public void setRefreshFinish();
}
