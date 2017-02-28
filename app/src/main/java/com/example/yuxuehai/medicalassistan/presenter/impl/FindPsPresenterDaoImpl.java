package com.example.yuxuehai.medicalassistan.presenter.impl;

import android.content.Context;
import android.os.CountDownTimer;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BasePresenter;
import com.example.yuxuehai.medicalassistan.model.impl.DataModelDaoImpl;
import com.example.yuxuehai.medicalassistan.presenter.FindPsPresenterDao;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;
import com.example.yuxuehai.medicalassistan.view.FindPasswdView;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by yuxuehai on 17-2-28.
 */

public class FindPsPresenterDaoImpl extends BasePresenter implements FindPsPresenterDao {

    private Context mContext;
    private FindPasswdView mPasswdView;
    private MyCountTimer timer;
    private DataModelDaoImpl mDataModelDao = DataModelDaoImpl.getInstance();

    public FindPsPresenterDaoImpl(Context context, FindPasswdView view) {
        super(context);
        this.mContext = context;
        this.mPasswdView = view;
    }

    @Override
    public void cofirmSMS(String phoneNum) {
        if (phoneNum.isEmpty()) {
            ToastUtil.showShort(mContext, mContext.getString(R.string.text_tost_empty));
            return;
        }
        timer = new MyCountTimer(60000, 1000);
        timer.start();
        mDataModelDao.requestSMS(phoneNum, "default", new QueryListener<Integer>() {

            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null) {//验证码发送成功
                    ToastUtil.showShort(getApplicationContext(),
                            mContext.getString(R.string.text_sms_code_reset_successfully));

                } else {
                }
            }
        });

    }


    /**
     * 倒计时器
     * 停止倒计时器用 timer.cancel();
     */
    class MyCountTimer extends CountDownTimer {

        public MyCountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mPasswdView.setResend(millisUntilFinished);
        }

        @Override
        public void onFinish() {
            mPasswdView.setTvText();
        }
    }
}
