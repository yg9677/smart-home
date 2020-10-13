package com.example.project_smart_home.utils;

import java.util.ArrayList;
import java.util.List;

// 상수, 문자열
public class Constants {

    // 액티비티 간 전송 메시지
    public static final String EXTRA_MESSAGE_ROOM_LIST = "room_arraylist";
    public static final String EXTRA_MESSAGE_ROOM = "room";
    public static final String EXTRA_MESSAGE_DEVICE = "device";
    public static final String EXTRA_MESSAGE_ROOM_NAME = "room_name";
    public static final String EXTRA_MESSAGE_MEMBER = "member";

    // 측정 데이터 텍스트
    public static final List<String> TEXT_MEASUREDDATA = new ArrayList<String>() {
        {
            add("온도");
            add("습도");
            add("미세먼지");
            add("불쾌지수");
        }
    };

    // SharedPreferences 토큰
    public static final String USER_ID = "user_id";

    // 기능별 구분 텍스트
    public static final String FUNCTION_KEY_TEMP = "temp";

    // 사용자 인공지능 조건 선택 값
    public static final int AI_CONDITION_KEY_DATE = 1;
    public static final int AI_CONDITION_KEY_DATA = 2;

    // 명령 구별 값
    public static final String AIRCLEANER = "공기청정기";    // 공기청정기
    public static final String DEVICEDATA = "room";    //
    public static final String DEVICE = "code";        // 장치
    public static final String DOORLOCK = "도어락";      // 도어락
    public static final String KEYSET = "key";         // 키
    public static final String KEYTOUCH = "code";      // 키 터치
    public static final String MOODLIGHT = "무드등";     // 무드등
    public static final String ROOM = "방";          // 방
    public static final String WINDOW = "스마트창문";      // 창문

    // 아두이노 통신 구별 값
    public static final String SEND_ARDUINO_AIRCLEANER = "mode";    // 공기청정기
    public static final String SEND_ARDUINO_MESUREDDATA = "room";    // 측정 데이터
    public static final String SEND_ARDUINO_DEVICE = "code";        // 장치
    public static final String SEND_ARDUINO_DOORLOCK = "door";      // 도어락
    public static final String SEND_ARDUINO_KEYSET = "key";         // 키
    public static final String SEND_ARDUINO_KEYTOUCH = "code";      // 키 터치
    public static final String SEND_ARDUINO_MOODLIGHT = "mood";     // 무드등
    public static final String SEND_ARDUINO_ROOM = "room";          // 방
    public static final String SEND_ARDUINO_WINDOW = "window";      // 창문

    // 서버 통신 구별 값
    public static final String SEND_SERVER_LOGIN = "login"; // 로그인

    // 공기청정기 명령
    public static final String CMD_ACL_ON = "manual/3";             // 켜짐
    public static final String CMD_ACL_OFF = "manual/4";            // 꺼짐
    public static final String CMD_ACL_AI = "auto";                 // 인공지능

    // 도어락 명령
    public static final String CMD_DL_OPEN = "door/open";           // open

    // 무드등 명령
    public static final String CMD_ML_ON = "room1/moodLight/on";    // 켜짐
    public static final String CMD_ML_OFF = "room1/moodLight/off";  // 꺼짐

    // 스마트창문 명령
    public static final String CMD_WINDOW_OPEN = "room1/window/open";               // open
    public static final String CMD_WINDOW_CLOSE = "room1/window/close";             // close
    public static final String CMD_WINDOW_AI = "room1/window/aiMode";               // 인공지능
    public static final String CMD_WINDOW_MANUAL = "room1/window/manualMode";       // 수동모드

    // 통신모드
    public static final String CONNECTED_MODE_SERVER = "server";           // 서버

    // 전송 종류
    public static final String TYPE_OF_RECEIVE = "receive";                 // ReceiveController
    public static final String TYPE_OF_DEVICE = "device";                 // DeviceController
}
