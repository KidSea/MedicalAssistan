package com.example.yuxuehai.medicalassistan.presenter.impl;

import android.content.Context;

import com.example.yuxuehai.medicalassistan.base.BasePresenter;
import com.example.yuxuehai.medicalassistan.bean.Notifications;
import com.example.yuxuehai.medicalassistan.model.impl.DataModelDaoImpl;
import com.example.yuxuehai.medicalassistan.presenter.MessagesPresenterDao;
import com.example.yuxuehai.medicalassistan.view.MessagesView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by yuxuehai on 2017/5/11.
 */

public class MessagesPresenterDaoImpl extends BasePresenter implements MessagesPresenterDao{

    private MessagesView mMessagesView;
    private DataModelDaoImpl mDataModelDao = DataModelDaoImpl.getInstance();

    private ArrayList<Notifications> mList;

    private int limit = 50;

    public MessagesPresenterDaoImpl(Context context, MessagesView view) {
        super(context);
        this.mMessagesView = view;
        mList = new ArrayList<>();
    }


    @Override
    public synchronized void getCommonMesFromServer() {
        mDataModelDao.queryCommonMes(limit, new FindListener<Notifications>() {

            @Override
            public void done(List<Notifications> list, BmobException e) {
                if(e == null){
                    mMessagesView.showView();
                    mList.clear();
                    mList = (ArrayList<Notifications>) list;
                    mMessagesView.setData(mList);
                }else {

                    mMessagesView.showEmpty();
                }
            }

        });
    }

    @Override
    public synchronized void getMergMesFromServer() {
        mDataModelDao.queryMergMes(limit, new FindListener<Notifications>() {

            @Override
            public void done(List<Notifications> list, BmobException e) {
                if(e == null){
                    mMessagesView.showView();
                    mList.clear();
                    mList = (ArrayList<Notifications>) list;
                    mMessagesView.setData(mList);
                }else {

                    mMessagesView.showEmpty();
                }
            }

        });
    }
}
