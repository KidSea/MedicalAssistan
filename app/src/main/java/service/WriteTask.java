package service;

import android.app.Activity;
import android.nfc.NdefMessage;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.AsyncTask;

import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;

/**
 * Created by yuxuehai on 16-12-29.
 */

public class WriteTask extends AsyncTask<Void, Void, Void> {


    private Activity mActivity = null;
    private NdefMessage mMessage = null;
    private Tag mTag = null;

    private String mText = null;

    public WriteTask(Activity activity, NdefMessage message, Tag tag) {

        mActivity = activity;
        mMessage = message;
        mTag = tag;
    }

    @Override
    protected Void doInBackground(Void... params) {
        int size = mMessage.toByteArray().length;
        try {
            Ndef ndef = Ndef.get(mTag);

            if (ndef == null) {
                NdefFormatable formatable = NdefFormatable.get(mTag);
                if (formatable != null) {
                    try {
                        formatable.connect();
                        try {
                            formatable.format(mMessage);
                        } catch (Exception e) {
                            mText = "Failed to format tag，Tag refused to format";
                        }
                    } catch (Exception e) {
                        mText = "Failed to connect tag，Tag refused to connect";
                    } finally {
                        formatable.close();
                    }
                } else {
                    mText = "NDEF is not supported in this Tag";
                }
            } else {
                ndef.connect();

                try {
                    if (!ndef.isWritable()) {
                        mText = "Tag is read-only";
                    } else if (ndef.getMaxSize() < size) {
                        mText = "The data cannot written to tag，Message is too big for tag，Tag capacity is "
                                + ndef.getMaxSize() + " bytes, message is " + size + " bytes.";
                    } else {
                        ndef.writeNdefMessage(mMessage);
                        mText = "Message is written tag, message=" + mMessage;
                    }
                } catch (Exception e) {
                    mText = "Tag refused to connect";
                } finally {
                    ndef.close();
                }
            }
        } catch (Exception e) {
            mText = "Write opreation is failed，General exception: " + e.getMessage();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if(mText != null){
            ToastUtil.showToast(mActivity, mText);
        }
        mActivity.finish();
    }
}
