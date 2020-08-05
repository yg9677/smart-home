package com.example.project_smart_home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_smart_home.R;
import com.example.project_smart_home.object.Room;

import java.util.ArrayList;

public class RoomRecyclerAdapter extends RecyclerView.Adapter<RoomRecyclerAdapter.ItemViewHolder> {
    ArrayList<Room> roomArrayList = new ArrayList<Room>();
    private OnClickItem clickEvent;

    public RoomRecyclerAdapter(OnClickItem onClick) {
        this.clickEvent = onClick;
    }

    @NonNull
    @Override
    public RoomRecyclerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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

    public void addItem(Room room){
        roomArrayList.add(room);
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

            this.clickEvent = onClick;
            this.context = context;

            dh = (int)context.getResources().getDimension(R.dimen.device_item_height);

            btnRoom = itemView.findViewById(R.id.room_title_btn);
            deviceGrid = itemView.findViewById(R.id.device_grid);
            emptyDevice = itemView.findViewById(R.id.empty_device);
        }

        void onBind(Room room){
            btnRoom.setText(room.getRoomname());
            btnRoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            if(room.getDeviceCount() == 0){
                deviceGrid.setVisibility(View.GONE);
            }else {
                emptyDevice.setVisibility(View.GONE);
                deviceGrid.setLayoutParams(modifyHeight(deviceGrid, room));
                DeviceGridViewAdapter dvGridAdapter = new DeviceGridViewAdapter(clickEvent, context, room.getRoomname());
                deviceGrid.setAdapter(dvGridAdapter);
                System.out.println("devicelist size :: " + room.getDeviceCount());
                for(int i = 0; i < room.getDeviceCount(); i++) {
                    dvGridAdapter.addItem(room.getDevice(i));
                    System.out.println("Device  num :: " + i);
                }
            }

        }

        private ViewGroup.LayoutParams modifyHeight(View view, Room room){
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params == null){
                params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            params.height += dh*(room.getDeviceCount()/2);
            return params;
        }
    }
}
