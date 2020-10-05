package com.example.project_smart_home.refine;
import com.example.project_smart_home.object.Device;


import java.util.ArrayList;
import java.util.StringTokenizer;

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
                    list.add(device);
                    break;
            }
        }

        return list;
    }
}
