package com.yogee.android.batterynotifier.activities;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;

import com.yogee.android.batterynotifier.R;

public class SettingsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    //main notification on/off switch
    private SwitchCompat onOffSwitch;

    //resources instance
    private Resources appResources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        appResources = getResources();

        //notification on/off switch
        onOffSwitch = (SwitchCompat) findViewById(R.id.on_off_switch);
        onOffSwitch.setOnCheckedChangeListener(this);
        onOffSwitch.setChecked(true);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.on_off_switch:
                break;
            default:
                break;
        }
    }
}
