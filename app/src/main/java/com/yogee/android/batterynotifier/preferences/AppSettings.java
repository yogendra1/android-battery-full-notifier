package com.yogee.android.batterynotifier.preferences;

/**
 * Created by yogendra on 24/9/16.
 */
public class AppSettings {

    //constants for json object keys
    public static final String KEY_NOTIFICATION_ENABLE = "notificationEnable";
    public static final String KEY_NOTIFICATION_INTERVAL = "notificationInterval";
    public static final String KEY_NOTIFICATION_TONE_NAME = "notificationToneName";
    public static final String KEY_NOTIFICATION_TONE_PATH = "notificationTonePath";
    public static final String KEY_BATTERY_CHARGING = "batteryCharging";
    public static final String KEY_LAST_SAVED_BATTERY_STAT = "lastBatteryStat";
    //main app switch on/of
    private boolean notificationEnable = true;
    //notification interval after battery is fully charged, in minutes
    private int notificationInterval = 5;
    //notification ringtone name
    private String notificationToneName = "";
    //notification ringtone file path
    private String notificationTonePath = "";
    //flag indicates that battery is charging
    private boolean batteryCharging = false;
    //array contains last battery percentage
    private int lastBatteryStat = 0;

    /**
     * @return
     */
    public boolean isNotificationEnable() {
        return this.notificationEnable;
    }

    /**
     * @param notificationEnable
     */
    public void setNotificationEnable(boolean notificationEnable) {
        this.notificationEnable = notificationEnable;
    }

    /**
     * @return
     */
    public int getNotificationInterval() {
        return this.notificationInterval;
    }

    /**
     * @param interval
     */
    public void setNotificationInterval(int interval) {
        this.notificationInterval = interval;
    }

    /**
     * @return
     */
    public String getNotificationToneName() {
        return this.notificationToneName;
    }

    /**
     * @param name
     */
    public void setNotificationToneName(String name) {
        this.notificationToneName = name;
    }

    /**
     * @return
     */
    public String getNotificationTonePath() {
        return this.notificationTonePath;
    }

    /**
     * @param path
     */
    public void setNotificationTonePath(String path) {
        this.notificationTonePath = path;
    }

    /**
     * @return
     */
    public boolean isBatteryCharging() {
        return this.batteryCharging;
    }

    /**
     * @param batteryCharging
     */
    public void setBatteryCharging(boolean batteryCharging) {
        this.batteryCharging = batteryCharging;
    }

    /**
     * @return
     */
    public int getLastBatteryStat() {
        return this.lastBatteryStat;
    }

    /**
     * @param batteryStat
     */
    public void setLastBatteryStat(int batteryStat) {
        this.lastBatteryStat = batteryStat;
    }
}
