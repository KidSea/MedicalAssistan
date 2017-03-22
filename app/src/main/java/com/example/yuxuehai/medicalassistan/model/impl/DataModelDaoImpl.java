package com.example.yuxuehai.medicalassistan.model.impl;

import com.example.yuxuehai.medicalassistan.bean.Event;
import com.example.yuxuehai.medicalassistan.bean.Patient;
import com.example.yuxuehai.medicalassistan.bean.Ward;
import com.example.yuxuehai.medicalassistan.model.DataModelDao;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by yuxuehai on 17-2-28.
 */

public class DataModelDaoImpl implements DataModelDao {

    private static DataModelDaoImpl mDataDao;



    public static  DataModelDaoImpl getInstance() {

        if (mDataDao == null) {
            synchronized (DataModelDaoImpl.class){
                mDataDao = new DataModelDaoImpl();
            }
        }

        return mDataDao;
    }


    @Override
    public void checkLogin(String phone, String passwd, LogInListener listener) {
        BmobUser.loginByAccount(phone, passwd, listener);
    }

    @Override
    public void requestSMS(String phone, String str, QueryListener listener) {
        BmobSMS.requestSMSCode(phone, str, listener);
    }

    @Override
    public void resetPasswdBySMS(String code, String passwd, UpdateListener listener) {
        BmobUser.resetPasswordBySMSCode(code, passwd, listener);
    }

    @Override
    public List<Ward> queryWard(int limit, FindListener findListener) {
        BmobQuery<Ward> query = new BmobQuery<>();
        query.order("roomName");
        query.include("nurse");
        query.setLimit(limit);
        query.findObjects(findListener);

        return null;
    }

    @Override
    public List<Patient> queryPatients(int limit, Ward ward, FindListener findListener) {

        BmobQuery<Patient> query = new BmobQuery<>();
        query.addWhereEqualTo("mWard", new BmobPointer(ward));
        query.include("mWard");
        query.order("num");
        query.findObjects(findListener);

        return null;
    }

    @Override
    public Patient queryPatient(String Id, QueryListener listener) {

        BmobQuery<Patient> query = new BmobQuery<>();
        query.include("mWard");
        query.getObject(Id, listener);

        return null;
    }

    @Override
    public void saveEvent(Event event, SaveListener listener) {
        event.save(listener);
    }

    @Override
    public List<Event> getEventList(FindListener findListener) {
        BmobQuery<Event> query = new BmobQuery<>();
        query.order("createdAt");
        query.findObjects(findListener);
        return null;
    }


}
