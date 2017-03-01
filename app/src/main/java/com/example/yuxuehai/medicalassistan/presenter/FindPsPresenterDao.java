package com.example.yuxuehai.medicalassistan.presenter;

/**
 * Created by yuxuehai on 17-2-28.
 */

public interface FindPsPresenterDao {
    public void cofirmSMS(String phoneNum);

    public void resetPassword(String code, String newPass, String newPassword);


}
