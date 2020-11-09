package com.example.project_smart_home.Task;

import static com.example.project_smart_home.utils.Constants.PATH_CODE_ROOM;
import static com.example.project_smart_home.utils.Constants.SEND_ARDUINO_ROOM;

public class RoomTask extends CommunicateTask {

    @Override
    protected String doInBackground(String... strings) {
        super.selectTask(SEND_ARDUINO_ROOM);
        super.selectPath(PATH_CODE_ROOM);
        return super.doInBackground(strings);
    }
}

