package com.example.project_smart_home.object;

public abstract class Condition {
    int type;

    public int getType() {
        return type;
    }

    @Override
    public abstract String toString();
}
