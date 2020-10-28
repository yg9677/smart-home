package com.example.project_smart_home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.project_smart_home.adapter.OnClickItem;
import com.example.project_smart_home.adapter.RoomRecyclerAdapter;
import com.example.project_smart_home.bluetooth.BluetoothConnector;
import com.example.project_smart_home.object.AISet;
import com.example.project_smart_home.object.AirCleaner;
import com.example.project_smart_home.object.Device;
import com.example.project_smart_home.object.DoorLock;
import com.example.project_smart_home.object.MeasuredData;
import com.example.project_smart_home.object.MoodLight;
import com.example.project_smart_home.object.Room;
import com.example.project_smart_home.object.Window;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.project_smart_home.utils.Constants.AIRCLEANER;
import static com.example.project_smart_home.utils.Constants.APP_TEST;
import static com.example.project_smart_home.utils.Constants.CONNECTED_MODE_SERVER;
import static com.example.project_smart_home.utils.Constants.DOORLOCK;
import static com.example.project_smart_home.utils.Constants.MOODLIGHT;
import static com.example.project_smart_home.utils.Constants.TOKEN_AISET;
import static com.example.project_smart_home.utils.Constants.TYPE_OF_DEVICE;
import static com.example.project_smart_home.utils.Constants.TYPE_OF_RECEIVE;
import static com.example.project_smart_home.utils.Constants.USER_ID;
import static com.example.project_smart_home.utils.Constants.WINDOW;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener, OnClickItem, BluetoothConnector.OnBluetoothConnectorListener,
        OnThreadListener {
    Gson gson;

    private Button nav_room_btn, nav_member_btn, nav_ai_btn, nav_key_btn;
    private ImageButton option_btn;

    SharedPreferences mSharedPreferences;                       // 앱 설정 값들, 액티비티간 공유 정보

    NavigationView mNavigationView;
    DrawerLayout mDrawer;

    RecyclerView roomList;
    RoomRecyclerAdapter rListAdapter;

    AIManager aiManager;                    // AI 조건을 1분마다 확인하는 Thread
    Measurement measurement;                // 측정 데이터를 1분마다 받아오는 Thread

    public final static String myIp="192.168.219.106";
    String userId;

    ArrayList<Room> room = new ArrayList<Room>();
    ArrayList<Device> deviceArrayList = new ArrayList<>();
    ArrayList<AISet> aiSets = new ArrayList<>();

    MasterController masterController = new MasterController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        userId = mSharedPreferences.getString(USER_ID, "");         // 저장되어 있는 userId 불러오기

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

        if(!APP_TEST){         // 앱 테스트 아닐 경우만
            measurement = new Measurement(room.size(), this);
            measurement.start();

            startAiManager();
        }
    }

    // SharedPreferences에 저장되어 있는 AI 조건들을 가져와 AIManager를 통해 실행
    private void startAiManager(){
        gson = new GsonBuilder().create();
        String straisetlist = mSharedPreferences.getString(TOKEN_AISET, "");
        if (!straisetlist.equals("")){
            Type type = new TypeToken<ArrayList<AISet>>(){}.getType();
            aiSets = gson.fromJson(straisetlist, type);

            aiManager = new AIManager(aiSets, this);
            aiManager.start();
        }
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

        if(APP_TEST)    // 테스트 실행
            setTestRoom();
        else
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
    public Device onClickItem(Device deviceItem) {
        Order deviceOrder = new DeviceOrder(TYPE_OF_DEVICE, "device_onoff", deviceItem);
        DeviceOrder returnOrder = (DeviceOrder)masterController.communicate(deviceOrder);
        return returnOrder.getDevice();
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

    // 테스트 샘플 데이터
    private void setTestRoom(){
        Room room1 = new Room("방1", 4, "거실", "주소1");
        Room room2 = new Room("방2", 4, "안방", "주소2");
        Room room3 = new Room("방3", 4, "부엌", "주소3");

        room1.addDevice(new MoodLight("무드등1", MOODLIGHT, "moodlight01", 2, room1.getRoom()));
        room1.addDevice(new MoodLight("무드등2", MOODLIGHT, "moodlight02", 2, room1.getRoom()));
        room1.addDevice(new Window("창문1", WINDOW, "window01", 2, room1.getRoom()));
        room1.addDevice(new DoorLock("도어락", DOORLOCK, "doorlock01", 2, room1.getRoom()));

        room2.addDevice(new MoodLight("무드등3", MOODLIGHT, "moodlight03", 2, room2.getRoom()));
        room2.addDevice(new Window("창문2", WINDOW, "window02", 2, room2.getRoom()));
        room2.addDevice(new AirCleaner("공기청정기", AIRCLEANER, "aircleaner01", 2, room2.getRoom()));

        room3.addDevice(new MoodLight("무드등4", MOODLIGHT, "moodlight04", 2, room3.getRoom()));
        room3.addDevice(new Window("창문3", WINDOW, "window03", 2, room3.getRoom()));

        MeasuredData md = new MeasuredData();
        md.setTimeHMS("");
        md.setNumber(1);
        md.setRegday("");
        md.setTimeYMD("");
        md.setRoom("방1");
        md.setTemperature(12);
        md.setHumidity(19);
        md.setDust(29);

        room1.setMeasuredData(md);
        room2.setMeasuredData(md);
        room3.setMeasuredData(md);

        room.add(room1);
        room.add(room2);
        room.add(room3);

        for(int i=0; i<room.size(); i++) { //방 저장
            System.out.println("device 갯수 : " + room.get(i).getDevicelist().size());
            rListAdapter.addRoom(room.get(i));
        }
    }
}