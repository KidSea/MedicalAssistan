package com.example.yuxuehai.medicalassistan.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by yuxuehai on 17-3-2.
 */

public class Ward extends BmobObject {

    private String roomName;

    private String category;
    private Integer patientNum;
    private String location;
    private String doctor;

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

}
