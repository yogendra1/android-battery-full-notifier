package com.yogee.android.batterynotifier.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.yogee.android.batterynotifier.common.AppUtils;
import com.yogee.android.batterynotifier.common.Constants;
import com.yogee.android.batterynotifier.preferences.AppPreferences;
import com.yogee.android.batterynotifier.preferences.AppSettings;

/**
 * Created by yogendra on 24/9/16.
 */
public class BatteryLevelCheckReceiver extends BroadcastReceiver {

    //app settings
    private AppSettings appSettings;

    //log tag
    private static final String TAG = AppSettings.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "*#, onReceive");

        //returning if app is not enable from settings
        appSettings = (new AppPreferences(context)).getAppSettings();
        if (!appSettings.isNotificationEnable()) {
            Log.d(TAG, "*#, notification not enable, returning");
            return;
        }

        //current battery level
        int batteryLevel = AppUtils.getBatteryLevel(context);
        Log.d(TAG, "*#, current battery level - "+ batteryLevel);
        appSettings.setLastBatteryStat(batteryLevel);
        (new AppPreferences(context)).setAppSettings(appSettings);

        //checking battery level and show notification
        if (batteryLevel >= Constants.FULL_BATTERY_LEVEL) {
            Log.d(TAG, "*#, battery full showing notification");
            AppUtils.showNotification(context);
            AppUtils.playNotificationTone(context);
        }

        //checking current and last battery stat
        if (!(appSettings.getLastBatteryStat() > batteryLevel) || (appSettings.getLastBatteryStat() == Constants.FULL_BATTERY_LEVEL)) {
            Log.d(TAG, "*#, current battery is more than last saved battery or battery level is 100%, scheduling next alarm to check battery");
            long batteryCheckInterval = AppUtils.getMilliSeconds(appSettings.getNotificationInterval());
            AppUtils.scheduleNextBatteryLevelCheck(context, System.currentTimeMillis() + batteryCheckInterval);
        }
    }
}
