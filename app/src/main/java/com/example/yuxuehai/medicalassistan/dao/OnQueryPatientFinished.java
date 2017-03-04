package com.example.yuxuehai.medicalassistan.dao;

import com.example.yuxuehai.medicalassistan.bean.Patient;

import java.util.List;

/**
 * Created by yuxuehai on 17-3-4.
 */

public interface OnQueryPatientFinished {
    void setData(List<Patient> list);
}
