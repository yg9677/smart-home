package com.example.project_smart_home.Task;

import static com.example.project_smart_home.utils.Constants.SEND_ARDUINO_MOODLIGHT;

public class MoodLightTask extends CommunicateTask {

    @Override
    protected String doInBackground(String... strings) {
        super.selectTask(SEND_ARDUINO_MOODLIGHT);
        return super.doInBackground(strings);
    }
}

