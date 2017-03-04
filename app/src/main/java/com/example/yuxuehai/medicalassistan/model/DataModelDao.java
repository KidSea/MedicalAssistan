package com.example.yuxuehai.medicalassistan.model;

import com.example.yuxuehai.medicalassistan.bean.Patient;
import com.example.yuxuehai.medicalassistan.bean.Ward;

import java.util.List;

import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by yuxuehai on 17-2-28.
 */

public interface DataModelDao {

    public void checkLogin(String phone, String passwd, LogInListener listener);

    public void requestSMS(String phone, String str, QueryListener listener);

    public void resetPasswdBySMS(String code, String passwd, UpdateListener listener);

    public List<Ward> queryWard(int limit, FindListener findListener);

    public List<Patient> queryPatients(int limit, Ward ward, FindListener findListener);
}
