package com.example.project_smart_home.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCondition extends Condition implements Parcelable {

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
       if(interval > 0)
            itvstr = + interval + "분 후";
        String tstr = "";
        if(!time.equals(""))
            tstr = time + " ";

        String daystr = "";
        String[] dow = {"일", "월", "화", "수", "목", "금", "토"};

        for(int i = 0; i < days.length; i++){
            if(days[i]) {
                daystr += dow[i];
                if (i < days.length - 1)
                    daystr += ",";
            }
        }

        return tstr + daystr + " " + itvstr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(interval);
        parcel.writeBooleanArray(days);
        parcel.writeString(time);
    }

    protected DateCondition(Parcel in) {
        interval = in.readInt();
        days = in.createBooleanArray();
        time = in.readString();
    }

    public static final Creator<DateCondition> CREATOR = new Creator<DateCondition>() {
        @Override
        public DateCondition createFromParcel(Parcel in) {
            return new DateCondition(in);
        }

        @Override
        public DateCondition[] newArray(int size) {
            return new DateCondition[size];
        }
    };
}
