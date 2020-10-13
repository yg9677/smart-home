package com.example.project_smart_home.order;

import com.example.project_smart_home.object.Device;

public class DeviceOrder extends Order {
    Device device;

    public DeviceOrder(String mode, String type, String message, Device device) {
        super(mode, type, message);
        this.device = device;
    }

    public DeviceOrder(String type, String message, Device device) {
        super(type, message);
        this.device = device;
    }

    public Device getDevice() {
        return device;
    }
}
