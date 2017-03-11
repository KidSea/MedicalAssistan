package com.example.yuxuehai.medicalassistan.utlis;

import com.example.yuxuehai.medicalassistan.bean.NfcWriteBean;

/**
 * Created by yuxuehai on 17-3-11.
 */

public class SimpleNfcInfoConverter {


    public static String toString(NfcWriteBean nfcWriteBean) {
        return nfcWriteBean.getId()+";"+nfcWriteBean.getCategory();
    }

    public static NfcWriteBean fromString(String content) {
        String[] split = content.split(";");
        if (split.length == 2) {
            return new NfcWriteBean(split[0], split[1]);
        } else
            throw new IllegalArgumentException("Cannot parse given content");

    }
}
