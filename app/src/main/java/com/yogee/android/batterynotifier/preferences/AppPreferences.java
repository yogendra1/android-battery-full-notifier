package com.yogee.android.batterynotifier.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yogendra on 24/9/16.
 */
public class AppPreferences {

    //shared preference instance
    private SharedPreferences preferences = null;

    //preference file name
    private static final String PREFERENCE_FILE_NAME = "BatteryNotifier";

    //preference key
    private static final String PREFERENCE_KEY = "AppSettings";

    //log tag
    private static final String TAG = AppPreferences.class.getSimpleName();

    //constructor
    public AppPreferences(Context context) {
        preferences = context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * @return saved application settings
     */
    public AppSettings getAppSettings() {
        AppSettings settings = new AppSettings();
        String prefJsonStr = preferences.getString(PREFERENCE_KEY, "");
        if (prefJsonStr != "") {
            try {
                JSONObject jsonObject = new JSONObject(prefJsonStr);
                settings.setNotificationEnable(jsonObject.getBoolean(AppSettings.KEY_NOTIFICATION_ENABLE));
                settings.setNotificationInterval(jsonObject.getInt(AppSettings.KEY_NOTIFICATION_INTERVAL));
                settings.setNotificationToneName(jsonObject.getString(AppSettings.KEY_NOTIFICATION_TONE_NAME));
                settings.setNotificationTonePath(jsonObject.getString(AppSettings.KEY_NOTIFICATION_TONE_PATH));
                settings.setBatteryCharging(jsonObject.getBoolean(AppSettings.KEY_BATTERY_CHARGING));
                settings.setLastBatteryStat(jsonObject.getInt(AppSettings.KEY_LAST_SAVED_BATTERY_STAT));
                Log.d(TAG, "*# getting saved setting - "+jsonObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return settings;
    }

    /**
     * @param settings
     */
    public void setAppSettings(AppSettings settings) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(AppSettings.KEY_NOTIFICATION_ENABLE, settings.isNotificationEnable());
            jsonObject.put(AppSettings.KEY_NOTIFICATION_INTERVAL, settings.getNotificationInterval());
            jsonObject.put(AppSettings.KEY_NOTIFICATION_TONE_NAME, settings.getNotificationToneName());
            jsonObject.put(AppSettings.KEY_NOTIFICATION_TONE_PATH, settings.getNotificationTonePath());
            jsonObject.put(AppSettings.KEY_BATTERY_CHARGING, settings.isBatteryCharging());
            jsonObject.put(AppSettings.KEY_LAST_SAVED_BATTERY_STAT, settings.getLastBatteryStat());
            preferences.edit().putString(PREFERENCE_KEY, jsonObject.toString()).commit();
            Log.d(TAG, "*# saving new setting - "+jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
