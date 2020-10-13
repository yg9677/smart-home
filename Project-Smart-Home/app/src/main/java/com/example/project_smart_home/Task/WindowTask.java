package com.example.project_smart_home.Task;

import static com.example.project_smart_home.utils.Constants.SEND_ARDUINO_WINDOW;

public class WindowTask extends CommunicateTask {

    @Override
    protected String doInBackground(String... strings) {
        super.selectTask(SEND_ARDUINO_WINDOW);
        return super.doInBackground(strings);
    }
}

