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

public class LoginPresenterImpl extends BasePresenter<LoginView> implements LoginPresenterDao {

    private Context mContext;
    private DataModelDaoImpl mDataModelDao = DataModelDaoImpl.getInstance();

    public LoginPresenterImpl(Context context) {
        super(context);
        mContext = context;
    }



    @Override
    public void isChecked() {
        boolean iskeep = SharePrefUtil.getBoolean(mContext, Constants.IsKeepWord, false);
        getView().setCheckBox(iskeep);

        String phone = SharePrefUtil.getString(mContext, Constants.sPHONE, "");
        String password = SharePrefUtil.getString(mContext, Constants.sPASSWORD, "");

        if (iskeep) {
            getView().setLoginInfo(phone, password);
        }
    }

    @Override
    public void login(final String phone, final String password) {
        if (!TextUtils.isEmpty(phone) & !TextUtils.isEmpty(password)) {
            getView().openDialog();
            mDataModelDao.checkLogin(phone, password, new LogInListener<UserBean>() {
                @Override
                public void done(UserBean userBean, BmobException e) {
                    getView().closeDialog();
                    if(userBean != null){
                        // 记住用户名和密码
                        boolean iskeep = SharePrefUtil.getBoolean(mContext, Constants.IsKeepWord, false);
                        if(iskeep){
                            // 记住用户名和密码
                            SharePrefUtil.setString(mContext, Constants.sPHONE, phone);
                            SharePrefUtil.setString(mContext, Constants.sPASSWORD, password);
                        }

                        getView().jumptoMain();
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

