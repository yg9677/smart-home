package com.example.project_smart_home.ui.AI;

import android.net.Uri;

import com.example.project_smart_home.object.AISet;
import com.example.project_smart_home.object.DataCondition;
import com.example.project_smart_home.object.DateCondition;
import com.example.project_smart_home.object.Device;
import com.example.project_smart_home.object.DeviceWorking;
import com.example.project_smart_home.object.Room;

public interface OnFragmentInteractionListener {
    void onFragmentInteraction(Uri uri);

    void onSelectRoomInteraction(Room room);

    void onSelectConditionInteraction(int item);

    void addWorkingInteraction(Room room);

    void addAI_Interaction(AISet aiSet);

    void onSelectDeviceInteraction(Device device);

    void addDeviceWorking(DeviceWorking dw);

    void addDataCondition(DataCondition data);

    void setDateCondition(DateCondition date);
}
