package com.example.project_smart_home.ui.room;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_smart_home.R;
import com.example.project_smart_home.object.Room;

import java.util.ArrayList;
import java.util.Collections;

// RoomListActivity의 RecyclerView 어뎁터
// 방 목록을 리스트 형식으로 표현
public class RoomListRecyclerAdapter extends RecyclerView.Adapter<RoomListRecyclerAdapter.RoomViewHolder>
        implements RoomItemTouchHelperCallback.OnItemMoveListener{
    ArrayList<Room> roomArrayList = new ArrayList<Room>();         // 방 목록
    private OnRoomListListener listListener;                             // 방을 클릭했을 때 발생하는 이벤트(RoomListActivity에서 구현)
    private OnStartDragListener startDragListener;              // 드래그 기능

    public interface OnStartDragListener{
        void onStartDrag(RoomViewHolder holder);
    }

    public RoomListRecyclerAdapter(OnRoomListListener listListener, OnStartDragListener listener) {
        this.listListener = listListener;
        this.startDragListener = listener;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_list_item, parent, false);
        return new RoomViewHolder(view, listListener);
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
                listListener.onClickRoom(position);
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
        listListener.changeRoomItem(fromPosition, toPosition);
        return false;
    }

    class RoomViewHolder extends RecyclerView.ViewHolder{
        private OnRoomListListener clickEvent;
        private TextView txtRoomName;
        private ImageButton dragHandler;
        private View view;

        public RoomViewHolder(@NonNull View itemView, OnRoomListListener onClick) {
            super(itemView);
            this.clickEvent = onClick;
            this.view = itemView;

            txtRoomName = itemView.findViewById(R.id.room_name);
            dragHandler = itemView.findViewById(R.id.draghandler);
        }

        void onBind(Room room){ txtRoomName.setText(room.getRoom()); }
    }

}
