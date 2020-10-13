package com.example.project_smart_home.object;

import java.io.Serializable;

public class DeviceWorking implements Serializable {
    Device device;
    boolean onoff = false;

    public Device getDevice() {
        return device;
    }

    public boolean isOnoff() {
        return onoff;
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
}
