package com.example.yuxuehai.medicalassistan.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by yuxuehai on 17-3-2.
 */

public class Patient extends BmobObject {

    private BmobFile photo;
    private String phoneNum;
    private String patientName;
    private String pathology;
    private String num;
    private Ward mWard;
    private String gender;
    private String detail;
    private Integer age;
    private String entryTime;
    private Boolean hasRecord;

    private String record;

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPathology() {
        return pathology;
    }

    public void setPathology(String pathology) {
        this.pathology = pathology;
    }

    public BmobFile getPhoto() {
        return photo;
    }

    public void setPhoto(BmobFile photo) {
        this.photo = photo;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Ward getWard() {
        return mWard;
    }

    public void setWard(Ward ward) {
        mWard = ward;
    }


    public Boolean getHasRecord() {
        return hasRecord;
    }

    public void setHasRecord(Boolean hasRecord) {
        this.hasRecord = hasRecord;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}
