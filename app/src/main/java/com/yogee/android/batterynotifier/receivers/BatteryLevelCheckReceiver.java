package com.yogee.android.batterynotifier.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.yogee.android.batterynotifier.common.AppUtils;
import com.yogee.android.batterynotifier.common.Constants;

/**
 * Created by yogendra on 24/9/16.
 */
public class BatteryLevelCheckReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        //checking battery level
        if(AppUtils.getBatteryLevel(context) == Constants.FULL_BATTERY_LEVEL){
            //show notification
        }

        /*boolean isPhoneCharging = true;
        long batteryCheckInterval = (1000 * 60) * 5; //5 minutes
        if(isPhoneCharging){
            AppUtils.scheduleNextBatteryLevelCheck(context, batteryCheckInterval);
        }*/
    }
}
