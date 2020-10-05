package com.example.project_smart_home.ui.AI;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_smart_home.R;

import java.util.ArrayList;

public class SelectRoomListAdapter extends RecyclerView.Adapter<SelectRoomListAdapter.ListViewHolder> {
    private ArrayList<String> roomList = new ArrayList<String>();
    OnSelectRoom event;

    public interface OnSelectRoom {
        void onSelectRoom(String roomname);
    }

    public SelectRoomListAdapter(OnSelectRoom onSelectRoom){ this.event = onSelectRoom; }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_list_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        holder.onBind(roomList.get(position));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                event.onSelectRoom(roomList.get(position));
            }
        });
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public void addItem(String roomname){
        roomList.add(roomname);
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
