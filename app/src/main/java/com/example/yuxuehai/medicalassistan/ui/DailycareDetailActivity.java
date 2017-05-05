package com.example.yuxuehai.medicalassistan.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.adapter.MyDalicareEventAdapter;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.base.BaseRecyclerAdapter;
import com.example.yuxuehai.medicalassistan.bean.Event;
import com.example.yuxuehai.medicalassistan.presenter.impl.DailycarePreseterDaoImpl;
import com.example.yuxuehai.medicalassistan.utlis.Constants;
import com.example.yuxuehai.medicalassistan.view.DailycareView;

import java.util.List;

/**
 * Created by yuxuehai on 17-2-23.
 */

public class DailycareDetailActivity extends BaseActivity implements DailycareView{


    private Toolbar mToolbar;
    private RelativeLayout mProgress;
    private RelativeLayout mNoData;
    private RecyclerView mRecyclerView;

    private DailycarePreseterDaoImpl mPreseterDao;
    private MyDalicareEventAdapter mAdapter;

    private boolean isOncreate = false;


    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
//
//    @Override
//    protected void onResume() {
//        if(isOncreate){
//            mPreseterDao.getDataFromServer();
//        }
//        super.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        isOncreate = true;
//        super.onPause();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dailycare_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_add:
                Intent intent = new Intent(this, DailycareResponseActivity.class);
                startActivityForResult(intent, Constants.ADD_EVENT_SUCCESS);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showPrograss() {
        mRecyclerView.setVisibility(View.GONE);
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePrograss() {
        mProgress.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setList(List<Event> list) {

        mAdapter.addDatas(list);
    }

    @Override
    public void showNodata() {
        mRecyclerView.setVisibility(View.GONE);
        mNoData.setVisibility(View.VISIBLE);
    }


    @Override
    protected void initView() {
        mToolbar = $(R.id.tb_mytb);
        mProgress = $(R.id.pb_loding);
        mRecyclerView = $(R.id.rc_dailyview);
        mNoData = $(R.id.layout_empty);
    }



    @Override
    protected void initData() {

        mAdapter = new MyDalicareEventAdapter();

        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
                Intent intent = new Intent(DailycareDetailActivity.this, ShowDailyEventActivity.class);
                intent.putExtra("event", mAdapter.getDatas().get(position));
                startActivityForResult(intent, Constants.REQUEST_INFO);
            }
        });

        mRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);



        mPreseterDao = new DailycarePreseterDaoImpl(this, this);

        mPreseterDao.getDataFromServer();

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_dailycare;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case Constants.ADD_EVENT_SUCCESS: // 添加数据回调
                mPreseterDao.getDataFromServer();
                break;
            case Constants.RESULT_DELETE_INFO:
                mPreseterDao.getDataFromServer();
                break;
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void setupActionBar() {
        super.setupActionBar();
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            // 设置返回按钮可以点击
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle(R.string.str_daily);
        }
    }



}
