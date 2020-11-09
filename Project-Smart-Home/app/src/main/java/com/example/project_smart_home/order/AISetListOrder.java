package com.example.project_smart_home.order;

import com.example.project_smart_home.object.AISet;

import java.util.ArrayList;

public class AISetListOrder extends Order{
    ArrayList<AISet> aiSetList;

    public AISetListOrder(String mode, String type, String message, ArrayList<AISet> aiSets) {
        super(mode, type, message);
        this.aiSetList = aiSets;
    }

    public ArrayList<AISet> getAISetList() {
        return aiSetList;
    }
}
