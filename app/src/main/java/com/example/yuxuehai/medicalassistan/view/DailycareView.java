package com.example.yuxuehai.medicalassistan.view;

import com.example.yuxuehai.medicalassistan.bean.Event;

import java.util.List;

/**
 * Created by yuxuehai on 17-3-15.
 */

public interface DailycareView {


    public void showPrograss();
    public void hidePrograss();

    public void setList(List<Event> list);

    public void showNodata();
}
