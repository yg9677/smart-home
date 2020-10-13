package com.example.project_smart_home.object;

import com.example.project_smart_home.Task.AirCleanerTask;

import static com.example.project_smart_home.utils.Constants.CMD_ACL_AI;
import static com.example.project_smart_home.utils.Constants.CMD_ACL_OFF;
import static com.example.project_smart_home.utils.Constants.CMD_ACL_ON;

public class AirCleaner extends Device{

    @Override
    public void onoffDevice() {
        switch (state){                 // 디바이스 상태
            case 1:                     // 켜져있을 경우
                setState(3);            // 인공지능모드로 변경
                commandDevice(CMD_ACL_AI);
                break;
            case 2:                     // 꺼져있을 경우
                setState(1);            // 켜짐으로 변경
                commandDevice(CMD_ACL_ON);
                break;
            case 3:                     // 인공지능 모드일 경우
                setState(2);            // 꺼짐으로 변경
                commandDevice(CMD_ACL_OFF);
                break;
        }
    }

    public void commandDevice(String cmd) {
        super.communicateTask = new AirCleanerTask();
        super.communicateTask.execute(cmd);
    }

    public void setByState(){
        super.setByState();
        switch (state){
            case 3:
                mode = "인공지능모드";
                onoff = true;
                break;
        }
    }

    @Override
    public void setState(int state) {
        super.setState(state);
        setByState();
    }
}
