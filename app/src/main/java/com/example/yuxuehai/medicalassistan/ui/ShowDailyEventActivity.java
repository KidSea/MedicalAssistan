package com.example.yuxuehai.medicalassistan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.bean.Event;
import com.example.yuxuehai.medicalassistan.utlis.Constants;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by yuxuehai on 17-3-23.
 */

public class ShowDailyEventActivity extends BaseActivity implements View.OnClickListener{


    private Toolbar mToolbar;
    private TextView mEventName;
    private TextView mObject;
    private TextView mLocation;
    private TextView mTime;
    private TextView mRemindTime;
    private TextView mDetail;
    private LinearLayout mEventEdit;
    private LinearLayout mEventShare;
    private LinearLayout mEventDelete;

    private Event mEvent;
    private boolean isOncreate = false;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }



    public <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id){
            case R.id.event_edit:
                Intent intent = new Intent(this, DailycareUpdateActivity.class);
                intent.putExtra("event", mEvent);
                startActivityForResult(intent, Constants.UPDATE_EVENT_SUCCESS);
                finish();
                break;
            case R.id.event_delete:
                OnDeleteDialog();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case Constants.UPDATE_EVENT_SUCCESS:
                setResult(Constants.RESULT_UPDATE_INFO);
                finish();
                break;
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        mEvent = (Event) intent.getSerializableExtra("event");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isOncreate){
            Intent intent = getIntent();
            mEvent = (Event) intent.getSerializableExtra("event");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isOncreate = true;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_dailycare_event_detail;
    }

    @Override
    protected void initView() {

        bindView();

        mEventEdit.setOnClickListener(this);
        mEventShare.setOnClickListener(this);
        mEventDelete.setOnClickListener(this);
    }

    @Override
    protected void initData() {super.onPause();

        mEventName.setText(mEvent.getName());
        mObject.setText(mEvent.getObjectname());
        mLocation.setText(mEvent.getLocation());
        String date = mEvent.getDate().getDate();
        mTime.setText(date.substring(0, date.length()-3));
        mRemindTime.setText(getRemindTime(mEvent.getRemindtime()));
        mDetail.setText(mEvent.getDecs());
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
            actionBar.setTitle(R.string.event_detail);
        }
    }


    private void bindView() {

        mToolbar = bindView(R.id.tb_mytb);
        mEventName = bindView(R.id.tv_title);
        mLocation = bindView(R.id.tv_location);
        mObject = bindView(R.id.tv_object);
        mTime = bindView(R.id.tv_time);
        mRemindTime = bindView(R.id.tv_remind);
        mDetail = bindView(R.id.tv_detail);
        mEventEdit = bindView(R.id.event_edit);
        mEventShare = bindView(R.id.event_share);
        mEventDelete = bindView(R.id.event_delete);
    }


    private String getRemindTime(int remindtime) {

        String name = "";
        switch (remindtime){
            case 0:
                name = "无";
                break;
            case 1:
                name = "准时";
                break;
            case 2:
                name = "5分钟前";
                break;
            case 3:
                name = "10分钟前";
                break;
            case 4:
                name = "30分钟前";
                break;
            case 5:
                name = "1小时前";
                break;
        }

        return name;
    }


    private void OnDeleteDialog() {

        LayoutInflater inflater = LayoutInflater.from(this);
        RelativeLayout inflate = (RelativeLayout) inflater.inflate(R.layout.dialog_delete, null);

        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.show();

        Window window = dialog.getWindow();
        window.setContentView(inflate); // 修改整个dialog窗口的显示

        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event event = new Event();
                event.setObjectId(mEvent.getObjectId());
                event.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            ToastUtil.showToast(ShowDailyEventActivity.this,"删除成功");
                        }else{
                            ToastUtil.showToast(ShowDailyEventActivity.this,"删除失败");
                        }
                    }
                });
                ShowDailyEventActivity.this.setResult(Constants.RESULT_DELETE_INFO);
                ShowDailyEventActivity.this.finish();

            }
        });
    }
}
