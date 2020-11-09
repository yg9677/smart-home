package com.example.project_smart_home;

import com.example.project_smart_home.Task.AISetTask;
import com.example.project_smart_home.Task.CommunicateTask;
import com.example.project_smart_home.object.AISet;
import com.example.project_smart_home.order.AIsetOrder;
import com.example.project_smart_home.order.Order;
import com.example.project_smart_home.refine.DateRefine;
import java.util.concurrent.ExecutionException;

import static com.example.project_smart_home.utils.Constants.PATH_CODE_AISET;

public class SendController {
    Order result;
    CommunicateTask requestTask;

    public SendController(Order order) {
        String cmd = order.getMessage();
        switch (cmd){
            case "send_aiset":
                sendAIset(order);
                break;
        }
    }

    private void sendAIset(Order order){
        AIsetOrder ao = (AIsetOrder) order;
        AISet aiSet = ao.getAISet();

        DateRefine refine = new DateRefine();
        String date [] = new String[3];
        AISetTask task= new AISetTask();

        date=refine.getData(aiSet.getDateCondition().toString());

        try {
            task.selectPath(PATH_CODE_AISET);
            task.execute(
                    MainActivity.myCode,
                    aiSet.getDeviceWorking(0).getDevice().getModel(),       // 동작할 디바이스 모델명
                    String.valueOf(aiSet.getDataCondition(0).getTemp()),    // 데이터 조건 온도
                    aiSet.getDataCondition(0).getTemp_ab(),                 // 온도의 이상 이하 여부
                    String.valueOf(aiSet.getDataCondition(0).getDust()),    // 데이터 조건 미세먼지
                    aiSet.getDataCondition(0).getDust_ab(),                 // 미세먼지 이상 이하 여부
                    String.valueOf(aiSet.getDataCondition(0).getHum()),     // 데이터 조건 습도
                    aiSet.getDataCondition(0).getHum_ab(),                  // 습도 이상 이하 여부
                    aiSet.getDeviceWorking(0).getOnoff(),                   // 디바이스 수행 동작
                    date[0], date[1], date[2],      //
                    aiSet.getRoomname()
            ).get();
        }catch(ExecutionException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }


    public Order getResult(){
        return result;
    }
}
