package com.example.yuxuehai.medicalassistan.view;

import com.example.yuxuehai.medicalassistan.bean.SampleBean;

import java.util.ArrayList;

/**
 * Created by yuxuehai on 17-3-4.
 */

public interface InformationView {

    public void refreshData();
    public void setData(ArrayList<SampleBean> mList);

}
