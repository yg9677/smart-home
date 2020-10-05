package com.example.project_smart_home.object;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Device extends Room implements Serializable{

    String name;
    String deviceKind;
    String model;
    int state;

    public Device(String room, int size, String kind, String address, String user,String name, String deviceKind, String model, int state){
        super(room, size, kind,address,user);
        this.name = name;
        this.deviceKind =deviceKind;
        this.model = model;
        this.state = state;
    }

    public Device(){}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDeviceKind(String deviceKind){this.deviceKind=deviceKind;}

    public String getDeviceKind(){return this.deviceKind;}

    public void setModel(String model){this.model=model;}

    public String getModel(){return this.model;}

    public void setState(int state){this.state=state;}

    public int getState(){return this.state;}
}
