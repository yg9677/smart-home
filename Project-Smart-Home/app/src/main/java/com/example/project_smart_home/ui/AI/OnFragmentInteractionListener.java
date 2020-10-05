package com.example.project_smart_home.ui.AI;

import android.net.Uri;

import com.example.project_smart_home.object.Device;
import com.example.project_smart_home.object.Room;

public interface OnFragmentInteractionListener {
    void onFragmentInteraction(Uri uri);

    void onSelectRoomInteraction(Room room);

    void onSelectConditionInteraction(int item);

    void addWorkingInteraction(Room room);

    void addAI_Interaction();

    void onSelectDeviceInteraction(Device device);
}
