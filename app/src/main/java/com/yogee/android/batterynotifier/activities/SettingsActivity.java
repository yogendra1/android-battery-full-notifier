package com.yogee.android.batterynotifier.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.widget.CompoundButton;

import com.yogee.android.batterynotifier.R;
import com.yogee.android.batterynotifier.preferences.AppPreferences;
import com.yogee.android.batterynotifier.preferences.AppSettings;
import com.yogee.android.batterynotifier.receivers.BatteryLevelCheckReceiver;

public class SettingsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    //main notification on/off switch
    private SwitchCompat onOffSwitch;

    //resources instance
    private Resources appResources;

    //app settings
    private AppSettings appSettings;

    //app preferences
    private AppPreferences appPreferences;

    //log tag
    private static final String TAG = SettingsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "*# onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        appResources = getResources();
        appPreferences = new AppPreferences(this);
        appSettings = appPreferences.getAppSettings();

        //notification on/off switch
        onOffSwitch = (SwitchCompat) findViewById(R.id.on_off_switch);
        onOffSwitch.setOnCheckedChangeListener(this);
        onOffSwitch.setChecked(appSettings.isNotificationEnable());
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.on_off_switch:
                appSettings.setNotificationEnable(buttonView.isChecked());
                Log.d(TAG, "*#, sending broadcast to check battery level and show notification");
                Intent i = new Intent(this, BatteryLevelCheckReceiver.class);
                sendBroadcast(i);
                break;
            default:
                break;
        }
        saveSettings();
    }

    /**
     * saves the current setting
     */
    private void saveSettings() {
        appPreferences.setAppSettings(appSettings);
    }
}
