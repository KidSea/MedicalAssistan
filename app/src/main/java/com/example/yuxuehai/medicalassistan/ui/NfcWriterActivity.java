package com.example.yuxuehai.medicalassistan.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.bean.NfcWriteBean;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;
import com.example.yuxuehai.medicalassistan.utlis.UIUtils;

import java.util.ArrayList;
import java.util.Arrays;

import me.srodrigo.androidhintspinner.HintAdapter;
import me.srodrigo.androidhintspinner.HintSpinner;

/**
 * Created by yuxuehai on 17-3-10.
 */

public class NfcWriterActivity extends BaseActivity implements View.OnClickListener {


    private static final String[] spinnerStr = UIUtils.getStringArray(R.array.nfc_category);

    private Toolbar mToolbar;
    private EditText mPatientId;
    private Spinner mCategory;
    private Button mWriteButton;
    private HintSpinner mHintSpinner;
    private NfcWriteBean mWriteBean;


    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void onClick(View view) {
        judgeIfWritting();
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


        mPatientId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mWriteBean.setId(mPatientId.getText().toString());
            }
        });

        mWriteButton.setOnClickListener(this);


    }


    @Override
    protected void initData() {
        mWriteBean = new NfcWriteBean();

    }

    private void initSpinner() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList(spinnerStr));
        HintAdapter hintAdapter = new HintAdapter(this, R.string.hint, list);
        mHintSpinner = new HintSpinner(mCategory, hintAdapter, (position, itemAtPosition) -> {
            ToastUtil.showShort(getcontext(), (String) mCategory.getSelectedItem());
            mWriteBean.setCategory((String) mCategory.getSelectedItem());
        });
        mHintSpinner.init();

    }
    private void judgeIfWritting() {
        if((mWriteBean.getId()!= null) && (mWriteBean.getCategory() != null)){
            if(!(mWriteBean.getId().equals("")) && !(mWriteBean.getCategory().equals(""))) {
                ToastUtil.showShort(getcontext(), "进入读写");
                Intent intent = new Intent(this, NfcWrittingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("writebean", mWriteBean);
                startActivity(intent);
            }else {
                ToastUtil.showShort(getcontext(), "信息不能为空");
            }

        }else {
            ToastUtil.showShort(getcontext(), "信息不能为空");
        }
    }
}
