package com.example.yuxuehai.medicalassistan.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

public class SampleBean implements Serializable {

    public SampleBean(int type, BmobObject object) {
        this.type = type;
        this.mBmobObject = object;
    }

    public BmobObject mBmobObject;
    public final int type;

    //Banner类型
    public static final int TYPE_WARD = 100;
    //文本类型
    public static final int TYPE_PATIENT = 200;

}
