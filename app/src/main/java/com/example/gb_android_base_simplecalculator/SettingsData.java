package com.example.gb_android_base_simplecalculator;

import android.os.Parcel;
import android.os.Parcelable;

public class SettingsData implements Parcelable {
    private int setting;

    public SettingsData() {
    }

    public SettingsData(int setting) {
        this.setting = setting;
    }

    protected SettingsData(Parcel in) {
        setting = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(setting);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SettingsData> CREATOR = new Creator<SettingsData>() {
        @Override
        public SettingsData createFromParcel(Parcel in) {
            return new SettingsData(in);
        }

        @Override
        public SettingsData[] newArray(int size) {
            return new SettingsData[size];
        }
    };

    public int getSetting() {
        return setting;
    }

    public void setSetting(int setting) {
        this.setting = setting;
    }

}
