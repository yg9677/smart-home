package com.example.project_smart_home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.project_smart_home.R;
import com.example.project_smart_home.object.Device;
import com.example.project_smart_home.ui.device.DeviceControllerActivity;

import java.util.ArrayList;

public class DeviceGridViewAdapter extends BaseAdapter {
    LayoutInflater inf;
    ArrayList<Device> deviceArrayList = new ArrayList<Device>();
    String roomname;
    OnClickItem clickEvent;

    public DeviceGridViewAdapter(OnClickItem onClick, Context context, String roomname){
        clickEvent = onClick;
        this.roomname = roomname;
        this.inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return deviceArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return deviceArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            System.out.println("Device num :: " + position);
            System.out.println("Device Size :: " + deviceArrayList.size());
            final Device deviceItem = deviceArrayList.get(position);
            view = inf.inflate(R.layout.device_item, null);
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = DeviceControllerActivity.getStartIntent(inf.getContext(), deviceItem, roomname);
//                    inf.getContext().startActivity(intent);
//                }
//            });

            TextView txtName = view.findViewById(R.id.device_name_txt);
            txtName.setText(deviceItem.getName());
            final TextView txtFunc = view.findViewById(R.id.device_value_txt);
            final ImageButton btnOnOff = view.findViewById(R.id.device_onoff_btn);
            System.out.println("전원 : " + deviceItem.isOnoff());
            btnOnOff.setSelected(deviceItem.isOnoff());


            btnOnOff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //버튼 on off
                    Device result = clickEvent.onClickItem(deviceItem);
                    deviceItem.copy(result);
                    txtFunc.setText(deviceItem.getMode());
                    System.out.println("전원 : " + deviceItem.isOnoff());
                    btnOnOff.setSelected(deviceItem.isOnoff());
                }
            });

            //디바이스 기능에 따라 버튼 추가
            try{
                switch (deviceItem.getDeviceKind()){
                    case "공기청정기":
                        if (deviceItem.getState()==0) {
                            txtFunc.setText("켜짐");
                        }
                        else if (deviceItem.getState()==1) {
                            txtFunc.setText("인공지능모드");
                        }
                        else if (deviceItem.getState()==2) {
                            txtFunc.setText("꺼짐");
                        }
                        break;
                    case "도어락":
                        txtFunc.setText("닫힘");
                        if(deviceItem.getState()==1){
                            txtFunc.setText("작동버튼");
                        }
                        break;
                    case "스마트창문":
                        if (deviceItem.getState()==0) {
                            txtFunc.setText("닫힘");
                        }
                        else if (deviceItem.getState()==1) {
                            txtFunc.setText("열림");
                        }else if (deviceItem.getState()==2){
                            txtFunc.setText("스마트모드");
                        }
                        break;
                        default:
                            if (deviceItem.getState()==1) {
                                txtFunc.setText("켜짐");
                            }
                            else if (deviceItem.getState()==2) {
                                txtFunc.setText("꺼짐");
                            }else{
                                txtFunc.setText("꺼짐");
                            }
                            break;
                }
            }catch (NullPointerException e){ System.out.println("DeviceGridViewAdapter:: Device:func NULL"); }

        }
        return view;
    }

    public void updateDevice(Device device){

    }

    public void addItem(Device item) {
        deviceArrayList.add(item);
    }
}
