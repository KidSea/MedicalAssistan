package com.example.yuxuehai.medicalassistan.bean;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by yuxuehai on 17-2-28.
 */

public class UserBean extends BmobUser {

    private BmobFile photo;

    private String hospital;

    public BmobFile getPhoto() {
        return photo;
    }

    public void setPhoto(BmobFile photo) {
        this.photo = photo;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

}
