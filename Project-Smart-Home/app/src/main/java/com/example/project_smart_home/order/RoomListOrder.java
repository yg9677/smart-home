package com.example.project_smart_home.order;

import com.example.project_smart_home.object.Room;

import java.util.ArrayList;

public class RoomListOrder extends Order{
    ArrayList<Room> roomArrayList = new ArrayList<>();

    public RoomListOrder(String mode, String type, String message, ArrayList<Room> rooms) {
        super(mode, type, message);
        this.roomArrayList = rooms;
    }

    public ArrayList<Room> getRoomArrayList() {
        return roomArrayList;
    }
}
