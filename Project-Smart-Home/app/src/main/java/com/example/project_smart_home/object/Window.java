package com.example.project_smart_home.object;

import com.example.project_smart_home.Task.WindowTask;

import static com.example.project_smart_home.utils.Constants.APP_TEST;
import static com.example.project_smart_home.utils.Constants.CMD_WINDOW_AI;
import static com.example.project_smart_home.utils.Constants.CMD_WINDOW_CLOSE;
import static com.example.project_smart_home.utils.Constants.CMD_WINDOW_OPEN;

public class Window extends Device{

    public Window(String name, String deviceKind, String model, int state, String room) {
        super(name, deviceKind, model, state, room);
    }

    public Window() {
    }

    @Override
    public void onoffDevice() {
        switch (state){                 // 디바이스 상태
            case 1:                     // open일 경우
                setState(3);            // 인공지능모드로 변경
                if(!APP_TEST) commandDevice(CMD_WINDOW_AI);
                break;
            case 2:                     // close일 경우
                setState(1);            // open으로 변경
                if(!APP_TEST) commandDevice(CMD_WINDOW_OPEN);
                break;
            case 3:                     // 인공지능 모드일 경우
                setState(2);            // close로 변경
                if(!APP_TEST) commandDevice(CMD_WINDOW_CLOSE);
                break;
        }
    }

    public void commandDevice(String cmd){
        super.communicateTask = new WindowTask();
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