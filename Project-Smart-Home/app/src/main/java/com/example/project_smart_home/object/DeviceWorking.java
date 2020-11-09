package com.example.project_smart_home.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class DeviceWorking implements Parcelable {
    Device device;
    boolean onoff = false;

    public DeviceWorking() {}

    public Device getDevice() {
        return device;
    }

    public String getOnoff() {
        String stronoff = "OFF";
        if (onoff){
            stronoff = "ON";
        }
        return stronoff;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public void setOnoff(boolean onoff) {
        this.onoff = onoff;
    }

    @Override
    public String toString() {
        String stronoff = "OFF";
        if (onoff){
            stronoff = "ON";
        }
        return device.getName() + " 전원 " + stronoff;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(device, i);
        parcel.writeByte((byte) (onoff ? 1 : 0));
    }

    protected DeviceWorking(Parcel in) {
        device = in.readParcelable(Device.class.getClassLoader());
        onoff = in.readByte() != 0;
    }

    public static final Creator<DeviceWorking> CREATOR = new Creator<DeviceWorking>() {
        @Override
        public DeviceWorking createFromParcel(Parcel in) {
            return new DeviceWorking(in);
        }

        @Override
        public DeviceWorking[] newArray(int size) {
            return new DeviceWorking[size];
        }
    };
}
