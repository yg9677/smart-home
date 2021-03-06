package com.example.project_smart_home.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class DataCondition extends Condition implements Parcelable {
    int[] ab = {1, 1, 1};    // 이상 이하 여부  1: 이상 2: 미만
    int[] data = {-1, -1, -1}; // 기준 수치

    public DataCondition() {
        super.type = 2;
    }

    public String getTemp_ab() {
        String abStr = "이상";
        if (ab[0] == 2) abStr = "미만";
        return abStr;
    }

    public String getDust_ab() {
        String abStr = "이상";
        if (ab[1] == 2) abStr = "미만";
        return abStr;
    }

    public String getHum_ab() {
        String abStr = "이상";
        if (ab[2] == 2) abStr = "미만";
        return abStr;
    }

    public int getTemp() { return this.data[0]; }

    public int getDust() {
        return this.data[1];
    }

    public int getHum() {
        return this.data[2];
    }

    public void setTemp_ab(int temp_ab) { this.ab[0] = temp_ab; }

    public void setDust_ab(int dust_ab) {
        this.ab[1] = dust_ab;
    }

    public void setHum_ab(int hum_ab) { this.ab[2] = hum_ab; }

    public void setTemp(int temp) {
        this.data[0] = temp;
    }

    public void setDust(int dust) {
        this.data[1] = dust;
    }

    public void setHum(int hum) {
        this.data[2] = hum;
    }


    @Override
    public String toString() {
        String tempStr = "", dustStr = "", humStr = "";
        String[] abStr = {"이상", "이상", "이상"};
        if (ab[0] == 2) abStr[0] = "미만";
        if (ab[1] == 2) abStr[1] = "미만";
        if (ab[2] == 2) abStr[2] = "미만";

        if(data[0] >= 0) tempStr = "온도 " + data[0] + abStr[0];
        if(data[1] >= 0) dustStr = "미세먼지 " + data[1] + abStr[1];
        if(data[2] >= 0) humStr = "습도 " + data[2] + abStr[2];

        return tempStr + "/" + dustStr + "/" + humStr;
    }

    public boolean checkedData(MeasuredData md){
        int tn = 0;
        int[] mds = {md.getTemperature(), md.getDust(), md.getHumidity()};
        for (int i = 0; i < 3; i++){
            if (ab[i] == 2)
                if (this.data[i] > mds[i])
                    tn++;
            else
                if (this.data[i] <= mds[i])
                    tn++;
        }
        if (tn == 3)
            return true;
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(ab);
        parcel.writeIntArray(data);
    }

    protected DataCondition(Parcel in) {
        ab = in.createIntArray();
        data = in.createIntArray();
    }

    public static final Creator<DataCondition> CREATOR = new Creator<DataCondition>() {
        @Override
        public DataCondition createFromParcel(Parcel in) {
            return new DataCondition(in);
        }

        @Override
        public DataCondition[] newArray(int size) {
            return new DataCondition[size];
        }
    };
}
