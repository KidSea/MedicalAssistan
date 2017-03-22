package com.example.yuxuehai.medicalassistan.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

/**
 * Created by yuxuehai on 17-3-20.
 */

public class Event extends BmobObject {

    private String name;
    private String objectName;
    private BmobDate date;
    private String location;
    private String desc;
    private int remindtime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjectname() {
        return objectName;
    }

    public void setObjectname(String objectname) {
        this.objectName = objectname;
    }

    public BmobDate getDate() {
        return date;
    }

    public void setDate(BmobDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDecs() {
        return desc;
    }

    public void setDecs(String desc) {
        this.desc = desc;
    }

    public int getRemindtime() {
        return remindtime;
    }

    public void setRemindtime(int remindtime) {
        this.remindtime = remindtime;
    }

}
