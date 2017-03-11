package com.example.yuxuehai.medicalassistan.bean;

import java.io.Serializable;

/**
 * Created by yuxuehai on 17-3-11.
 */

public class NfcWriteBean implements Serializable {

    public NfcWriteBean (String id, String category){
        this.id = id;
        this.category = category;
    }

    public NfcWriteBean(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String id;
    private String category;
}
