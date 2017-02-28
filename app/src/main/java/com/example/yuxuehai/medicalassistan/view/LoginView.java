package com.example.yuxuehai.medicalassistan.view;

/**
 * Created by yuxuehai on 17-2-28.
 */

public interface LoginView {

    public void setCheckBox(boolean iskeep);

    public void setLoginInfo(String phone, String password);

    public void openDialog();
    public void closeDialog();

    public void jumptoMain();
}
