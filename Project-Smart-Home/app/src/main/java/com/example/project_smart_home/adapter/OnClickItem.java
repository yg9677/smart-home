package com.example.project_smart_home.adapter;

import com.example.project_smart_home.object.Device;
import com.example.project_smart_home.object.Room;

public interface OnClickItem {
    public Device onClickItem(Device deviceItem);
    public void onClickRoom(Room room);
}
