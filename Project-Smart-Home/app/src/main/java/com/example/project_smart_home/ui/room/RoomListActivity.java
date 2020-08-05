package com.example.project_smart_home.ui.room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.project_smart_home.R;
import com.example.project_smart_home.adapter.OnClickItem;
import com.example.project_smart_home.adapter.RoomRecyclerAdapter;
import com.example.project_smart_home.object.Room;
import com.example.project_smart_home.ui.member.MemberActivity;

import java.util.ArrayList;

import static com.example.project_smart_home.utils.Constants.EXTRA_MESSAGE_ROOM_LIST;

public class RoomListActivity extends AppCompatActivity implements RoomListRecyclerAdapter.OnStartDragListener, OnClickRoom {
    RoomListRecyclerAdapter adapter;
    ItemTouchHelper itemTouchHelper;

    ArrayList<Room> rooms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        rooms = (ArrayList<Room>) getIntent().getSerializableExtra(EXTRA_MESSAGE_ROOM_LIST);

        Toolbar toolbar = findViewById(R.id.room_list_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setRoomList();
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

    @Override
    public void onClickRoom(int position) {
        Intent intent = RoomActivity.getStartIntent(this, rooms.get(position));
        startActivity(intent);
    }
}
