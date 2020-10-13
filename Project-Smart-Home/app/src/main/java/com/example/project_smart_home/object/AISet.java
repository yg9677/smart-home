package com.example.project_smart_home.object;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class AISet implements Serializable {
    String roomname;
    ArrayList<DeviceWorking> workings = new ArrayList<>();
    ArrayList<DataCondition> dataConditions = new ArrayList<>();
    DateCondition dateCondition;

    public void setDateCondition(DateCondition dateCondition) {
        this.dateCondition = dateCondition;
    }

    public void addWorking(DeviceWorking dw){
        workings.add(dw);
    }

    public void addDataCondition(DataCondition dc){
        dataConditions.add(dc);
    }

    public DataCondition getDataCondition(int i) { return dataConditions.get(i); }

    public DeviceWorking getDeviceWorking(int i) { return workings.get(i); }

    public DateCondition getDateCondition() {
        return dateCondition;
    }

    public int getDataCondSize() { return dataConditions.size(); }

    public int getWorkingsSize() { return workings.size(); }

    public boolean isEmptyDataCond() { return  dataConditions.isEmpty(); }

    public boolean isNullDateCondition() { return dateCondition == null; }

    public boolean isEmpty(){
        if (workings.isEmpty() && dataConditions.isEmpty() && isNullDateCondition()){
            return true;
        }
        return false;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }
}
