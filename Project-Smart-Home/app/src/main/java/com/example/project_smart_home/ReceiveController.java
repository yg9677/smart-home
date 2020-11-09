package com.example.project_smart_home;

import com.example.project_smart_home.Task.AILoadTask;
import com.example.project_smart_home.Task.CommunicateTask;
import com.example.project_smart_home.Task.DeviceTask;
import com.example.project_smart_home.Task.RoomTask;
import com.example.project_smart_home.object.Device;
import com.example.project_smart_home.object.Room;
import com.example.project_smart_home.order.AISetListOrder;
import com.example.project_smart_home.order.Order;
import com.example.project_smart_home.order.RoomListOrder;
import com.example.project_smart_home.refine.AIRefine;
import com.example.project_smart_home.refine.DeviceRifine;
import com.example.project_smart_home.refine.RoomRifine;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.example.project_smart_home.utils.Constants.CONNECTED_MODE_SERVER;
import static com.example.project_smart_home.utils.Constants.TYPE_OF_RECEIVE;

public class ReceiveController {
    Order result;
    CommunicateTask requestTask;

    public ReceiveController(Order order) {
        String cmd = order.getMessage();
        switch (cmd){
            case "get_roomlist":
                getRoomList(order);
                break;
            case "get_ailist":
                getAIList(order);
                break;
        }
    }

    private void getRoomList(Order order){
        requestTask = new RoomTask();
        RoomRifine refine = new RoomRifine();
        String resultMsg = null;
        ArrayList<Room> roomList = null;
        try {
            resultMsg = requestTask.execute(MainActivity.myCode,MainActivity.myCode).get(); //현재 방 저장

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(resultMsg);
        roomList = refine.getRoomList(resultMsg); //데이터 정제 결과물: ArrayList<Room>
        ArrayList<Device> devicesList = getDeviceList();

        // 장치를 장치가 있는 방 객체에 추가
        for(int i=0; i<roomList.size(); i++) {
            for (int j=0; j < devicesList.size(); j++){
                if (roomList.get(i).getRoom().equals(devicesList.get(j).getRoom()))         // 장치의 방 정보와 방의 정보가 일치할 경우
                    roomList.get(i).addDevice(devicesList.get(j));
            }
        }

        result = new RoomListOrder(CONNECTED_MODE_SERVER, TYPE_OF_RECEIVE, "result:roomlist", roomList);
    }

    private ArrayList<Device> getDeviceList(){
        requestTask = new DeviceTask();
        DeviceRifine refine = new DeviceRifine();
        String resultMsg = null;
        try {
            resultMsg = requestTask.execute(MainActivity.myCode,MainActivity.myCode).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(resultMsg);

        return refine.getDeviceList(resultMsg);
    }

    public void getAIList(Order order){
        AILoadTask aiLoadTask = new AILoadTask();
        AIRefine refine = new AIRefine();
        String resultMsg = "";

        try {
            resultMsg = aiLoadTask.execute(MainActivity.myCode).get();
            result = new AISetListOrder("", "", "", refine.getAIList(resultMsg));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public Order getResult(){
        return result;
    }
}
