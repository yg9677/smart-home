package com.example.project_smart_home.refine;
import com.example.project_smart_home.object.AirCleaner;
import com.example.project_smart_home.object.Device;
import com.example.project_smart_home.object.DoorLock;
import com.example.project_smart_home.object.MoodLight;
import com.example.project_smart_home.object.Window;


import java.util.ArrayList;
import java.util.StringTokenizer;

import static com.example.project_smart_home.utils.Constants.AIRCLEANER;
import static com.example.project_smart_home.utils.Constants.DOORLOCK;
import static com.example.project_smart_home.utils.Constants.MOODLIGHT;
import static com.example.project_smart_home.utils.Constants.WINDOW;

public class DeviceRifine {
    Device device;
    ArrayList<Device> list = new ArrayList<>();
    int i=1;
    public DeviceRifine(){}

    public ArrayList<Device> getDeviceList(String result){
        StringTokenizer token;
        token = new StringTokenizer(result,"/" );
        String temp;
        while (token.hasMoreTokens()) {
            temp = token.nextToken(); //값 받아오기
            switch(i){
                case 1:
                    device=new Device();
                    device.setName(temp);
                    i++;
                    break;
                case 2:
                    device.setDeviceKind(temp);
                    i++;
                    break;
                case 3:
                    device.setModel(temp);
                    i++;
                    break;
                case 4:
                    device.setRoom(temp);
                    i++;
                    break;
                case 5:
                    device.setState(Integer.parseInt(temp));
                    i=1;
                    list.add(castingDevice(device));
                    break;
            }
        }

        return list;
    }

    private Device castingDevice(Device device){
        Device castDevice;
        switch (device.getDeviceKind()){
            case AIRCLEANER:                                        // 공기청정기
                castDevice = new AirCleaner();
                break;
            case DOORLOCK:                                          // 도어락
                castDevice = new DoorLock();
                break;
            case MOODLIGHT:                                         // 무드등
                castDevice = new MoodLight();
                break;
            case WINDOW:                                            // 스마트창문
                castDevice = new Window();
                break;
                default:
                    castDevice = device;
        }
        castDevice.copy(device);
        return castDevice;
    }
}
