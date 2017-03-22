package com.example.yuxuehai.medicalassistan.presenter;

/**
 * Created by yuxuehai on 17-3-21.
 */

public interface ResponsePresenterDao {

    public void saveEvent();

    public boolean isEmpty(String name, String location, String object);

}
