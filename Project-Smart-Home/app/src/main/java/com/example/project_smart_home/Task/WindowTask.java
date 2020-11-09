package com.example.project_smart_home.Task;

import static com.example.project_smart_home.utils.Constants.PATH_CODE_WINDOW;
import static com.example.project_smart_home.utils.Constants.SEND_CMD;
import static com.example.project_smart_home.utils.Constants.SEND_MODEL;

public class WindowTask extends CommunicateTask {

    @Override
    protected String doInBackground(String... strings) {
        super.selectPath(PATH_CODE_WINDOW);
        super.selectTask(SEND_MODEL);
        super.selectCmd(SEND_CMD);
        return super.doInBackground(strings);
    }
}

