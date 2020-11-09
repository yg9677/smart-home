package com.example.project_smart_home.Task;


import static com.example.project_smart_home.utils.Constants.SEND_CMD;
import static com.example.project_smart_home.utils.Constants.SEND_MODEL;

public class DeviceDataTask extends CommunicateTask {

    @Override
    protected String doInBackground(String... strings) {
        super.selectTask(SEND_MODEL);
        super.selectCmd(SEND_CMD);
        return super.doInBackground(strings);
    }
}
