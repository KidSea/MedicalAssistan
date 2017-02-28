package com.example.yuxuehai.medicalassistan.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.example.yuxuehai.medicalassistan.base.BasePresenter;
import com.example.yuxuehai.medicalassistan.bean.UserBean;
import com.example.yuxuehai.medicalassistan.model.impl.DataModelDaoImpl;
import com.example.yuxuehai.medicalassistan.presenter.LoginPresenterDao;
import com.example.yuxuehai.medicalassistan.utlis.Constants;
import com.example.yuxuehai.medicalassistan.utlis.SharePrefUtil;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;
import com.example.yuxuehai.medicalassistan.view.LoginView;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * Created by yuxuehai on 17-2-28.
 */

public class LoginPresenterImpl extends BasePresenter implements LoginPresenterDao {

    private LoginView mLoginView;
    private Context mContext;
    private DataModelDaoImpl mDataModelDao = DataModelDaoImpl.getInstance();

    public LoginPresenterImpl(Context context, LoginView view) {
        super(context);
        mContext = context;
        mLoginView = view;
    }



    @Override
    public void isChecked() {
        boolean iskeep = SharePrefUtil.getBoolean(mContext, Constants.IsKeepWord, false);
        mLoginView.setCheckBox(iskeep);

        String phone = SharePrefUtil.getString(mContext, Constants.sPHONE, "");
        String password = SharePrefUtil.getString(mContext, Constants.sPASSWORD, "");

        if (iskeep) {
            mLoginView.setLoginInfo(phone, password);
        }
    }

    @Override
    public void login(String phone, String password) {
        if (!TextUtils.isEmpty(phone) & !TextUtils.isEmpty(password)) {
            mLoginView.openDialog();
            mDataModelDao.checkLogin(phone, password, new LogInListener<UserBean>() {
                @Override
                public void done(UserBean userBean, BmobException e) {
                    mLoginView.closeDialog();
                    if(userBean != null){
                        mLoginView.jumptoMain();
                    }else {
                        ToastUtil.showShort(mContext, "登录失败" + e.toString());
                    }
                }
            });
        } else {
            ToastUtil.showShort(mContext, "密码不能为空");
        }
    }


}

