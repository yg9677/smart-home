package com.example.project_smart_home.object;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {

    private String room;
    private int size;
    private String kind;
    private String address;

    ArrayList<Device> devicelist = new ArrayList<Device>();

    private MeasuredData measuredData = new MeasuredData(); // 측정 데이터


    public Room(String room, int size, String kind, String address, String user){
        this.room=room;
        this.size=size;
        this.kind=kind;
        this.address=address;
    }
    public Room(){}

    public MeasuredData getMeasuredData(){ return  measuredData; }

    public void setMeasuredData(MeasuredData measuredData) {
        synchronized (this){
            this.measuredData = measuredData;
        }
    }

    public Device findDevice(String name){
        for (int i = 0; i < devicelist.size(); i++){
            if (devicelist.get(i).getName().equals(name))
                return devicelist.get(i);
        }
        return null;
    }

    public String getRoom() { return this.room; }

    public void setRoom(String room) { this.room = room; }

    public int getSize(){return this.size;}

    public void setSize(int size){this.size=size;}

    public String getKind(){return this.kind;}

    public void setKind(String kind){this.kind=kind;}

    public String getAddress(){return this.address;}

    public void setAddress(String address){this.address=address;}

    public ArrayList<Device> getDevicelist() {
        return devicelist;
    }

    public void addDevice(Device device){ devicelist.add(device); }
}
