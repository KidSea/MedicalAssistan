package com.example.yuxuehai.medicalassistan.dao;

import com.example.yuxuehai.medicalassistan.bean.Ward;

import java.util.List;

/**
 * Created by yuxuehai on 17-3-4.
 */

public interface OnQueryWardFinished {

    void setData(List<Ward> list);
}
