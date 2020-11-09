package com.example.project_smart_home.refine;

import com.example.project_smart_home.object.AISet;
import com.example.project_smart_home.object.DataCondition;
import com.example.project_smart_home.object.DateCondition;
import com.example.project_smart_home.object.Device;
import com.example.project_smart_home.object.DeviceWorking;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class AIRefine {
    AISet aiLoad;
    ArrayList<AISet> aiLoadList = new ArrayList<>();
    int i=1;
    public ArrayList<AISet> getAIList(String result){
        StringTokenizer token;
        token = new StringTokenizer(result,"/" );
        Device device = new Device();
        DeviceWorking dvWorking = new DeviceWorking();
        DataCondition dataCond = new DataCondition();
        DateCondition dateCond = new DateCondition();
        String temp;
        int id = 1;
        while (token.hasMoreTokens()) {
            temp = token.nextToken(); //값 받아오기
            switch(i){
                case 1:
                    aiLoad = new AISet();
                    aiLoad.setRoomname(temp);
                    i++;
                    break;
                case 2:
                    device.setModel(temp);
                    i++;
                    break;
                case 3:
                    dataCond.setTemp(Integer.parseInt(temp));
                    i++;
                    break;
                case 4:
                    dataCond.setTemp_ab(Integer.parseInt(temp));
                    i++;
                    break;
                case 5:
                    dataCond.setDust(Integer.parseInt(temp));
                    i++;
                    break;
                case 6:
                    dataCond.setDust_ab(Integer.parseInt(temp));
                    i++;
                    break;
                case 7:
                    dataCond.setHum(Integer.parseInt(temp));
                    i++;
                    break;
                case 8:
                    dataCond.setHum_ab(Integer.parseInt(temp));
                    i++;
                    break;
                case 9:
                    String[] days = {"일", "월", "화", "수", "목", "금", "토"};
                    for(int i = 0; i < days.length; i++)
                        if (days[i].equals(temp))
                            dateCond.selectDay(i);
                    i++;
                    break;
                case 10:
                    if (temp.equals("on"))
                        dvWorking.setOnoff(true);
                    else if (temp.equals("off"))
                        dvWorking.setOnoff(false);
                    i++;
                    break;
                case 11:
                    dateCond.setTime(temp);
                    i++;
                    break;
                case 12:
                    dateCond.setInterval(Integer.parseInt(temp));
                    i++;
                    break;
                case 13:
                    dvWorking.setDevice(device);
                    aiLoad.setDateCondition(dateCond);
                    aiLoad.addDataCondition(dataCond);
                    aiLoad.addWorking(dvWorking);
                    aiLoad.setAiSetID(id);
                    id++;
                    i=1;
                    aiLoadList.add(aiLoad);
                    break;
            }
        }

        return aiLoadList;
    }
}
