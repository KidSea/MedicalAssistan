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

public class PatientsPresenterDaoImpl extends BasePresenter<InformationView> implements InformationPresenterDao {

    private DataModelDaoImpl mDataModelDao = DataModelDaoImpl.getInstance();

    private ArrayList<SampleBean> mList;

    private int limit = 50;

    public PatientsPresenterDaoImpl(Context context) {
        super(context);
        mList = new ArrayList<>();
    }


    @Override
    public synchronized void getListFromServer() {
        mDataModelDao.queryWard(limit, new FindListener<Ward>() {

            @Override
            public void done(List<Ward> list, BmobException e) {
                mList.clear();
                for (Ward ward : list) {
                    if (ward.getUser().getUsername().equals(BmobUser.getCurrentUser().getUsername())) {
                        SampleBean wardbean = new SampleBean(SampleBean.TYPE_WARD, ward);
                        mList.add(wardbean);
                    }
                }
                getView().setData(mList);
            }
        });
    }

    @Override
    public synchronized void getPatientsFromServer(Ward ward) {

        mDataModelDao.queryPatients(limit, ward, new FindListener<Patient>() {
            @Override
            public void done(List<Patient> list, BmobException e) {
                if(e == null){
                    getView().showView();
                    mList.clear();
                    for (Patient patient: list) {
                        SampleBean patientbean = new SampleBean(SampleBean.TYPE_PATIENT, patient);
                        mList.add(patientbean);
                    }
                    getView().setData(mList);
                }else {
                    getView().showEmpty();
                }
            }

        });

    }
}
