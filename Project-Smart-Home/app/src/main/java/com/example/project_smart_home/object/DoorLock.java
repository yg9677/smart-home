package com.example.project_smart_home.object;

import com.example.project_smart_home.Task.DoorLockTask;

import static com.example.project_smart_home.utils.Constants.APP_TEST;
import static com.example.project_smart_home.utils.Constants.CMD_DL_OPEN;

public class DoorLock extends Device{

    public DoorLock(String name, String deviceKind, String model, int state, String room) {
        super(name, deviceKind, model, state, room);
    }

    public DoorLock() {
    }

    public void commandDevice(String cmd){
        super.communicateTask = new DoorLockTask();
        super.communicateTask.execute(cmd);
    }

    public void open(){
        if(!APP_TEST) commandDevice(CMD_DL_OPEN);
    }
}
