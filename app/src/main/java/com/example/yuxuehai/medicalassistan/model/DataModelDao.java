package com.example.yuxuehai.medicalassistan.model;

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
}
