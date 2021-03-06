package com.example.yuxuehai.medicalassistan.ui;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.base.BaseActivity;
import com.example.yuxuehai.medicalassistan.bean.NfcWriteBean;
import com.example.yuxuehai.medicalassistan.bean.Patient;
import com.example.yuxuehai.medicalassistan.model.impl.DataModelDaoImpl;
import com.example.yuxuehai.medicalassistan.utlis.Constants;
import com.example.yuxuehai.medicalassistan.utlis.SimpleNfcInfoConverter;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;
import com.example.yuxuehai.medicalassistan.widget.RoundButton;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

import static com.example.yuxuehai.medicalassistan.R.id.btn_query;
import static com.example.yuxuehai.medicalassistan.R.id.edt_category;
import static com.example.yuxuehai.medicalassistan.R.id.edt_nfc_id;

/**
 * Created by yuxuehai on 17-3-10.
 */
@SuppressWarnings("unchecked")
public class NfcReaderActivity extends BaseActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private TextView mNfcId;
    private TextView mCategory;
    private RoundButton mQueryButton;

    private String readResult = "";
    private PendingIntent pendingIntent;
    private IntentFilter[] mFilters;
    private String[][] mTechLists;
    private boolean isFirst = true;
    private IntentFilter ndef;
    private NfcAdapter mNfcAdapter;
    private NfcWriteBean mWriteBean;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case Constants.READ_NFC_FAILE:
                    ToastUtil.showShort(getcontext(), "读取数据失败");
                    break;
                case Constants.READ_NFC_SUCCESS:
                    mNfcId.setText(mWriteBean.getId());
                    mCategory.setText(mWriteBean.getCategory());
                    break;

            }
            super.handleMessage(msg);

        }
    };

    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {

        if(mWriteBean != null){

            if(!mWriteBean.getId().equals("")){
                jumptoDetail();
            }else {
                ToastUtil.showShort(getcontext(), "扫描数据为空");
            }


        }else {
            ToastUtil.showShort(getcontext(), "请先扫描数据");
        }



    }

    @Override
    protected void onResume() {
        super.onResume();
        enableForegroundDispatch();
//        if (!isFirst){
//            mNfcId.setText("");
//            mCategory.setText("");}
}


    @Override
    protected void onPause() {
        super.onPause();
        disableForegroundDispatch();
//        isFirst = false;
        mWriteBean = null;
        mNfcId.setText("");
        mCategory.setText("");
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
            actionBar.setTitle(R.string.nfc_reader);
        }
    }

    /*
 * 有必要要了解onNewIntent回调函数的调用时机,请自行上网查询
 */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())||
                NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) {

            new Thread(() -> {

                Message message = new Message();
                if (readFromTag(intent)) {
                    message.what = Constants.READ_NFC_SUCCESS;

                }else {
                    message.what = Constants.READ_NFC_FAILE;
                }
                mHandler.sendMessage(message);
            }).start();
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_nfc_reader;
    }

    @Override
    protected void initView() {
        mToolbar = $(R.id.tb_mytb);

        mNfcId = $(edt_nfc_id);
        mCategory = $(edt_category);
        mQueryButton = $(btn_query);

        mQueryButton.setOnClickListener(this);

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

    private void jumptoDetail(){

        DataModelDaoImpl.getInstance().queryPatient(mWriteBean.getId(), new QueryListener<Patient>() {
            @Override
            public void done(Patient patient, BmobException e) {
                if(e == null){
                    Intent intent = new Intent(NfcReaderActivity.this, PatientsDetailActivity.class);
                    intent.putExtra("patient", patient);
                    startActivity(intent);
                }else {
                    ToastUtil.showToast(getcontext(), "数据获取异常");
                }

            }
        });

    }
}
