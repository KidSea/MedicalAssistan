package com.example.yuxuehai.medicalassistan.presenter;

import com.example.yuxuehai.medicalassistan.bean.Event;

import java.util.List;

/**
 * Created by yuxuehai on 17-3-15.
 */

public interface DailycarePreseterDao {

    public List<Event> getDataFromServer();
}
