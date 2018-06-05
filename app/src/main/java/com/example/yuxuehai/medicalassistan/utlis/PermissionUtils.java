package com.example.yuxuehai.medicalassistan.utlis;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import com.example.yuxuehai.medicalassistan.GlobalApplication;

/**
 * Created by yuxuehai on 17-12-13.
 */

public class PermissionUtils {

    //运行时权限集合
    public final static String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    public final static int REQUEST_CODE = 0;

    // 判断权限集合
    public static boolean lacksPermissions(String... permissions) {
        for (String permission : permissions) {
            if (lacksPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    // 判断是否缺少权限
    public static boolean lacksPermission(String permission) {
        return ContextCompat.checkSelfPermission(GlobalApplication.getContext(), permission) !=
                PackageManager.PERMISSION_GRANTED;
    }
    // 含有全部的权限
    public static boolean hasAllPermissionsGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            System.out.println(grantResult);
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }


}
