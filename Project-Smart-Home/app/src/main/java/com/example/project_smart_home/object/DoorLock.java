package com.example.project_smart_home.object;

import com.example.project_smart_home.Task.DoorLockTask;

import static com.example.project_smart_home.utils.Constants.CMD_DL_OPEN;

public class DoorLock extends Device{
    public void commandDevice(String cmd){
        super.communicateTask = new DoorLockTask();
        super.communicateTask.execute(cmd);
    }

    public void open(){
        commandDevice(CMD_DL_OPEN);
    }
}
