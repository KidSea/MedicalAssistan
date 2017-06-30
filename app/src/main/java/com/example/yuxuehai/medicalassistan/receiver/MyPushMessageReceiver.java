package com.example.yuxuehai.medicalassistan.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.NotificationCompat;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.bean.Notifications;
import com.example.yuxuehai.medicalassistan.ui.MainActivity;
import com.example.yuxuehai.medicalassistan.utlis.GsonUtils;
import com.example.yuxuehai.medicalassistan.utlis.ToastUtil;
import com.example.yuxuehai.medicalassistan.utlis.UIUtils;

import cn.bmob.push.PushConstants;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MyPushMessageReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
//            Log.d("bmob", "BmobPushDemo收到消息：" + intent.getStringExtra(PushConstants.
//                    EXTRA_PUSH_MESSAGE_STRING));
//            Toast.makeText(context, "BmobPushDemo收到消息：" + intent.getStringExtra(PushConstants.
//                    EXTRA_PUSH_MESSAGE_STRING), Toast.LENGTH_SHORT).show();

            Notifications notifications = GsonUtils.parseJsonWithGson(intent.
                    getStringExtra(PushConstants.EXTRA_PUSH_MESSAGE_STRING), Notifications.class);

            saveNotification(context, notifications);

            createNotification(context, notifications);
        }
    }

    private void createNotification(Context context, Notifications notifications) {
        NotificationManager nm = (NotificationManager) context.
                getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(UIUtils.
                getContext());

        Intent activityIntent =  new Intent(context, MainActivity.class);
        activityIntent.putExtra("Notification", "notification");
        activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 , activityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentTitle("收到通知啦")
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_logo_no))
                .setSmallIcon(R.drawable.ic_logo_no)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .setContentText(notifications.getContent())
                .setColor(Color.parseColor("#3bc06b"))
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_DEFAULT);
        nm.notify(1, builder.build());
    }

    private void saveNotification(Context context, Notifications notifications) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                notifications.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null){
                            ToastUtil.showToast(context, "保存成功");
                        }else {
                            ToastUtil.showToast(context, "保存失败");
                        }
                    }
                });
            }
        }).start();
    }

}
