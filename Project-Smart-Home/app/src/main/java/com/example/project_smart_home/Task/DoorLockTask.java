package com.example.project_smart_home.Task;

import static com.example.project_smart_home.utils.Constants.PATH_CODE_DOORLOCK;
import static com.example.project_smart_home.utils.Constants.SEND_CMD;
import static com.example.project_smart_home.utils.Constants.SEND_MODEL;


public class DoorLockTask extends CommunicateTask {

    @Override
    protected String doInBackground(String... strings) {
        super.selectCmd(SEND_CMD);
        super.selectPath(PATH_CODE_DOORLOCK);
        super.selectTask(SEND_MODEL);
        return super.doInBackground(strings);
    }
}

