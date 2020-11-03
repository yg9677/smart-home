package com.example.project_smart_home.ui.room;

import com.example.project_smart_home.object.Room;

import java.util.ArrayList;

public interface OnRoomListListener {
    public void onClickRoom(int i);
    public void changeRoomItem(int fromPosition, int toPosition);
}
