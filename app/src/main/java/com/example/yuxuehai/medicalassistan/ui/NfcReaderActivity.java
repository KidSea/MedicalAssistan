package com.example.yuxuehai.medicalassistan.ui;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Parcelable;
import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.bean.NfcWriteBean;
import com.example.yuxuehai.medicalassistan.utlis.SimpleNfcInfoConverter;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static com.example.yuxuehai.medicalassistan.R.id.ifo_NFC;

/**
 * Created by yuxuehai on 17-3-10.
 */

public class NfcReaderActivity extends BaseActivity {

    private Toolbar mToolbar;
    private TextView mNfcInfo;

    private String readResult = "";
    private PendingIntent pendingIntent;
    private IntentFilter[] mFilters;
    private String[][] mTechLists;
    private boolean isFirst = true;
    private IntentFilter ndef;
    private NfcAdapter mNfcAdapter;
    private NfcWriteBean mWriteBean;

    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        enableForegroundDispatch();

    }

    @Override
    protected void onPause() {
        super.onPause();
        disableForegroundDispatch();
    }

    @Override
    protected void setupActionBar() {
        super.setupActionBar();
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 设置返回按钮可以点击
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(R.string.nfc_reader);
    }

    /*
 * 有必要要了解onNewIntent回调函数的调用时机,请自行上网查询
 */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())||
                NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) {
            if (readFromTag(intent)) {
                mNfcInfo.setText(readResult);
            } else {
                mNfcInfo.setText("标签数据为空");
            }
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_nfc_reader;
    }

    @Override
    protected void initView() {
        mToolbar = $(R.id.tb_mytb);
        mNfcInfo = $(ifo_NFC);
    }



    @Override
    protected void initData() {
        checkNfcFunction();

        //将被调用的Intent，用于重复被Intent触发后将要执行的跳转
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        //设定要过滤的标签动作，这里只接收ACTION_NDEF_DISCOVERED类型
        ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        ndef.addCategory("*/*");
        mFilters = new IntentFilter[] { ndef };// 过滤器
        mTechLists = new String[][]{
                new String[]{
                        Ndef.class.getName()
                },
                new String[]{
                        NdefFormatable.class.getName()
                }
        };

        if (isFirst) {
            if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent()
                    .getAction())) {
                if (readFromTag(getIntent())) {
                    mNfcInfo.setText(readResult);
                } else {
                    mNfcInfo.setText("标签数据为空");
                }
            }
            isFirst = false;
        }

    }


    /**
     * 读取NFC标签数据的操作
     */
    private boolean readFromTag1(Intent intent) {
        Parcelable[] rawArray = intent
                .getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        if (rawArray != null) {
            NdefMessage mNdefMsg = (NdefMessage) rawArray[0];
            NdefRecord mNdefRecord = mNdefMsg.getRecords()[0];
            try {
                if (mNdefRecord != null) {
                    readResult = new String(mNdefRecord.getPayload(), "UTF-8");
                    return true;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return false;
        }
        return false;
    }

    /**
     * 读取NFC标签数据的操作
     */
    private boolean readFromTag(Intent intent) {

        NdefMessage[] messages = null;
        Parcelable[] rawArray = intent
                .getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        if (rawArray != null) {
            messages = new NdefMessage[rawArray.length];
            for (int i = 0; i < rawArray.length; i++) {
                messages[i] = (NdefMessage) rawArray[i];
            }

            return processNDEFTag_RTDText(messages);

        }
        return false;
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
            mNfcAdapter.enableForegroundDispatch(this, pendingIntent, mFilters, mTechLists);
        }
    }

    private void disableForegroundDispatch() {
        if (mNfcAdapter != null) {
            mNfcAdapter.disableForegroundDispatch(this);
        }
    }


    private boolean processNDEFTag_RTDText(NdefMessage[] messages) {
        // TODO Auto-generated method stub
        if (messages == null || messages.length == 0) {
            return false;
        }

        for (int i = 0; i < messages.length; i++) {
            int length = messages[i].getRecords().length;
            NdefRecord[] records = messages[i].getRecords();
            for (int j = 0; j < length; j++) {
                for (NdefRecord record : records) {
                    if (isTextRecord(record)) {
                        return parseRTD_TEXTRecord(record);
                    }
                }
            }
        }
        return false;
    }

    private boolean parseRTD_TEXTRecord(NdefRecord record) {
        Preconditions.checkArgument(record.getTnf() == NdefRecord.TNF_WELL_KNOWN);
        Preconditions.checkArgument(Arrays.equals(record.getType(), NdefRecord.RTD_TEXT));

        String payloadStr = "";
        byte[] payload = record.getPayload();
        Byte statusByte = record.getPayload()[0];

        String textEncoding = ((statusByte & 0200) == 0) ? "UTF-8" : "UTF-16";// 0x80=0200
        int languageCodeLength = statusByte & 0077; // & 0x3F=0077(bit 5 to 0)
        String languageCode = new String(payload, 1, languageCodeLength, Charset.forName("UTF-8"));
        try {
            payloadStr = new String(payload, languageCodeLength + 1, payload.length
                    - languageCodeLength - 1, textEncoding);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mWriteBean = SimpleNfcInfoConverter.fromString(payloadStr);
        if (mWriteBean != null){

            readResult = mWriteBean.getId() + mWriteBean.getCategory();

            return true;
        }

        return false;
    }

    private boolean isTextRecord(NdefRecord record) {
        if (record.getTnf() == NdefRecord.TNF_WELL_KNOWN) {
            if (Arrays.equals(record.getType(), NdefRecord.RTD_TEXT)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
