package com.example.project_smart_home;

import android.widget.Switch;

import com.example.project_smart_home.object.AirCleaner;
import com.example.project_smart_home.object.Device;
import com.example.project_smart_home.object.DoorLock;
import com.example.project_smart_home.object.Humidifier;
import com.example.project_smart_home.object.MoodLight;
import com.example.project_smart_home.object.Window;
import com.example.project_smart_home.order.DeviceOrder;
import com.example.project_smart_home.order.Order;

import static com.example.project_smart_home.utils.Constants.AIRCLEANER;
import static com.example.project_smart_home.utils.Constants.CMD_ACL_OFF;
import static com.example.project_smart_home.utils.Constants.CMD_ACL_ON;
import static com.example.project_smart_home.utils.Constants.CMD_DL_OPEN;
import static com.example.project_smart_home.utils.Constants.CMD_HMD_OFF;
import static com.example.project_smart_home.utils.Constants.CMD_HMD_ON;
import static com.example.project_smart_home.utils.Constants.CMD_ML_OFF;
import static com.example.project_smart_home.utils.Constants.CMD_ML_ON;
import static com.example.project_smart_home.utils.Constants.CMD_WINDOW_CLOSE;
import static com.example.project_smart_home.utils.Constants.CMD_WINDOW_OPEN;
import static com.example.project_smart_home.utils.Constants.DOORLOCK;
import static com.example.project_smart_home.utils.Constants.HUMIDIFIER;
import static com.example.project_smart_home.utils.Constants.MOODLIGHT;
import static com.example.project_smart_home.utils.Constants.WINDOW;

public class DeviceController {
    Order result;

    public DeviceController(DeviceOrder order) {
        String cmd = order.getMessage();
        switch (cmd){
            case "device_onoff":
                onoffDevice(order.getDevice());
                break;
            case "device_on":
                deviceOn(order.getDevice());
                break;
            case "device_off":
                deviceOff(order.getDevice());
                break;
        }
    }

    // 장치 onoff 및 인공지능 명령
    public void onoffDevice(Device device){
        Device selectedDevice = device;
        System.out.println("Kind :: " + selectedDevice.getDeviceKind());
        switch (selectedDevice.getDeviceKind()){                    // 장치 판별
            case AIRCLEANER:                                        // 공기청정기
                AirCleaner airCleaner = (AirCleaner)selectedDevice;
                airCleaner.onoffDevice();
                result = new DeviceOrder("device", "return", airCleaner);
                break;
            case DOORLOCK:                                          // 도어락
                DoorLock doorLock = (DoorLock)selectedDevice;
                doorLock.open();
                result = new DeviceOrder("device", "return", doorLock);
                break;
            case MOODLIGHT:                                         // 무드등
                MoodLight moodLight = (MoodLight)selectedDevice;
                moodLight.onoffDevice();
                result = new DeviceOrder("device", "return", moodLight);
                break;
            case WINDOW:                                            // 스마트창문
                Window window = (Window)selectedDevice;
                window.onoffDevice();
                result = new DeviceOrder("device", "return", window);
                break;
            case HUMIDIFIER:                                        // 가습기
                Humidifier humidifier = (Humidifier)selectedDevice;
                humidifier.onoffDevice();
                result = new DeviceOrder("device", "return", humidifier);
                break;
        }
    }

    public void deviceOn(Device device){
        String kind = device.getDeviceKind();
        if (device.getState() != 1){                                    // 장치가 이미 켜져있지 않은 경우
            device.setState(1);
            switch (kind){                                              // 장치 판별
                case AIRCLEANER:                                        // 공기청정기
                    AirCleaner airCleaner = (AirCleaner)device;
                    airCleaner.commandDevice(CMD_ACL_ON);
                    break;
                case DOORLOCK:                                          // 도어락
                    DoorLock doorLock = (DoorLock)device;
                    doorLock.commandDevice(CMD_DL_OPEN);
                    break;
                case MOODLIGHT:                                         // 무드등
                    MoodLight moodLight = (MoodLight)device;
                    moodLight.commandDevice(CMD_ML_ON);
                    break;
                case WINDOW:                                            // 스마트창문
                    Window window = (Window)device;
                    window.commandDevice(CMD_WINDOW_OPEN);
                    break;
                case HUMIDIFIER:                                        // 가습기
                    Humidifier humidifier = (Humidifier)device;
                    humidifier.commandDevice(CMD_HMD_ON);
                    break;
            }
        }
    }

    public void deviceOff(Device device){
        String kind = device.getDeviceKind();
        if (device.getState() != 2){                                    // 장치가 이미 켜져있지 않은 경우
            device.setState(2);
            switch (kind){                                              // 장치 판별
                case AIRCLEANER:                                        // 공기청정기
                    AirCleaner airCleaner = (AirCleaner)device;
                    airCleaner.commandDevice(CMD_ACL_OFF);
                    break;
                case MOODLIGHT:                                         // 무드등
                    MoodLight moodLight = (MoodLight)device;
                    moodLight.commandDevice(CMD_ML_OFF);
                    break;
                case WINDOW:                                            // 스마트창문
                    Window window = (Window)device;
                    window.commandDevice(CMD_WINDOW_CLOSE);
                    break;
                case HUMIDIFIER:                                        // 가습기
                    Humidifier humidifier = (Humidifier)device;
                    humidifier.commandDevice(CMD_HMD_OFF);
                    break;
            }
        }
    }

    public Order getResult() {
        return result;
    }
}
