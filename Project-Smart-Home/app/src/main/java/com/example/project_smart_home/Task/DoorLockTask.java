package com.example.project_smart_home.Task;

import static com.example.project_smart_home.utils.Constants.SEND_ARDUINO_DOORLOCK;

public class DoorLockTask extends CommunicateTask {

    @Override
    protected String doInBackground(String... strings) {
        super.selectTask(SEND_ARDUINO_DOORLOCK);
        return super.doInBackground(strings);
    }
}

