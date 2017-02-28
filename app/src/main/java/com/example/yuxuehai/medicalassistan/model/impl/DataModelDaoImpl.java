package com.example.yuxuehai.medicalassistan.model.impl;

import com.example.yuxuehai.medicalassistan.model.DataModelDao;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.LogInListener;

/**
 * Created by yuxuehai on 17-2-28.
 */

public class DataModelDaoImpl implements DataModelDao{

    private static DataModelDaoImpl mDataDao;


    public static synchronized DataModelDaoImpl getInstance(){

        if(mDataDao == null){
            mDataDao = new DataModelDaoImpl();
        }

        return mDataDao;
    }


    @Override
    public void checkLogin(String phone, String passwd, LogInListener listener) {
        BmobUser.loginByAccount(phone, passwd, listener);
    }
}
