package com.example.yuxuehai.medicalassistan.presenter.impl;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BasePresenter;
import com.example.yuxuehai.medicalassistan.model.impl.DataModelDaoImpl;
import com.example.yuxuehai.medicalassistan.presenter.FindPsPresenterDao;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;
import com.example.yuxuehai.medicalassistan.view.FindPasswdView;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

import static cn.bmob.v3.Bmob.getApplicationContext;
import static com.example.yuxuehai.medicalassistan.utlis.UIUtils.getString;

/**
 * Created by yuxuehai on 17-2-28.
 */

public class FindPsPresenterDaoImpl extends BasePresenter<FindPasswdView> implements FindPsPresenterDao {

    private Context mContext;
    private MyCountTimer timer;
    private DataModelDaoImpl mDataModelDao = DataModelDaoImpl.getInstance();

    public FindPsPresenterDaoImpl(Context context) {
        super(context);
        this.mContext = context;
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

    @Override
    public void resetPassword(String code, String newPass, String newPassword) {
        if(!TextUtils.isEmpty(newPass) & !TextUtils.isEmpty(newPassword)){
            if(newPass.equals(newPassword)) {
                mDataModelDao.resetPasswdBySMS(code, newPassword, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            ToastUtil.showShort(mContext, getString(R.string.reset_successfully));
                            getView().finishAc();
                        }else{
                            ToastUtil.showShort(mContext,
                                    getString(R.string.reset_failed)+"+:code ="
                                            +e.getErrorCode()+",msg = "+e.getLocalizedMessage());
                        }
                    }
                });
            }else {
                ToastUtil.showShort(mContext,
                        getString(R.string.text_two_input_not_consistent));
            }
        }else {
            ToastUtil.showShort(mContext, getString(R.string.text_tost_empty));
        }
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
            getView().setResend(millisUntilFinished);
        }

        @Override
        public void onFinish() {
            getView().setTvText();
        }
    }
}
