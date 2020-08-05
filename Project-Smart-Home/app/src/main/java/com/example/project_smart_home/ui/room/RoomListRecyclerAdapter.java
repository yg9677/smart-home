package com.example.project_smart_home.ui.room;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_smart_home.R;
import com.example.project_smart_home.adapter.DeviceGridViewAdapter;
import com.example.project_smart_home.adapter.OnClickItem;
import com.example.project_smart_home.object.Room;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RoomListRecyclerAdapter extends RecyclerView.Adapter<RoomListRecyclerAdapter.RoomViewHolder>
        implements RoomItemTouchHelperCallback.OnItemMoveListener{
    ArrayList<Room> roomArrayList = new ArrayList<Room>();
    private OnClickRoom clickEvent;
    private OnStartDragListener startDragListener;

    public interface OnStartDragListener{
        void onStartDrag(RoomViewHolder holder);
    }

    public RoomListRecyclerAdapter(OnClickRoom onClick, OnStartDragListener listener) {
        this.clickEvent = onClick;
        this.startDragListener = listener;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_list_item, parent, false);
        return new RoomViewHolder(view, clickEvent);
    }

    @Override
    public void onBindViewHolder(@NonNull final RoomViewHolder holder, final int position) {
        holder.onBind(roomArrayList.get(position));
        holder.dragHandler.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN){
                    startDragListener.onStartDrag(holder);
                }
                return false;
            }
        });
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickEvent.onClickRoom(position);
            }
        });
    }

    @Override
    public int getItemCount() {return roomArrayList.size(); }

    public void addItem(Room room){ roomArrayList.add(room); }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(roomArrayList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    class RoomViewHolder extends RecyclerView.ViewHolder{
        private OnClickRoom clickEvent;
        private TextView txtRoomName;
        private ImageButton dragHandler;
        private View view;

        int dh;             // 디바이스 아이템 높이 - device_item_height

        public RoomViewHolder(@NonNull View itemView, OnClickRoom onClick) {
            super(itemView);
            this.clickEvent = onClick;
            this.view = itemView;

            txtRoomName = itemView.findViewById(R.id.room_name);
            dragHandler = itemView.findViewById(R.id.draghandler);
        }

        void onBind(Room room){ txtRoomName.setText(room.getRoomname()); }
    }

}
