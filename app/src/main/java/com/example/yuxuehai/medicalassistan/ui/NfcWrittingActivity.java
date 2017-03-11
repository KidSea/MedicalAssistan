package com.example.yuxuehai.medicalassistan.ui;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.view.View;
import android.widget.Button;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.bean.NfcWriteBean;
import com.example.yuxuehai.medicalassistan.utlis.BobNdefMessage;
import com.example.yuxuehai.medicalassistan.utlis.SimpleNfcInfoConverter;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;

import service.WriteTask;

/**
 * Created by yuxuehai on 17-3-11.
 */

public class NfcWrittingActivity extends BaseActivity {


    private Button mButton;
    private NfcAdapter mNfcAdapter;
    private PendingIntent mPendingIntent;
    private IntentFilter[] mFilters;
    private String[][] mTechLists;
    private NfcWriteBean mNfcWriteBean;
    private NdefMessage NDEFMsg2Write = null;
    private WriteTask mWriteTask;

    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNfcWriteBean = (NfcWriteBean) getIntent().getSerializableExtra("writebean");
        enableForegroundDispatch();

    }

    @Override
    protected void onPause() {
        super.onPause();
        disableForegroundDispatch();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Tag detectTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (detectTag != null && mNfcWriteBean != null) {

            if (supportedTechs(detectTag.getTechList())) {
                String payLoadStr = SimpleNfcInfoConverter.toString(mNfcWriteBean);
                NDEFMsg2Write = BobNdefMessage.getNdefMsg_from_RTD_TEXT(payLoadStr, false, false);
                mWriteTask = (WriteTask) new WriteTask(this, NDEFMsg2Write, detectTag).execute();
            } else {
                ToastUtil.showToast(this, "This tag type is not supported");
            }
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_nfc_writting;
    }

    @Override
    protected void initView() {
        mButton = $(R.id.btn_cancel);
        mButton.setOnClickListener(view -> finish());
    }

    @Override
    protected void initData() { 
        
        checkNfcFunction();

        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter ndfeDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        IntentFilter techDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);

        mFilters = new IntentFilter[]{ndfeDetected};

        mTechLists = new String[][]{
                new String[]{
                        Ndef.class.getName()
                },
                new String[]{
                        NdefFormatable.class.getName()
                }
        };
    }

    private void checkNfcFunction() {

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (mNfcAdapter == null) {
            ToastUtil.showToast(this, "该手机不支持NFC");
            return;
        }
        if (!mNfcAdapter.isEnabled()) {
            ToastUtil.showToast(this, "请在系统设置中先启用NFC功能！");
            return;
        }
    }

    private void enableForegroundDispatch() {
        if (mNfcAdapter != null) {
            mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters, mTechLists);
        }
    }

    private void disableForegroundDispatch() {
        if (mNfcAdapter != null) {
            mNfcAdapter.disableForegroundDispatch(this);
        }
    }
    private boolean supportedTechs(String[] techList) {
        boolean tech_ndef = false;
        for (String tech : techList) {
            if (tech.equals("android.nfc.tech.Ndef")
                    || tech.equals("android.nfc.tech.NdefFormatable")) {
                tech_ndef = true;
            } else {
                tech_ndef = false;
            }
        }
        if (tech_ndef) {
            return true;
        } else {
            return false;
        }
    }

}
