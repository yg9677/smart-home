package com.example.project_smart_home.object;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCondition extends Condition implements Serializable {
    int interval = -1;
    boolean[] days = {false, false, false, false, false, false, false};
    String time = "";

    public DateCondition() {
        super.type = 1;
    }

    public boolean isDays(int i) { return days[i]; }

    public void selectDay(int selected){
        this.days[selected] = true;
    }

    public void unselectDay(int unselected){
        this.days[unselected] = false;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getInterval() {
        return interval;
    }

    public boolean[] getDays() {
        return days;
    }

    public String getTime() {
        return time;
    }

    public Date getDate() throws ParseException {
        if (time.equals("")) return null;
        return new SimpleDateFormat("HH:mm").parse(time);
    }

    @Override
    public String toString() {
        String itvstr = "";
        if(interval == 0)
            itvstr = "간격: 항상";
        else if(interval > 0)
            itvstr = "간격: " + interval + "분마다";
        String tstr = "";
        if(!time.equals(""))
            tstr = time;

        String daystr = "";
        String[] dow = {"일", "월", "화", "수", "목", "금", "토"};

        for(int i = 0; i < days.length; i++){
            if(days[i]) {
                daystr += dow[i];
                if (i < days.length - 1)
                    daystr += ",";
            }
        }

        return itvstr + tstr + daystr;
    }
}
