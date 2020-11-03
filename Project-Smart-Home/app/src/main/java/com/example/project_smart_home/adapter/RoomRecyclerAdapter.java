package com.example.project_smart_home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_smart_home.R;
import com.example.project_smart_home.object.Device;
import com.example.project_smart_home.object.Room;

import java.util.ArrayList;
import java.util.Collections;

public class RoomRecyclerAdapter extends RecyclerView.Adapter<RoomRecyclerAdapter.ItemViewHolder> {
    ArrayList<Room> roomArrayList = new ArrayList<Room>();
    int deviceNum = 0;

    private OnClickItem clickEvent;

    public RoomRecyclerAdapter(OnClickItem onClick) {
        this.clickEvent = onClick;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_item, parent, false);
        return new ItemViewHolder(view, clickEvent, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.onBind(roomArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return roomArrayList.size();
    }

    public void addRoom(Room room){
        roomArrayList.add(room);
    }

    public void update(int[] sort){
        for(int i= 0; i <roomArrayList.size(); i++)
            roomArrayList.get(i).setSeqNum(sort[i]);
        Collections.sort(roomArrayList);
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        private Context context;
        private OnClickItem clickEvent;
        private Button btnRoom;
        private GridView deviceGrid;
        private LinearLayout emptyDevice;

        int dh;             // 디바이스 아이템 높이 - device_item_height

        public ItemViewHolder(@NonNull View itemView, OnClickItem onClick, Context context) {
            super(itemView);
            dh = 10;
            this.clickEvent = onClick;
            this.context = context;

            dh += (int)context.getResources().getDimension(R.dimen.device_item_height);

            System.out.println("dh : " + dh);

            btnRoom = itemView.findViewById(R.id.room_title_btn);
            deviceGrid = itemView.findViewById(R.id.device_grid);
            emptyDevice = itemView.findViewById(R.id.empty_device);
        }

        void onBind(final Room room){
            btnRoom.setText(room.getRoom());

            btnRoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickEvent.onClickRoom(room);
                }
            });

            if(room.getSize() == 0){
                deviceGrid.setVisibility(View.GONE);
            }else {
                ArrayList<Device> deviceArrayList = room.getDevicelist();
                deviceNum = deviceArrayList.size();

                emptyDevice.setVisibility(View.GONE);
                deviceGrid.setLayoutParams(modifyHeight(deviceGrid));
                DeviceGridViewAdapter dvGridAdapter = new DeviceGridViewAdapter(clickEvent, context, room.getRoom());
                deviceGrid.setAdapter(dvGridAdapter);

                System.out.println("dh size : " + dh);
                System.out.println("devicelist size :: " + deviceArrayList.size());
                for(int i = 0; i < deviceArrayList.size(); i++) {
                    if(deviceArrayList.get(i).getRoom().equals(room.getRoom())) { //디바이스와 해당 방의 이름이 똑같을 시
                        dvGridAdapter.addItem(deviceArrayList.get(i));
                        System.out.println("Device  num :: " + i);
                    }
                }
            }

        }

        private ViewGroup.LayoutParams modifyHeight(View view){
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params == null){
                params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            params.height = dh*(deviceNum/2 + deviceNum%2);
            return params;
        }
    }
}
