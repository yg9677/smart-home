package com.example.project_smart_home.Task;

import static com.example.project_smart_home.utils.Constants.PATH_CODE_MESUREDDATA;
import static com.example.project_smart_home.utils.Constants.SEND_ARDUINO_MESUREDDATA;


public class MeasuredDataTask extends CommunicateTask {

    @Override
    protected String doInBackground(String... strings) {
        super.selectTask(SEND_ARDUINO_MESUREDDATA);
        super.selectPath(PATH_CODE_MESUREDDATA);
        return super.doInBackground(strings);
    }
}

