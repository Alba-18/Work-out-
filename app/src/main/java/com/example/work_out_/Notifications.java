package com.example.work_out_;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class Notifications extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        String CHANNEL_ID = "reminder_channel_1";
        CharSequence name = "Reminder Channel";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            manager.createNotificationChannel(channel);
        }

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.running_man)
                .setContentTitle("Exercise reminder")
                .setContentText("Go to WORK(OUT) app and do your routines !")
                .build();

        manager.notify(1, notification);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void scheduleNotification(Context context, long time) {
        Intent intent = new Intent(context, Notifications.class);
        PendingIntent pending = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pending);
    }

}
