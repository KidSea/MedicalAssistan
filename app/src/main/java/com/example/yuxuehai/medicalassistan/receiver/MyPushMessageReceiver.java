package com.example.yuxuehai.medicalassistan.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.yuxuehai.medicalassistan.R;
import com.example.yuxuehai.medicalassistan.ui.MainActivity;
import com.example.yuxuehai.medicalassistan.utlis.UIUtils;

import cn.bmob.push.PushConstants;

public class MyPushMessageReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
            Log.d("bmob", "BmobPushDemo收到消息：" + intent.getStringExtra(PushConstants.
                    EXTRA_PUSH_MESSAGE_STRING));
            Toast.makeText(context, "BmobPushDemo收到消息：" + intent.getStringExtra(PushConstants.
                    EXTRA_PUSH_MESSAGE_STRING), Toast.LENGTH_SHORT).show();

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
                    .setSmallIcon(R.drawable.ic_logo)
                    .setContentIntent(pendingIntent)
                    .setContentText(intent.getStringExtra(PushConstants.
                            EXTRA_PUSH_MESSAGE_STRING))
                    .setTicker("通知来啦")
                    .setAutoCancel(true)
                    .setPriority(Notification.PRIORITY_DEFAULT)
                    .setOngoing(true);
            nm.notify(1, builder.build());
        }
    }

}
