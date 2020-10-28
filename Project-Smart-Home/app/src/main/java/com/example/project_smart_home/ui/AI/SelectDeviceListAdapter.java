package com.example.project_smart_home.ui.AI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_smart_home.R;
import com.example.project_smart_home.object.Device;

import java.util.ArrayList;

public class SelectDeviceListAdapter extends RecyclerView.Adapter<SelectDeviceListAdapter.ListViewHolder> {
    private ArrayList<String> deviceList = new ArrayList<String>();
    OnSelectDevice event;

    public interface OnSelectDevice {
        void onSelectDevice(String device);
    }

    public SelectDeviceListAdapter(OnSelectDevice onSelectDevice){ this.event = onSelectDevice; }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_list_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        holder.onBind(deviceList.get(position));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                event.onSelectDevice(deviceList.get(position));
            }
        });
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    public void addItem(String devicename){
        deviceList.add(devicename);
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public View view;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            name = itemView.findViewById(R.id.room_name);
        }

        public void onBind(String str){
            name.setText(str);
        }
    }
}
