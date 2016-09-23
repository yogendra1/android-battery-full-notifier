package com.yogee.android.batterynotifier.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

import com.yogee.android.batterynotifier.R;
import com.yogee.android.batterynotifier.common.AppUtils;

/**
 * Created by yogendra on 24/9/16.
 */
public class PowerConnectionReceiver extends BroadcastReceiver {

    //toast message text
    private String toastMessage = "";

    @Override
    public void onReceive(Context context, Intent intent) {

        switch (intent.getAction()) {
            case Intent.ACTION_POWER_CONNECTED:

                int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
                boolean usbCharge = (chargePlug == BatteryManager.BATTERY_PLUGGED_USB);
                boolean acCharge = (chargePlug == BatteryManager.BATTERY_PLUGGED_AC);

                if (acCharge) {
                    toastMessage = context.getResources().getString(R.string.charging_fast) + ": ";
                } else if (usbCharge) {
                    toastMessage = context.getResources().getString(R.string.charging_slow) + ": ";
                }

                //showing information toast
                toastMessage = context.getResources().getString(R.string.app_name) + " "
                        + context.getResources().getString(R.string.msg_will_notify_on_charge_full);
                AppUtils.showLongToast(context, toastMessage);

                //schedule next battery check receiver broadcast, passing current time will trigger broadcast immediately
                AppUtils.scheduleNextBatteryLevelCheck(context, System.currentTimeMillis());

                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                AppUtils.showShortToast(context, context.getResources().getString(R.string.power_disconnected));
                break;
        }
    }
}
