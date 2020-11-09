package com.example.project_smart_home.Task;

import static com.example.project_smart_home.utils.Constants.PATH_CODE_DOORLOCK_KEKTOUCH;
import static com.example.project_smart_home.utils.Constants.SEND_CMD;
import static com.example.project_smart_home.utils.Constants.SEND_MODEL;

public class KeyTouchTask extends CommunicateTask {

    @Override
    protected String doInBackground(String... strings) {
        super.selectCmd(SEND_CMD);
        super.selectPath(PATH_CODE_DOORLOCK_KEKTOUCH);
        super.selectTask(SEND_MODEL);
        return super.doInBackground(strings);
    }
}

