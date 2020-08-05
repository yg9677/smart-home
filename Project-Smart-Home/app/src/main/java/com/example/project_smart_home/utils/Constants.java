package com.example.project_smart_home.utils;

import java.util.ArrayList;
import java.util.List;

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
}
