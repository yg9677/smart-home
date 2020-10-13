package com.example.project_smart_home.thread;

import com.example.project_smart_home.Task.CommunicateTask;
import com.example.project_smart_home.Task.MeasuredDataTask;
import com.example.project_smart_home.object.MeasuredData;
import com.example.project_smart_home.refine.MeasuredDataRefine;

import java.util.concurrent.ExecutionException;

public class Measurement extends Thread {
    CommunicateTask task = new MeasuredDataTask();

    int roomArrayLength;
    OnThreadListener mListener;

    public Measurement(int roomArrayLength, OnThreadListener listener) {
        this.roomArrayLength = roomArrayLength;
        this.mListener = listener;
    }

    @Override
    public void run() {
        while (true){
            String result = "";
            try {
                for (int i = 0; i < roomArrayLength; i++){
                    result = task.execute("").get();
                    mListener.onUpdateMeasuredData(new MeasuredDataRefine().getMeasuredData(result), i);
                }
                sleep(60000);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
