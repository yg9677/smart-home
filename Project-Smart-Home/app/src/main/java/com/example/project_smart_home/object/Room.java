package com.example.project_smart_home.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Parcelable, Comparable {
    private String room;
    private int size;
    private String kind;
    private String address;
    int seqNum;                     // 방 순서

    public int getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(int seqNum) {
        this.seqNum = seqNum;
    }

    ArrayList<Device> devicelist = new ArrayList<Device>();

    private MeasuredData measuredData = new MeasuredData(); // 측정 데이터


    public Room(String room, int size, String kind, String address){
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


    public Room(Parcel in){
        this.room = in.readString();
        this.size = in.readInt();
        this.kind = in.readString();
        this.address = in.readString();
        this.seqNum = in.readInt();
        in.readTypedList(devicelist, Device.CREATOR);
        this.measuredData = (MeasuredData) in.readSerializable();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.room);
        parcel.writeInt(this.size);
        parcel.writeString(this.kind);
        parcel.writeString(this.address);
        parcel.writeInt(this.seqNum);
        parcel.writeTypedList(this.devicelist);
        parcel.writeSerializable(this.measuredData);
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };

    @Override
    public int compareTo(Object o) {
        Room r = (Room)o;
        return this.seqNum - r.getSeqNum();
    }
}
