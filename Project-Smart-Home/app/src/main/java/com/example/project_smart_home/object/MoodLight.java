package com.example.project_smart_home.object;

import com.example.project_smart_home.Task.MoodLightTask;

import static com.example.project_smart_home.utils.Constants.CMD_ML_OFF;
import static com.example.project_smart_home.utils.Constants.CMD_ML_ON;

public class MoodLight extends Device{
    public void commandDevice(String cmd){
        super.communicateTask = new MoodLightTask();
        super.communicateTask.execute(cmd);
    }
    public void onoffDevice(){
        switch (state){
            case 1:                 // 상태가 on일 경우
                setState(2);        // 상태를 off로 만든다.
                commandDevice(CMD_ML_OFF);
                break;
            case 2:                 // 상태가 off일 경우
                setState(1);        // 상태를 on으로 만든다.
                commandDevice(CMD_ML_ON);
                break;
        }
    }
}
