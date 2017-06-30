package com.example.yuxuehai.medicalassistan.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by yuxuehai on 2017/5/9.
 */

public class Notifications extends BmobObject {


    private String category;
    private String time;
    private String content;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return category + time + content;
    }
}
