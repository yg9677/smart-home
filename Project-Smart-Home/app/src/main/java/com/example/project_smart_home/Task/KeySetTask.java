package com.example.project_smart_home.Task;


import static com.example.project_smart_home.utils.Constants.PATH_CODE_DOORLOCK_KEYSET;
import static com.example.project_smart_home.utils.Constants.SEND_CMD;
import static com.example.project_smart_home.utils.Constants.SEND_MODEL;


public class KeySetTask extends CommunicateTask {
    @Override
    protected String doInBackground(String... strings) {
        super.selectCmd(SEND_CMD);
        super.selectPath(PATH_CODE_DOORLOCK_KEYSET);
        super.selectTask(SEND_MODEL);
        return super.doInBackground(strings);
    }
}

