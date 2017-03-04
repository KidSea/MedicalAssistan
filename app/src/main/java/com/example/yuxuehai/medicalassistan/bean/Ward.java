package com.example.yuxuehai.medicalassistan.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by yuxuehai on 17-3-2.
 */

public class Ward extends BmobObject {

    private String roomName;
    private Integer patientNum;
    private BmobUser nurse;
    private String location;
    private String doctor;
    private String category;



    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPatientNum() {
        return patientNum;
    }

    public void setPatientNum(Integer patientNum) {
        this.patientNum = patientNum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public BmobUser getUser() {
        return nurse;
    }

    public void setUser(BmobUser user) {
        this.nurse = user;
    }

}
