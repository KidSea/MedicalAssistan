package com.example.yuxuehai.medicalassistan.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.utlis.UIUtils;

import java.util.ArrayList;
import java.util.Arrays;

import me.srodrigo.androidhintspinner.HintAdapter;
import me.srodrigo.androidhintspinner.HintSpinner;

/**
 * Created by yuxuehai on 17-3-10.
 */

public class NfcWriterActivity extends BaseActivity implements View.OnClickListener{


    private static final String[] spinnerStr = UIUtils.getStringArray(R.array.nfc_category);

    private Toolbar mToolbar;
    private EditText mPatientId;
    private Spinner mCategory;
    private Button mWriteButton;
    private HintSpinner mHintSpinner;



    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void onClick(View view) {

    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_nfc_writer;
    }


    @Override
    protected void setupActionBar() {
        super.setupActionBar();
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 设置返回按钮可以点击
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(R.string.nfc_writer);
    }

    @Override
    protected void initView() {
        mToolbar = $(R.id.tb_mytb);
        mPatientId = $(R.id.edt_patient_id);
        mCategory = $(R.id.sp_category);
        mWriteButton = $(R.id.btn_write);
        
        initSpinner();


        mWriteButton.setOnClickListener(this);


    }


    @Override
    protected void initData() {

    }

    private void initSpinner() {
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(spinnerStr));
        HintAdapter hintAdapter = new HintAdapter(this, R.string.hint, list);
        mHintSpinner = new HintSpinner(mCategory, hintAdapter, (position, itemAtPosition) -> {

        });
        mHintSpinner.init();

    }

}
