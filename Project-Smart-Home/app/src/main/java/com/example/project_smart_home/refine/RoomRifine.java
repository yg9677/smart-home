package com.example.project_smart_home.refine;
import com.example.project_smart_home.object.Room;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class RoomRifine {
    Room room;
    ArrayList<Room> list = new ArrayList<>();
    int i=1;
    public RoomRifine(){}

    public ArrayList<Room> getRoomList(String result){
        StringTokenizer token;
        token = new StringTokenizer(result,"/" );
        String temp;
        while (token.hasMoreTokens()) {
            temp = token.nextToken(); //값 받아오기
            switch(i){
                case 1:
                    room=new Room();
                    room.setRoom(temp);
                    i++;
                    break;
                case 2:
                    room.setAddress(temp);
                    i++;
                    break;
                case 3:
                    room.setKind(temp);
                    i++;
                    break;
                case 4:
                    room.setSize(Integer.parseInt(temp));
                    i=1;
                    list.add(room);
                    break;
            }
        }

        return list;
    }
}
