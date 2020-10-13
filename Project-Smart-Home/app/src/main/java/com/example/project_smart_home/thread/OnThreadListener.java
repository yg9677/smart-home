package com.example.project_smart_home.thread;

import com.example.project_smart_home.object.MeasuredData;

public interface OnThreadListener {
    public void onUpdateMeasuredData(MeasuredData md, int i);

    public void onWork(String name);

    public void offWork(String name);

    public MeasuredData getMeasuredData(String roomname);
}
