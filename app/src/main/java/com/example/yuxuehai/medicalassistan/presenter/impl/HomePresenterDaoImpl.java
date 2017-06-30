package com.example.yuxuehai.medicalassistan.presenter.impl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BasePresenter;
import com.example.yuxuehai.medicalassistan.bean.Event;
import com.example.yuxuehai.medicalassistan.bean.ItemData;
import com.example.yuxuehai.medicalassistan.model.impl.DataModelDaoImpl;
import com.example.yuxuehai.medicalassistan.presenter.HomePresenterDao;
import com.example.yuxuehai.medicalassistan.ui.DailycareDetailActivity;
import com.example.yuxuehai.medicalassistan.ui.InformationDetailActivity;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;
import com.example.yuxuehai.medicalassistan.utlis.UIUtils;
import com.example.yuxuehai.medicalassistan.view.HomeView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by yuxuehai on 17-2-20.
 */

public class HomePresenterDaoImpl extends BasePresenter implements HomePresenterDao {


    private DataModelDaoImpl mDataModelDao = DataModelDaoImpl.getInstance();

    private HomeView mHomeView;

    /**
     * 首页轮播的界面的资源
     */
    private int[] resId;
    private ArrayList<View> allListView;
    private String[] mTitles;
    private int[] mIcons;



    public HomePresenterDaoImpl(Context context, HomeView view) {
        super(context);
        this.mHomeView = view;

    }


    @Override
    public void initAds() {
        if (allListView != null) {
            allListView.clear();
            allListView = null;
        }
        resId = UIUtils.getDrawables(R.array.images_ad);
        allListView = new ArrayList<View>();

        for (int i = 0; i < resId.length; i++) {
            //导入ViewPager的布局
            View view = LayoutInflater.from(getContext()).inflate(R.layout.pic_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.pic_item);
            imageView.setImageResource(resId[i]);
            allListView.add(view);
        }

        mHomeView.startAd(allListView);
    }

    @Override
    public void initIcons(ArrayList<ItemData> itemDatas) {
        mTitles = UIUtils.getStringArray(R.array.home_title_arrays);
        mIcons = UIUtils.getDrawables(R.array.item_icon_array);


        for (int i = 0; i < mTitles.length; i++) {
            ItemData data = new ItemData();
            data.setTitles(mTitles[i]);
            data.setImageId(mIcons[i]);
            itemDatas.add(data);
        }
    }

    @Override
    public void onclickCall(int position) {

        switch (position){
            case 0:
                mHomeView.jumptoActivity(InformationDetailActivity.class);
                break;
            case 1:
                mHomeView.jumptoActivity(DailycareDetailActivity.class);
                break;
            case 2:
                ToastUtil.showToast(getContext(), "待开发");
                break;
            case 3:
                ToastUtil.showToast(getContext(), "待开发");
                break;
            case 4:
                ToastUtil.showToast(getContext(), "待开发");
                break;
            case 5:
                ToastUtil.showToast(getContext(), "待开发");
                break;
            case 6:
                ToastUtil.showToast(getContext(), "待开发");
                break;
            case 7:
                ToastUtil.showToast(getContext(), "待开发");
                break;
            default:
                break;

        }
    }

    @Override
    public void onmoreclickCall(View view) {
        int id = view.getId();
        switch (id){
            case R.id.tv_daily_more:
                mHomeView.jumptoActivity(DailycareDetailActivity.class);
                break;
            case R.id.tv_news_more:
                mHomeView.showClick(id);
                break;
            case R.id.tv_mall_more:
                mHomeView.showClick(id);
            default:
                break;

        }
    }

    @Override
    public void getEventFromServer() {
        mHomeView.showRefresh();
        mDataModelDao.getEventList(new FindListener<Event>() {
            @Override
            public void done(List<Event> list, BmobException e) {
                if (e == null){
                    List<Event> events = list.subList(0, 5);
                    if(events.size() > 0){
                        mHomeView.setEventData(events);
                        mHomeView.setRefreshFinish();
                    }else {

                    }
                }else {

                }
            }
        });
    }

    @Override
    public void getNewEvent() {

        mDataModelDao.getEventList(new FindListener<Event>() {
            @Override
            public void done(List<Event> list, BmobException e) {
                if (e == null){
                    List<Event> events = list.subList(0, 5);
                    if(events.size() > 0){
                        mHomeView.setEventData(events);
                        mHomeView.setRefreshFinish();
                    }else {

                    }
                }else {

                }
            }
        });
    }
}
