package com.example.project_smart_home.Task;

import static com.example.project_smart_home.utils.Constants.SEND_ARDUINO_AIRCLEANER;

public class AirCleanerTask extends CommunicateTask {

    @Override
    protected String doInBackground(String... strings) {
        super.selectTask(SEND_ARDUINO_AIRCLEANER);
        return super.doInBackground(strings);
    }
}

