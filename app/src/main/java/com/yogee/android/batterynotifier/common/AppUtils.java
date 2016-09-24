package com.yogee.android.batterynotifier.common;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import com.yogee.android.batterynotifier.R;
import com.yogee.android.batterynotifier.receivers.BatteryLevelCheckReceiver;

/**
 * Created by yogendra on 24/9/16.
 */
public class AppUtils {

    /**
     * shows short length toast
     *
     * @param context
     * @param message
     */
    public static void showShortToast(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * shows long length toast
     *
     * @param context
     * @param message
     */
    public static void showLongToast(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * returns current battery percentage of device
     *
     * @param context, current state context
     * @return current battery percentage of device
     */
    public static int getBatteryLevel(Context context) {
        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, iFilter);
        int rawLevel = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        int level = -1;
        if (rawLevel >= 0 && scale > 0) {
            level = (rawLevel * 100) / scale;
        }
        return level;
    }

    /**
     * schedule next start of BatteryLevelCheckReceiver
     *
     * @param context
     * @param startTimeInMillis
     */
    public static void scheduleNextBatteryLevelCheck(Context context, long startTimeInMillis) {
        Intent intent = new Intent(context, BatteryLevelCheckReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(context, Constants.BATTERY_CHECK_ALARM_ID, intent, 0);
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pIntent);
        alarm.set(AlarmManager.RTC_WAKEUP, startTimeInMillis, pIntent);
    }

    /**
     * @param minutes
     * @return milliseconds
     */
    public static long getMilliSeconds(int minutes) {
        return 1000 * 60 * minutes;
    }

    /**
     *
     * @param context
     */
    public static void showNotification(Context context){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle(context.getResources().getString(R.string.app_name));
        mBuilder.setContentText(context.getResources().getString(R.string.notification_text));
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(Constants.NOTIFICATION_ID, mBuilder.build());
    }

    /**
     *
     * @param context
     */
    public static void playNotificationTone(Context context){
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(context.getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
