package com.example.project_smart_home.ui.room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.project_smart_home.MainActivity;
import com.example.project_smart_home.R;
import com.example.project_smart_home.object.Room;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.project_smart_home.utils.Constants.EXTRA_MESSAGE_ROOM_LIST;

// 방 목록 액티비티
public class RoomListActivity extends AppCompatActivity implements RoomListRecyclerAdapter.OnStartDragListener, OnRoomListListener {
    RoomListRecyclerAdapter adapter;        // 방 리스클러뷰 어뎁터
    ItemTouchHelper itemTouchHelper;        // 드래그 기능

    ArrayList<Room> rooms;                  // 방 목록
    int[] roomsSeq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        rooms = (ArrayList<Room>) getIntent().getSerializableExtra(EXTRA_MESSAGE_ROOM_LIST);
        roomsSeq = new int[rooms.size()];
        for (int i = 0; i < rooms.size(); i++)
            roomsSeq[i] = i;

        Toolbar toolbar = findViewById(R.id.room_list_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setRoomList();

        Button btnSaveList = findViewById(R.id.save_room_list_btn);
        btnSaveList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.getMainController().updateRoomList(roomsSeq);
                finish();
            }
        });

    }

    private void setRoomList(){
        adapter = new RoomListRecyclerAdapter(this, this);
        RecyclerView roomList = findViewById(R.id.room_list_recycler);
        roomList.setLayoutManager(new LinearLayoutManager(this));

        RoomItemTouchHelperCallback callback = new RoomItemTouchHelperCallback(adapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(roomList);

        roomList.setAdapter(adapter);
        for(int i = 0; i < rooms.size(); i++) adapter.addItem(rooms.get(i));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent getStartIntent(Context context, ArrayList<Room> roomList){
        Intent intent = new Intent(context, RoomListActivity.class);
        intent.putExtra(EXTRA_MESSAGE_ROOM_LIST, roomList);
        return intent;
    }


    @Override
    public void onStartDrag(RoomListRecyclerAdapter.RoomViewHolder holder) {
        itemTouchHelper.startDrag(holder);
    }

    // 방을 클릭했을 경우 RoomActivity로 전환
    @Override
    public void onClickRoom(int position) {
        Intent intent = RoomActivity.getStartIntent(this, rooms.get(position));
        startActivity(intent);
        finish();
    }

    @Override
    public void changeRoomItem(int fromPosition, int toPosition) {
        Collections.swap(rooms, fromPosition, toPosition);
        int t = roomsSeq[fromPosition];
        roomsSeq[fromPosition] = roomsSeq[toPosition];
        roomsSeq[toPosition] = t;
        System.out.println(rooms.get(0).getRoom());
        System.out.println(rooms.get(1).getRoom());
        System.out.println(rooms.get(2).getRoom());
    }
}
