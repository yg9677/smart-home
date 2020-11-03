package com.example.project_smart_home.object;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.project_smart_home.Task.CommunicateTask;

import java.io.Serializable;

public class Device implements Parcelable {
    String room;
    String name;
    String deviceKind;
    String model;
    
    int state;
    boolean onoff;
    String mode;

    CommunicateTask communicateTask;

    public Device(String name, String deviceKind, String model, int state, String room){
        this.room = room;
        this.name = name;
        this.deviceKind =deviceKind;
        this.model = model;
        this.state = state;
        setByState();
    }

    public void onoffDevice(){
        switch (state){
            case 1:                 // 상태가 on일 경우
                setState(2);        // 상태를 off로 만든다.
                break;
            case 2:                 // 상태가 off일 경우
                setState(1);        // 상태를 on으로 만든다.
                break;
        }
    }

    // 장치로 명령어를 보내는 메서드
    public void commandDevice(String cmd){
        communicateTask = new CommunicateTask();
        communicateTask.execute(cmd);
    }

    // state에 따라 모드와 onoff 상태를 변경해주는 메서드
    protected void setByState(){
        switch (state){
            case 1:
                mode = "켜짐";
                onoff = true;
                break;
            case 2:
                mode = "꺼짐";
                onoff = false;
                break;
                default:
                    mode = "";
                    onoff = true;
                    break;
        }
    }

    public boolean isOnoff() {
        return onoff;
    }

    public String getMode() {
        return mode;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoom() {
        return room;
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

    public void setState(int state){this.state=state; setByState();}

    public int getState(){return this.state;}

    public void copy(Device dv){
        setName(dv.getName());
        setDeviceKind(dv.getDeviceKind());
        setModel(dv.getModel());
        setRoom(dv.getRoom());
        setState(dv.getState());
    }

    public Device(Parcel in){
        this.room = in.readString();
        this.name = in.readString();
        this.deviceKind = in.readString();
        this.model = in.readString();
        this.state = in.readInt();
        setByState();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.room);
        parcel.writeString(this.name);
        parcel.writeString(this.deviceKind);
        parcel.writeString(this.model);
        parcel.writeInt(this.state);
    }

    public static final Parcelable.Creator<Device> CREATOR = new Parcelable.Creator<Device>(){
        @Override
        public Device createFromParcel(Parcel in) {
            return new Device(in);
        }

        @Override
        public Device[] newArray(int i) {
            return new Device[i];
        }
    };
}
