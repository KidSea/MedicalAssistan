package com.example.yuxuehai.medicalassistan.view;

import android.graphics.Bitmap;

/**
 * Created by yuxuehai on 17-3-1.
 */

public interface MineFragmentView {

    public void showDialog();

    public void jumptoCamera();
    public void jumptoGallery();

    public void setIcon(Bitmap bitmap);
}
