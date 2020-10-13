package com.example.project_smart_home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.project_smart_home.adapter.OnClickItem;
import com.example.project_smart_home.adapter.RoomRecyclerAdapter;
import com.example.project_smart_home.bluetooth.BluetoothConnector;
import com.example.project_smart_home.object.AISet;
import com.example.project_smart_home.object.Device;
import com.example.project_smart_home.object.MeasuredData;
import com.example.project_smart_home.object.Room;
import com.example.project_smart_home.order.DeviceOrder;
import com.example.project_smart_home.order.Order;
import com.example.project_smart_home.order.RoomListOrder;
import com.example.project_smart_home.thread.AIManager;
import com.example.project_smart_home.thread.Measurement;
import com.example.project_smart_home.thread.OnThreadListener;
import com.example.project_smart_home.ui.AI.AIActivity;
import com.example.project_smart_home.ui.manual.ManualActivity;
import com.example.project_smart_home.ui.member.MemberActivity;
import com.example.project_smart_home.ui.message.MessageActivity;
import com.example.project_smart_home.ui.room.RoomListActivity;
import com.example.project_smart_home.ui.Enrollment.KeyActivity;

import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import static com.example.project_smart_home.utils.Constants.CONNECTED_MODE_SERVER;
import static com.example.project_smart_home.utils.Constants.TYPE_OF_DEVICE;
import static com.example.project_smart_home.utils.Constants.TYPE_OF_RECEIVE;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener, OnClickItem, BluetoothConnector.OnBluetoothConnectorListener,
        OnThreadListener {
    private Button nav_room_btn, nav_member_btn, nav_ai_btn, nav_key_btn;
    private ImageButton option_btn;

    SharedPreferences mSharedPreferences;                       // 앱 설정 값들, 액티비티간 공유 정보

    NavigationView mNavigationView;
    DrawerLayout mDrawer;

    RecyclerView roomList;
    RoomRecyclerAdapter rListAdapter;

    public final static String myIp="192.168.219.106";
    ArrayList<Room> room = new ArrayList<Room>();
    ArrayList<Device> deviceArrayList = new ArrayList<>();
    ArrayList<AISet> aiSets = new ArrayList<>();

    MasterController masterController = new MasterController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

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

        Measurement measurement = new Measurement(room.size(), this);
        measurement.start();

        AIManager aiManager = new AIManager(aiSets, this);
        aiManager.start();

        btnSetting();
        roomSetting();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        switch (requestCode) {
//            case BT_REQUEST_ENABLE:
//                if (resultCode == RESULT_OK) { // 블루투스 활성화를 확인을 클릭하였다면
//                    Toast.makeText(getApplicationContext(), "블루투스 활성화", Toast.LENGTH_LONG).show();
//                } else if (resultCode == RESULT_CANCELED) { // 블루투스 활성화를 취소를 클릭하였다면
//                    Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_LONG).show();
//                }
//                break;
//        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        getMenuInflater().inflate(R.menu.bluetooth, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.menu.bluetooth:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent getStartIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    public void onClick(View view) { // 사이드 바
        Intent next = null;
        switch (view.getId()){
            case R.id.nav_room_btn: //방의 대한 센서 데이터들
                next = RoomListActivity.getStartIntent(this, room);
                break;
            case R.id.nav_member_btn:
                next = MemberActivity.getStartIntent(this);
                break;
            case R.id.nav_ai_btn:
                next = AIActivity.getStartIntent(this, room);
                break;
            case R.id.option_btn:
                next = SettingsActivity.getStartIntent(this);
                break;
            case R.id.nav_key_btn:
                next = KeyActivity.getStartIntent(this);
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
        nav_key_btn = hView.findViewById(R.id.nav_key_btn);
        nav_key_btn.setOnClickListener(this);
    }

    private void roomSetting(){ //방 세팅
        roomList = findViewById(R.id.room_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        roomList.setLayoutManager(linearLayoutManager);

        rListAdapter = new RoomRecyclerAdapter(this);
        roomList.setAdapter(rListAdapter);

        setRoomList();
    }

    private void setRoomList(){
        Order requestRoomList = new Order(CONNECTED_MODE_SERVER, TYPE_OF_RECEIVE, "get_roomlist");
        RoomListOrder resultOrder = (RoomListOrder) masterController.communicate(requestRoomList);

        room = resultOrder.getRoomArrayList();
        for(int i=0; i<room.size(); i++) { //방 저장
            rListAdapter.addRoom(room.get(i));
        }
    }

    @Override
    public void onClickItem(Device deviceItem) {
        Order deviceOrder = new DeviceOrder(TYPE_OF_DEVICE, "device_onoff", deviceItem);
        masterController.communicate(deviceOrder);
    }

    @Override
    public void startActivityForResultInConnector(Intent it, int constant) {
        startActivityForResult(it, constant);
    }


    @Override
    public void onUpdateMeasuredData(MeasuredData md, int i) {
        room.get(i).setMeasuredData(md);
    }

    @Override
    public void onWork(String name) {
        Order deviceOrder = new DeviceOrder(TYPE_OF_DEVICE, "device_on", findDeviceByName(name));
        masterController.communicate(deviceOrder);
    }

    @Override
    public void offWork(String name) {
        Order deviceOrder = new DeviceOrder(TYPE_OF_DEVICE, "device_off", findDeviceByName(name));
        masterController.communicate(deviceOrder);
    }

    @Override
    public MeasuredData getMeasuredData(String roomname) {
        for(int i = 0; i < room.size(); i++)
            if (room.get(i).getRoom().equals(roomname))
                return room.get(i).getMeasuredData();
        return null;
    }

    private Device findDeviceByName(String name){
        Device result = null;
        for(int i = 0; i < room.size(); i++){
            if ((result = room.get(i).findDevice(name)) != null)
                break;
        }
        return result;
    }
}