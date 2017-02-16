package com.example.yuxuehai.medicalassistan.utlis;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.yuxuehai.medicalassistan.GlobalApplication;

/**
 * Created by yuxuehai on 17-2-16.
 */

public class TDevice {


    public static void hideSoftKeyboard(View view) {
        if (view == null)
            return;
        ((InputMethodManager) GlobalApplication.getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                view.getWindowToken(), 0);
    }
}
