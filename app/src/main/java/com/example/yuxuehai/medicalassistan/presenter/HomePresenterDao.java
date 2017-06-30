package com.example.yuxuehai.medicalassistan.presenter;

import android.view.View;

import com.example.yuxuehai.medicalassistan.bean.ItemData;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 17-2-18.
 */

public interface HomePresenterDao {

    public void initAds();
    public void initIcons(ArrayList<ItemData> itemDatas);

    public void onclickCall(int position);
    public void onmoreclickCall(View view);

    public void getEventFromServer();

    public void getNewEvent();
}
