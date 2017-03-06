package com.example.yuxuehai.medicalassistan.presenter;

import com.example.yuxuehai.medicalassistan.bean.Ward;

/**
 * Created by yuxuehai on 17-3-4.
 */

public interface InformationPresenterDao {

    public void getListFromServer();

    public void getPatientsFromServer(Ward ward);

}
