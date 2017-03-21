package com.example.yuxuehai.medicalassistan.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.bean.Event;
import com.example.yuxuehai.medicalassistan.presenter.impl.ResponsePresenterDaoImpl;
import com.example.yuxuehai.medicalassistan.utlis.LogUtils;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;
import com.example.yuxuehai.medicalassistan.view.DailyEventView;
import com.example.yuxuehai.medicalassistan.widget.ClearEditText;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by yuxuehai on 17-3-20.
 */

public class DailycareResponseActivity extends BaseActivity implements DailyEventView , View.OnClickListener{

    private static final String TAG = "DailycareResponseActivity";
    private static final String TAG_DATETIME_FRAGMENT = "TAG_DATETIME_FRAGMENT";

    private Toolbar mToolbar;
    private ClearEditText mEditTitle;
    private ClearEditText mEditObject;
    private ClearEditText mEditLocation;
    private TextView mTime;
    private TextView mRemindTime;
    private ClearEditText mEditDetail;
    private RelativeLayout mTimeLayout;
    private RelativeLayout mRemindTimeLayout;
    private SwitchDateTimeDialogFragment dateTimeFragment;

    private ResponsePresenterDaoImpl mPresenterDao;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dailycare_res_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public Event getEvent() {
        Event event = new Event();

        event.setName(mEditTitle.getText().toString());
        event.setObjectname(mEditObject.getText().toString());
        event.setLocation(mEditLocation.getText().toString());
//        Date date = new Date(mTime.getText().toString());
//        BmobDate bmobDate = new BmobDate(date);
//        event.setDate(bmobDate);
        event.setDecs(mEditDetail.getText().toString().trim());


        return event;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_submit:
                //ToastUtil.showToast(this, "submit");

                String name = mEditTitle.getText().toString();
                String object = mEditObject.getText().toString();
                String location = mEditLocation.getText().toString();
                LogUtils.e("submit" + name + object + location);
                if (mPresenterDao.isEmpty(name, object, location)) {
                    LogUtils.e("save Entry");
                    mPresenterDao.saveEvent();
                }else {
                    ToastUtil.showToast(this, "所填信息不能为空");
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        int id =  view.getId();

        switch (id){
            case R.id.rl_time:
                dateTimeFragment.show(getSupportFragmentManager(), TAG_DATETIME_FRAGMENT);
                break;
            case R.id.rl_remind_time:

                break;
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_dailycare_respond;
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
            actionBar.setTitle(R.string.new_event);
        }
    }

    @Override
    protected void initView() {
        bindView();

        mTimeLayout.setOnClickListener(this);
        mRemindTimeLayout.setOnClickListener(this);

        initDateTime();

    }

    @Override
    protected void initData() {
        mPresenterDao = new ResponsePresenterDaoImpl(this, this);

        // Assign values we want
        final SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault());
        dateTimeFragment.startAtCalendarView();
        dateTimeFragment.set24HoursMode(true);
        dateTimeFragment.setMinimumDateTime(new GregorianCalendar(2015, Calendar.JANUARY, 1).getTime());
        dateTimeFragment.setMaximumDateTime(new GregorianCalendar(2025, Calendar.DECEMBER, 31).getTime());
        dateTimeFragment.setDefaultDateTime(new GregorianCalendar(2017, Calendar.MARCH, 4, 15, 20).getTime());

        // Define new day and month format
        try {
            dateTimeFragment.setSimpleDateMonthAndDayFormat(new SimpleDateFormat("MMMM dd", Locale.getDefault()));
        } catch (SwitchDateTimeDialogFragment.SimpleDateMonthAndDayFormatException e) {
            Log.e(TAG, e.getMessage());
        }

        // Set listener for date
        dateTimeFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Date date) {
                ToastUtil.showShort(getcontext(), myDateFormat.format(date));
                mTime.setText(myDateFormat.format(date));
            }

            @Override
            public void onNegativeButtonClick(Date date) {

            }
        });

    }

    private void bindView(){
        mToolbar = bindView(R.id.tb_mytb);
        mEditTitle = bindView(R.id.et_title);
        mEditObject = bindView(R.id.et_object);
        mEditLocation = bindView(R.id.et_location);
        mTime = bindView(R.id.tv_time);
        mRemindTime = bindView(R.id.tv_remind);
        mEditDetail = bindView(R.id.et_detail);
        mTimeLayout = bindView(R.id.rl_time);
        mRemindTimeLayout = bindView(R.id.rl_remind_time);
    }

    private void initDateTime(){
        dateTimeFragment = (SwitchDateTimeDialogFragment) getSupportFragmentManager().findFragmentByTag(TAG_DATETIME_FRAGMENT);

        if(dateTimeFragment == null) {
            dateTimeFragment = SwitchDateTimeDialogFragment.newInstance(
                    getString(R.string.label_datetime_dialog),
                    getString(R.string.positive_button_datetime_picker),
                    getString(R.string.negative_button_datetime_picker)
            );
        }


    }
}
