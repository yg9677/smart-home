package com.example.project_smart_home;

import com.example.project_smart_home.order.DeviceOrder;
import com.example.project_smart_home.order.Order;


import static com.example.project_smart_home.utils.Constants.CONNECTED_MODE_SERVER;
import static com.example.project_smart_home.utils.Constants.TYPE_OF_DEVICE;
import static com.example.project_smart_home.utils.Constants.TYPE_OF_RECEIVE;
import static com.example.project_smart_home.utils.Constants.TYPE_OF_SEND;

public class MasterController {
    ReceiveController receiveController;
    DeviceController deviceController;
    SendController sendController;
    public MasterController(){

    }

    public Order communicate(Order order){
        Order returnOrder = null;
        switch (order.getMode()){                           // 서버와 통신 모드일 경우
            case CONNECTED_MODE_SERVER:
                switch (order.getType()){                   // 컨트롤러 판별
                    case TYPE_OF_RECEIVE:
                        receiveController = new ReceiveController(order);
                        returnOrder = receiveController.getResult();
                        break;
                    case TYPE_OF_DEVICE:
                        deviceController = new DeviceController((DeviceOrder)order);
                        returnOrder = deviceController.getResult();
                        break;
                    case TYPE_OF_SEND:
                        sendController = new SendController(order);
                        returnOrder = sendController.getResult();
                        break;
                }
                break;
        }

        return  returnOrder;
    }
}
