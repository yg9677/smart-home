package com.example.project_smart_home.order;

import static com.example.project_smart_home.utils.Constants.CONNECTED_MODE_SERVER;

public class Order {
    String mode;
    String type;
    String message;

    public Order(String mode, String type, String message) {
        this.mode = mode;
        this.type = type;
        this.message = message;
    }

    public Order(String type, String message){
        this.mode = CONNECTED_MODE_SERVER;
        this.type = type;
        this.message = message;
    }

    public Order(){}

    public String getMode() {
        return mode;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
