package com.example.project_smart_home.object;

public class Device {
    String name;
    String roomname;
    String func;

    public void setName(String name) {
        this.name = name;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getName() {
        return name;
    }

    public String getRoomname() {
        return roomname;
    }

    public String getFunc() {
        return func;
    }
}
