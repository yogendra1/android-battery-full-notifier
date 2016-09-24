package com.yogee.android.batterynotifier.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.yogee.android.batterynotifier.preferences.AppPreferences;
import com.yogee.android.batterynotifier.preferences.AppSettings;

/**
 * Created by yogendra on 24/9/16.
 */
public class BootCompleteReceiver extends BroadcastReceiver {

    //app settings
    private AppSettings appSettings;

    //log tag
    private static final String TAG = BootCompleteReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "*#, onReceive");

        //returning if app is not enable from settings
        appSettings = (new AppPreferences(context)).getAppSettings();
        if(!appSettings.isNotificationEnable()){
            Log.d(TAG, "*#, notification not enable, returning");
            return;
        }

        Log.d(TAG, "*#, sending broadcast to check battery level and show notification");
        //sending broadcast to check battery level and show notification
        Intent i = new Intent(context, BatteryLevelCheckReceiver.class);
        context.sendBroadcast(i);
    }
}
