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

import static com.example.project_smart_home.utils.Constants.FUNCTION_KEY_TEMP;

public class DeviceGridViewAdapter extends BaseAdapter {
    LayoutInflater inf;
    ArrayList<Device> deviceArrayList = new ArrayList<Device>();
    String roomname;
    OnClickItem clickEvent;
    ImageButton btnOnOff;
    Button btnFunc;

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
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = DeviceControllerActivity.getStartIntent(inf.getContext(), deviceItem, roomname);
                    inf.getContext().startActivity(intent);
                }
            });

            TextView txtName = view.findViewById(R.id.device_name_txt);
            txtName.setText(deviceItem.getName());

            btnOnOff = view.findViewById(R.id.device_onoff_btn);

            btnOnOff.setSelected(deviceItem.isOnoff());
            btnOnOff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //버튼 on off
                    if(btnOnOff.isSelected()){

                    }else{

                    }
                }
            });

            TextView txtFunc = view.findViewById(R.id.device_value_txt);

            //디바이스 기능에 따라 버튼 추가
            try{
                switch (deviceItem.getFunc()){
                    case FUNCTION_KEY_TEMP:
                        txtFunc.setText("설정온도 " + deviceItem.getValue(FUNCTION_KEY_TEMP) + "℃");
                        break;
                        default:
                            txtFunc.setText("꺼짐");
                            if (deviceItem.isOnoff())
                                txtFunc.setText("켜짐");
                            break;
                }
            }catch (NullPointerException e){ System.out.println("DeviceGridViewAdapter:: Device:func NULL"); }

        }
        return view;
    }

    public void addItem(Device item) {
        deviceArrayList.add(item);
    }
}
