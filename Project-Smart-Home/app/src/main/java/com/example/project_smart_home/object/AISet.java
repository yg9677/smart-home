package com.example.project_smart_home.object;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class AISet implements Parcelable {
    int aiSetID;

    String roomname;
    ArrayList<DeviceWorking> workings = new ArrayList<>();
    ArrayList<DataCondition> dataConditions = new ArrayList<>();
    DateCondition dateCondition;
    int on = 1;

    public int getAiSetID() {
        return aiSetID;
    }

    public void setAiSetID(int aiSetID) {
        this.aiSetID = aiSetID;
    }

    public int getOn() {
        return on;
    }

    public void setOn(int on) {
        this.on = on;
    }

    public AISet() {}

    public void setDateCondition(DateCondition dateCondition) {
        this.dateCondition = dateCondition;
    }

    public void addWorking(DeviceWorking dw){
        workings.add(dw);
    }

    public void addDataCondition(DataCondition dc){
        dataConditions.add(dc);
    }

    public DataCondition getDataCondition(int i) { return dataConditions.get(i); }

    public DeviceWorking getDeviceWorking(int i) { return workings.get(i); }

    public DateCondition getDateCondition() {
        return dateCondition;
    }

    public int getDataCondSize() { return dataConditions.size(); }

    public int getWorkingsSize() { return workings.size(); }

    public boolean isEmptyDataCond() { return  dataConditions.isEmpty(); }

    public boolean isNullDateCondition() { return dateCondition == null; }

    public boolean isEmpty(){
        if (workings.isEmpty() && dataConditions.isEmpty() && isNullDateCondition()){
            return true;
        }
        return false;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(roomname);
        parcel.writeTypedList(workings);
        parcel.writeTypedList(dataConditions);
        parcel.writeParcelable(dateCondition, i);
        parcel.writeInt(on);
    }

    protected AISet(Parcel in) {
        roomname = in.readString();
        workings = in.createTypedArrayList(DeviceWorking.CREATOR);
        dataConditions = in.createTypedArrayList(DataCondition.CREATOR);
        dateCondition = in.readParcelable(DateCondition.class.getClassLoader());
        on = in.readInt();
    }

    public static final Creator<AISet> CREATOR = new Creator<AISet>() {
        @Override
        public AISet createFromParcel(Parcel in) {
            return new AISet(in);
        }

        @Override
        public AISet[] newArray(int size) {
            return new AISet[size];
        }
    };
}
