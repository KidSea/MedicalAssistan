package com.example.yuxuehai.medicalassistan;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.example.yuxuehai.medicalassistan.utlis.Constants;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * 自定义Application，进行全局化
 * Created by yuxuehai on 2017/2/12.
 */

public class GlobalApplication extends Application {


    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMyTid() {
        return mainThreadId;
    }

    private static Handler handler;
    private static int mainThreadId;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        //注册bmob后台
        // 初始化Bmob
        if (!Constants.BMOB_APP_ID.isEmpty()) {
            BmobConfig config =new BmobConfig.Builder(this)
                    .setApplicationId(Constants.BMOB_APP_ID)// 设置appkey
                    .setConnectTimeout(30)// 请求超时时间（单位为秒）：默认15s
                    .setUploadBlockSize(1024*1024)// 文件分片上传时每片的大小（单位字节），默认512*1024
                    .setFileExpiration(2500)// 文件的过期时间(单位为秒)：默认1800s
                    .build();
            Bmob.initialize(config);

//            // 使用推送服务时的初始化操作
//            BmobInstallation.getCurrentInstallation().save();
//            // 启动推送服务
//            BmobPush.startWork(this);
        }


        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();//
    }

}
