package com.example.yuxuehai.medicalassistan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.bean.Patient;
import com.xdandroid.simplerecyclerview.SimpleSwipeRefreshLayout;

/**
 * Created by yuxuehai on 17-3-7.
 */

public class PatientsDetailActivity extends BaseActivity {


    private boolean isOncreate = false;

    private Toolbar mToolbar;
    private TextView mPatientName;
    private TextView mPatientPhone;
    private TextView mPatientGender;
    private TextView mPatientAge;

    private SimpleSwipeRefreshLayout mSwipeRefreshLayout;
    private Patient mPatient;


    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }



    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!isOncreate){
            Intent intent = getIntent();
            mPatient = (Patient) intent.getSerializableExtra("patient");
        }
        super.onCreate(savedInstanceState);
        isOncreate = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        mPatient = (Patient) intent.getSerializableExtra("patient");

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_patients_detail;
    }

    @Override
    protected void setupActionBar() {
        super.setupActionBar();
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 设置返回按钮可以点击
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(R.string.str_patients);
    }


    @Override
    protected void initView() {
        mToolbar = $(R.id.tb_mytb);
        mPatientName = $(R.id.tv_patient_name);
        mPatientPhone = $(R.id.tv_patient_phone);
        mPatientGender = $(R.id.tv_patient_gender);
        mPatientAge = $(R.id.tv_patient_age);

    }

    @Override
    protected void initData() {

        mPatientName.setText(mPatient.getPatientName());
        mPatientPhone.setText(mPatient.getPhoneNum());
        mPatientAge.setText(String.valueOf(mPatient.getAge()));
        mPatientGender.setText(mPatient.getGender());

    }
}
