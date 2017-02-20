package com.example.yuxuehai.medicalassistan.presenter;

import com.example.yuxuehai.medicalassistan.bean.ItemData;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 17-2-18.
 */

public interface HomePresenterDao {

    public void initAds();
    public void initIcons(ArrayList<ItemData> itemDatas);

    public void onclickCall(int position);


}
