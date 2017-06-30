package com.example.yuxuehai.medicalassistan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.bean.Patient;

import cn.bmob.v3.BmobUser;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yuxuehai on 17-3-7.
 */

public class PatientsDetailActivity extends BaseActivity {


    private boolean isOncreate = false;

    private CircleImageView mPatientIcon;
    private Toolbar mToolbar;
    private TextView mPatientName;
    private TextView mPatientPhone;
    private TextView mPatientGender;
    private TextView mPatientAge;
    private TextView mDoctor;
    private TextView mNurse;
    private TextView mCode;
    private TextView mEntrytime;
    private TextView mPathology;
    private TextView mDetail;

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
        Intent intent = getIntent();
        mPatient = (Patient) intent.getSerializableExtra("patient");

        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isOncreate){
            Intent intent = getIntent();
            mPatient = (Patient) intent.getSerializableExtra("patient");
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        isOncreate = true;
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
        mPatientIcon = $(R.id.iv_patient_icon);
        mPatientName = $(R.id.tv_patient_name);
        mPatientPhone = $(R.id.tv_patient_phone);
        mPatientGender = $(R.id.tv_patient_gender);
        mPatientAge = $(R.id.tv_patient_age);
        mDoctor = $(R.id.tv_patient_doctor);
        mNurse = $(R.id.tv_patient_nurse);
        mCode = $(R.id.tv_patient_code);
        mEntrytime = $(R.id.tv_patient_entrytime);
        mPathology = $(R.id.tv_patient_pathology);
        mDetail = $(R.id.tv_patient_detail);
    }

    @Override
    protected void initData() {

        if(mPatient != null){
            Glide.with(this).load(mPatient.getPhoto().getFileUrl()).into(mPatientIcon);
            mPatientName.setText(mPatient.getPatientName());
            mPatientPhone.setText(mPatient.getPhoneNum());
            mPatientAge.setText(String.valueOf(mPatient.getAge()));
            mPatientGender.setText(mPatient.getGender());
            mDoctor.setText(mPatient.getWard().getDoctor());
            mNurse.setText(BmobUser.getCurrentUser().getUsername());
            mCode.setText(mPatient.getObjectId());
            mEntrytime.setText(mPatient.getEntryTime());
            mPathology.setText(mPatient.getPathology());
            mDetail.setText("   " + mPatient.getDetail());
        }
    }
}
