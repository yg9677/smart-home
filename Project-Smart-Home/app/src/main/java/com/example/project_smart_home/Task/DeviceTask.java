package com.example.project_smart_home.Task;

import static com.example.project_smart_home.utils.Constants.PATH_CODE_DEVICE;
import static com.example.project_smart_home.utils.Constants.SEND_ARDUINO_DEVICE;

public class DeviceTask extends CommunicateTask {

    @Override
    protected String doInBackground(String... strings) {
        super.selectTask(SEND_ARDUINO_DEVICE);
        super.selectPath(PATH_CODE_DEVICE);
        return super.doInBackground(strings);
    }
}

