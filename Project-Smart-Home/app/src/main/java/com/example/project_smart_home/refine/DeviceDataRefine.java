package com.example.project_smart_home.refine;
import com.example.project_smart_home.object.Device;
import com.example.project_smart_home.object.DeviceData;



import java.util.StringTokenizer;

public class DeviceDataRefine {
    DeviceData deviceData;

    int i=1;
    public DeviceDataRefine(){}

    public DeviceData getDeviceData(String result){
        StringTokenizer token;
        token = new StringTokenizer(result,"/" );
        String temp;
        while (token.hasMoreTokens()) {
            temp = token.nextToken(); //값 받아오기
            switch(i){
                case 1:
                    deviceData=new DeviceData();
                    deviceData.setNumber(Integer.parseInt(temp));
                    i++;
                    break;
                case 2:
                    deviceData.setRoom(temp);
                    i++;
                    break;
                case 3:
                    deviceData.setRegday(temp);
                    i++;
                    break;
                case 4:
                    deviceData.setTemperature(Integer.parseInt(temp));
                    i++;
                    break;
                case 5:
                    deviceData.setHumidity(Integer.parseInt(temp));
                    i++;
                    break;
                case 6:
                    deviceData.setDust(Integer.parseInt(temp));
                    i=1;
                    break;
            }
        }

        return deviceData;
    }
}
