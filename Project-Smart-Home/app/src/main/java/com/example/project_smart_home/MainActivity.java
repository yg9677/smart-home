package com.example.project_smart_home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.project_smart_home.adapter.OnClickItem;
import com.example.project_smart_home.adapter.RoomRecyclerAdapter;
import com.example.project_smart_home.object.Device;
import com.example.project_smart_home.object.Room;
import com.example.project_smart_home.ui.AI.AIActivity;
import com.example.project_smart_home.ui.manual.ManualActivity;
import com.example.project_smart_home.ui.member.MemberActivity;
import com.example.project_smart_home.ui.message.MessageActivity;
import com.example.project_smart_home.ui.room.RoomListActivity;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;

import com.example.project_smart_home.ui.setting.SettingsActivity;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

import static com.example.project_smart_home.utils.Constants.FUNCTION_KEY_TEMP;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener, OnClickItem {
    private Button nav_room_btn, nav_member_btn, nav_ai_btn;
    private ImageButton option_btn;

    NavigationView mNavigationView;
    DrawerLayout mDrawer;

    RecyclerView roomList;
    RoomRecyclerAdapter rListAdapter;


    //샘플 데이터
    ArrayList<Room> sample = new ArrayList<Room>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_24px);

        mDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                mDrawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        btnSetting();
        roomSetting();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public static Intent getStartIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    public void onClick(View view) {
        Intent next = null;
        switch (view.getId()){
            case R.id.nav_room_btn:
                next = RoomListActivity.getStartIntent(this, sample);
                break;
            case R.id.nav_member_btn:
                next = MemberActivity.getStartIntent(this);
                break;
            case R.id.nav_ai_btn:
                next = AIActivity.getStartIntent(this);
                break;
            case R.id.option_btn:
                next = SettingsActivity.getStartIntent(this);
                break;
        }
        if (next != null) startNextActivity(next);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent next = null;
        switch (menuItem.getItemId()){
            case R.id.nav_message:
                System.out.println(menuItem.getItemId());
                System.out.println("message");
                next = new Intent(this, MessageActivity.class);
                startNextActivity(next);
                break;
            case R.id.nav_description:
                next = new Intent(this, ManualActivity.class);
                startNextActivity(next);
                break;
                //url로 웹 공지사항으로 연결
            case R.id.nav_notification:
                break;
        }

        return false;
    }

    private void startNextActivity(Intent n){
        startActivity(n);
    }

    private void btnSetting(){
        View hView = mNavigationView.getHeaderView(0);
        nav_room_btn = hView.findViewById(R.id.nav_room_btn);
        nav_room_btn.setOnClickListener(this);
        nav_member_btn = hView.findViewById(R.id.nav_member_btn);
        nav_member_btn.setOnClickListener(this);
        nav_ai_btn = hView.findViewById(R.id.nav_ai_btn);
        nav_ai_btn.setOnClickListener(this);
        option_btn = hView.findViewById(R.id.option_btn);
        option_btn.setOnClickListener(this);
    }

    private void roomSetting(){
        roomList = findViewById(R.id.room_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        roomList.setLayoutManager(linearLayoutManager);

        rListAdapter = new RoomRecyclerAdapter(this);
        roomList.setAdapter(rListAdapter);
        setRoomList();
    }

    private void setRoomList(){
        //샘플 데이터
        Room r1 = new Room("거실");
        Room r2 = new Room("안방");
        Room r3 = new Room("부엌");

        Device d1 = new Device("전등1");
        Device d2 = new Device("전등2");
        Device d3 = new Device("에어컨");
        d3.setFunc(FUNCTION_KEY_TEMP);
        d3.addValue(FUNCTION_KEY_TEMP, "25");
        Device d4 = new Device("공기청정기");
        Device d5 = new Device("전등3");
        Device d6 = new Device("전등4");
        Device d7 = new Device("전등5");
        Device d8 = new Device("후드");

        r1.addDevice(d1);
        r1.addDevice(d2);
        r1.addDevice(d3);
        r1.addDevice(d4);
        r3.addDevice(d5);
        r3.addDevice(d6);
        r3.addDevice(d7);
        r3.addDevice(d8);
        sample.add(r1);
        sample.add(r2);
        sample.add(r3);

        rListAdapter.addItem(r1);
        rListAdapter.addItem(r2);
        rListAdapter.addItem(r3);
    }

    @Override
    public void onClickItem() {

    }
}
