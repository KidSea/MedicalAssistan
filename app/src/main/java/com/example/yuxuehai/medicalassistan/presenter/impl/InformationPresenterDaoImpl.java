package com.example.yuxuehai.medicalassistan.presenter.impl;

import android.content.Context;

import com.example.yuxuehai.medicalassistan.base.BasePresenter;
import com.example.yuxuehai.medicalassistan.bean.Patient;
import com.example.yuxuehai.medicalassistan.bean.SampleBean;
import com.example.yuxuehai.medicalassistan.bean.Ward;
import com.example.yuxuehai.medicalassistan.model.impl.DataModelDaoImpl;
import com.example.yuxuehai.medicalassistan.presenter.InformationPresenterDao;
import com.example.yuxuehai.medicalassistan.view.InformationView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by yuxuehai on 17-3-4.
 */

public class InformationPresenterDaoImpl extends BasePresenter implements InformationPresenterDao {

    private InformationView mView;
    private DataModelDaoImpl mDataModelDao = DataModelDaoImpl.getInstance();

    private ArrayList<SampleBean> mList;
    private List<Ward> mWards;
    private int limit = 50;

    public InformationPresenterDaoImpl(Context context, InformationView view) {
        super(context);
        this.mView = view;
        mList = new ArrayList<>();
    }


    @Override
    public void getListFromServer() {
        mList.clear();
        mDataModelDao.queryWard(limit, new FindListener<Ward>() {

            @Override
            public void done(List<Ward> list, BmobException e) {
                for (Ward ward : list) {
                    if (ward.getUser().getUsername().equals(BmobUser.getCurrentUser().getUsername())) {
                        SampleBean wardbean = new SampleBean(SampleBean.TYPE_WARD, ward);
                        mList.add(wardbean);
                        mDataModelDao.queryPatients(50, ward, new FindListener<Patient>() {
                            @Override
                            public void done(List<Patient> list, BmobException e) {
                                List<SampleBean> sampleBeen = new ArrayList<>();
                                for (int i = 0; i < 6; i++) {
                                    SampleBean bean;
                                    if (i < list.size()) {
                                        bean = new SampleBean(SampleBean.TYPE_PATIENT, list.get(i));
                                        sampleBeen.add(bean);
                                    } else {
                                        bean = new SampleBean(SampleBean.TYPE_PATIENT, null);
                                        sampleBeen.add(bean);
                                    }
                                }
                                mList.addAll(sampleBeen);
                                mView.setData(mList);
                            }
                        });

                    }
                }
            }
        });
    }
}
