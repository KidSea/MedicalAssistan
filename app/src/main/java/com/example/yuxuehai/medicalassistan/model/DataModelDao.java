package com.example.yuxuehai.medicalassistan.model;

import cn.bmob.v3.listener.LogInListener;

/**
 * Created by yuxuehai on 17-2-28.
 */

public interface DataModelDao {

    public void checkLogin(String phone, String passwd, LogInListener listener);
}
