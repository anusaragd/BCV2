package com.example.anusara.bcv2.Alarm;

/**
 * Created by anusara on 22-Feb-17.
 */

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.anusara.bcv2.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class Alarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();

        // Put here YOUR code.
        //Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_SHORT).show(); // For example

        time(context, intent);
        Log.e("onReceive: ", "aaaaaaaaaaaaaaaaaa");

        wl.release();
    }

    public void setAlarm(Context context) {

        Log.e("setAlarm: ", "in set alarm");
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60 , pi); // Millisec * Second * Minute
    }

    public void cancelAlarm(Context context) {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }

    public void time(Context context, Intent intent) {
        try {
            Date d = new Date();
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            String currentDate = date.format(new Date());

            SimpleDateFormat hour = new SimpleDateFormat("HH:mm");
            String currentTime = hour.format(new Date());

            if (getTime(context).equals(currentDate+currentTime)) {
                Intent it = new Intent(context, SetAlarmActivity.class);
                createNotification(context, it, "new mensage", "body333!", "this is a mensage");
            }
        } catch (Exception e) {
            Log.i("date", "error == " + e.getMessage());
        }
    }


    public void createNotification(Context context, Intent intent, CharSequence ticker, CharSequence title, CharSequence descricao) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(ticker);
        builder.setContentTitle(title);
        builder.setContentText(descricao);
        builder.setSmallIcon(R.drawable.ic_alarm);
        builder.setContentIntent(p);
        Notification n = builder.build();
        //create the notification
        n.vibrate = new long[]{150, 300, 150, 400};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.drawable.ic_alarm, n);
        //create a vibration
        try {

            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(context, som);
            toque.play();
        } catch (Exception e) {
        }
    }

    public String getTime(Context context){
        SharedPreferences prefs = context.getSharedPreferences("time_alarm", MODE_PRIVATE);
        String time = prefs.getString("dateTime", "no time");
        Log.e( "getTime: ", time);
        return time;
    }
}