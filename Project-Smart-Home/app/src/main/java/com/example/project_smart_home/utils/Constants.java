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

    // 기능별 구분 텍스트
    public static final String FUNCTION_KEY_TEMP = "temp";

    // 사용자 인공지능 조건 선택 값
    public static final int AI_CONDITION_KEY_DATE = 1;
    public static final int AI_CONDITION_KEY_DATA = 2;
}
