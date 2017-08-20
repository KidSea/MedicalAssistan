package com.example.yuxuehai.medicalassistan.base;

import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * Created by yuxuehai on 17-2-7.
 */

public abstract class BasePresenter<V> {
    private final Context mContext;
    protected WeakReference<V> mViewRef;

    public BasePresenter(Context context) {
        mContext = context.getApplicationContext();
    }


    public Context getContext() {
        return mContext;
    }


    public void attachView(V view) {
        mViewRef = new WeakReference<>(view);
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    protected V getView() {
        return mViewRef.get();
    }

}
