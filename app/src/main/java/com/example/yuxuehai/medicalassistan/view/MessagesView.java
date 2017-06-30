package com.example.yuxuehai.medicalassistan.view;

import com.example.yuxuehai.medicalassistan.bean.Notifications;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 2017/5/11.
 */

public interface MessagesView {

    public void refreshData();
    public void setData(ArrayList<Notifications> mList);

    public void showEmpty();
    public void showView();
}
