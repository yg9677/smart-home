package com.example.project_smart_home.object;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Device implements Serializable {
    String name;
    String func;
    Map<String, String> values;
    boolean onoff;

    public Device(String name){
        this.name = name;
        onoff = false;
        func = "";
        values = new HashMap<String, String>();
    }
    public String getFunc(){ return func; }

    public void setFunc(String f){ func = f; }

    public void addValue(String key, String value){ values.put(key, value); }

    public void removeValue(String key){ values.remove(key); }

    public String getValue(String key){ return values.get(key); }

    public void setName(String name) {
        this.name = name;
    }

    public void setOnoff(boolean onoff) {
        this.onoff = onoff;
    }

    public boolean isOnoff() {
        return onoff;
    }

    public String getName() {
        return name;
    }

}
