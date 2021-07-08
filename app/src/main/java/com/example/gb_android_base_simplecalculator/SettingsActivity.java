package com.example.gb_android_base_simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity implements Constants {

    private SettingsData settingsData = new SettingsData();
    private RadioGroup radGrp;
    private RadioButton radBtnLight;
    private RadioButton radBtnDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSettings();
        setTheme(getAppTheme());
        setContentView(getAppLayout());

        initButton(settingsData);
    }

    private void initButton(SettingsData settingsData) {
        Button button_ok = findViewById(R.id.button_ok);
        Button button_cancel = findViewById(R.id.button_cancel);
        radGrp = (RadioGroup)findViewById(R.id.radios);
        radBtnLight = (RadioButton) findViewById(R.id.radioButton_light);
        radBtnDark = (RadioButton) findViewById(R.id.radioButton_dark);

        settingCheck(settingsData);

        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSettings = new Intent();
                intentSettings.putExtra(YOUR_SETTING, settingsData);
                setResult(RESULT_OK, intentSettings);
                setSettings();

                finish();
            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        radGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton_light:
                        setSettingsData(LIGHT);
                        break;
                    case R.id.radioButton_dark:
                        setSettingsData(DARK);
                        break;
                }
            }
        });
    }

    private void settingCheck(SettingsData settingsData) {
        int value = settingsData.getSetting();
        switch (value) {
            case LIGHT:
                radBtnLight.setChecked(true);
                break;
            case DARK:
                radBtnDark.setChecked(true);
                break;
            default:
                radGrp.clearCheck();
                break;
        }
    }



    private void setSettingsData(int value) {
        settingsData.setSetting(value);
    }



    private void setSettings() {
        int value = settingsData.getSetting();
        SharedPreferences sharedPref = getSharedPreferences(APP_THEME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(APP_THEME, value);
        editor.commit();
    }

    private void getSettings() {
        SharedPreferences sharedPref = getSharedPreferences(APP_THEME, MODE_PRIVATE);
        int value = sharedPref.getInt(APP_THEME, LIGHT);
        settingsData.setSetting(value);
    }

    private int getAppTheme(){
        if (settingsData.getSetting() == DARK) {
            return R.style.MyDarkTheme;
        } else {
            return R.style.MyLightTheme;
        }
    }

    public int getAppLayout(){
        if (settingsData.getSetting() == DARK) {
            return R.layout.activity_settings_dark;
        } else {
            return R.layout.activity_settings;
        }
    }
}