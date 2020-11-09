package com.example.project_smart_home.order;

import com.example.project_smart_home.object.AISet;

public class AIsetOrder extends Order{
    AISet aiSet;

    public AIsetOrder(String mode, String type, String message, AISet aiSet) {
        super(mode, type, message);
        this.aiSet = aiSet;
    }

    public AISet getAISet() {
        return aiSet;
    }
}
