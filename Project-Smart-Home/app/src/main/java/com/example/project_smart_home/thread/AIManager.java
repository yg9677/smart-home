package com.example.project_smart_home.thread;

import com.example.project_smart_home.object.AISet;
import com.example.project_smart_home.object.MeasuredData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AIManager extends Thread{
    ArrayList<AISet> aiSets = new ArrayList<>();
    Date[] cond_dates;
    Date today;

    OnThreadListener mListener;

    public AIManager(ArrayList<AISet> aiSets, OnThreadListener listener){
        this.aiSets = aiSets;
        cond_dates = new Date[aiSets.size()];
        this.mListener = listener;
    }

    @Override
    public void run() {
        today = new Date();
        int minute = 0;
        MeasuredData data;
        while (true){
            long currentTime = currenTime();    // 현재 시간
            int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
            try {
                for (int i = 0; i < cond_dates.length; i++){
                    cond_dates[i] = aiSets.get(1).getDateCondition().getDate();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < aiSets.size(); i++){
                if(cond_dates[i] == null){
                    if (checkedInterval(i,minute) &&                                // 경과시간(분)이 간격 조건과 일치하고
                        aiSets.get(i).getDateCondition().isDays(dayOfWeek))         // 요일도 현재 요일과 일치할 경우
                        if (aiSets.get(i).isEmptyDataCond())                        // 데이터 조건이 없을 경우
                            workDevice(i);                                          // 디바이스 동작
                        else{
                            if (checkedDataCond(i))                                 // 데이터 조건을 모두 만족할 경우
                                workDevice(i);                                      // 디바이스 동작
                        }
                }
                else {
                    if (currentTime == cond_dates[i].getTime() &&                   // 시간 조건이 현재 시간과 일치하고
                        aiSets.get(i).getDateCondition().isDays(dayOfWeek))         // 요일도 현재 요일과 일치할 경우
                        if (aiSets.get(i).isEmptyDataCond())                        // 데이터 조건이 없을 경우
                            workDevice(i);                                          // 디바이스 동작
                        else{
                            if (checkedDataCond(i))                                 // 데이터 조건을 모두 만족할 경우
                                workDevice(i);                                      // 디바이스 동작
                        }
                }
            }

            try {
                sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (minute > 60) minute = 0;
            minute++;
        }
    }

    long currenTime(){
        return new Date(new SimpleDateFormat("HH:mm").format(today)).getTime();
    }

    void workDevice(int s){
//        for (int i = 0; i < aiSets.get(s).getWorkingsSize(); i++){
//            if(aiSets.get(s).getDeviceWorking(i).isOnoff()){    // On 동작
//                mListener.onWork(aiSets.get(s).getDeviceWorking(i).getDevice().getName());
//            } else {                                            // Off 동작
//                mListener.offWork(aiSets.get(s).getDeviceWorking(i).getDevice().getName());
//            }
//        }
    }

    boolean checkedInterval(int s, int m) {
        return aiSets.get(s).getDateCondition().getInterval() == m;
    }

    boolean checkedDataCond(int index){
        MeasuredData data = mListener.getMeasuredData(aiSets.get(index).getRoomname());
        int tn = 0;
        for (int i = 0; i < aiSets.get(index).getDataCondSize(); i++){
            if (aiSets.get(index).getDataCondition(i).checkedData(data))
                tn++;
        }
        if (tn == aiSets.get(index).getDataCondSize())
            return true;
        return false;
    }

}
