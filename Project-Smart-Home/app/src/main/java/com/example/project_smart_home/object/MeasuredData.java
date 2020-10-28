package com.example.project_smart_home.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class MeasuredData implements Serializable {
    private int number;
    private String room;
    private String time_ymd;
    private String time_hms;
    private String regday;
    private int temperature;
    private int humidity;
    private int dust;

    public String toString() {
        return number+"/"+room+"/"+regday+"/"+temperature+"/"+humidity+"/"+dust+"/";
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number=number;
    }
    public String getRoom() {
        return room;
    }
    public void setRoom(String room) {
        this.room=room;
    }
    public String getTimeYMD() {
        return this.time_ymd;
    }
    public void setTimeYMD(String time_ymd) {
        this.time_ymd=time_ymd;
    }
    public String getTimeHMS() {
        return this.time_hms;
    }
    public void setTimeHMS(String time_hms) {
        this.time_hms=time_hms;
    }
    public String getRegday() {
        return regday;
    }
    public void setRegday(String regday) {
        this.regday=regday;
    }

    public int getTemperature() {
        return temperature;
    }
    public void setTemperature(int temperature) {
        this.temperature=temperature;
    }
    public int getHumidity() {
        return humidity;
    }
    public void setHumidity(int humidit) {
        this.humidity=humidit;
    }
    public int getDust() {
        return dust;
    }
    public void setDust(int dust) {
        this.dust=dust;
    }
}