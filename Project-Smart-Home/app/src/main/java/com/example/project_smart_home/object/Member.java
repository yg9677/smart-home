package com.example.project_smart_home.object;

import java.io.Serializable;

public class Member implements Serializable {
    String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}