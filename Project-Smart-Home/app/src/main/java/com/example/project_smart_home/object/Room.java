package com.example.project_smart_home.object;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {
    String roomname;
    ArrayList<Device> devicelist = new ArrayList<Device>();

    public Room(String roomname) {
        this.roomname = roomname;
    }

    public Device getDevice(int i ){ return devicelist.get(i); }

    public int getDeviceCount(){ return devicelist.size(); }

    public void removeDevice(int n){ devicelist.remove(n); }

    public void removeDevice(Device device){ devicelist.remove(device); }

    public void addDevice(Device device){ devicelist.add(device); }

    public String getRoomname() { return roomname; }

    public ArrayList<Device> getDevicelist() { return devicelist; }

    public void setRoomname(String roomname) { this.roomname = roomname; }

    public void setDevicelist(ArrayList<Device> devicelist) { this.devicelist = devicelist; }
}
