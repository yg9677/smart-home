package com.example.project_smart_home.ui.AI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import com.example.project_smart_home.R;
import com.example.project_smart_home.object.AISet;
import com.example.project_smart_home.object.DataCondition;
import com.example.project_smart_home.object.DateCondition;
import com.example.project_smart_home.object.Device;
import com.example.project_smart_home.object.DeviceWorking;
import com.example.project_smart_home.object.Room;

import java.util.ArrayList;

import static com.example.project_smart_home.utils.Constants.AI_CONDITION_KEY_DATA;
import static com.example.project_smart_home.utils.Constants.AI_CONDITION_KEY_DATE;
import static com.example.project_smart_home.utils.Constants.EXTRA_MESSAGE_ROOM_LIST;

// 조건 추가 액티비티
public class AddConditionActivity extends AppCompatActivity implements OnFragmentInteractionListener{
    ArrayList<Room> roomArrayList = new ArrayList<Room>();
    Room selectedRoom;
    AISet aiSet;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_condition);

        aiSet = new AISet();
        roomArrayList = (ArrayList<Room>) getIntent().getSerializableExtra(EXTRA_MESSAGE_ROOM_LIST);

        toolbar = findViewById(R.id.add_condition_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        replaceFragment(SelectRoomFragment.newInstance(roomArrayList, ""));
    }

    public void replaceFragment(Fragment frag){
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_frame, frag).addToBackStack(null).commit();
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
        Intent intent = new Intent(context, AddConditionActivity.class);
        intent.putExtra(EXTRA_MESSAGE_ROOM_LIST, roomList);
        return intent;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onSelectRoomInteraction(Room room) {
        selectedRoom = room;
        aiSet.setRoomname(selectedRoom.getRoom());
        replaceFragment(AddConditionFragment.newInstance(room, aiSet));
    }

    @Override
    public void onSelectConditionInteraction(int item) {
        if (item == AI_CONDITION_KEY_DATA){
            replaceFragment(DataConditionFragment.newInstance("",""));
        }
        if (item == AI_CONDITION_KEY_DATE){
            replaceFragment(DateConditionFragment.newInstance("",""));
        }
    }

    @Override
    public void addWorkingInteraction(Room room) {
        toolbar.setTitle("동작 추가");
        replaceFragment(DeviceSelectFragment.newInstance(room, ""));
    }

    @Override
    public void addDeviceWorking(DeviceWorking dw) {
        aiSet.addWorking(dw);
        replaceFragment(AddConditionFragment.newInstance(selectedRoom, aiSet));
    }

    @Override
    public void addDataCondition(DataCondition data) {
        aiSet.addDataCondition(data);
        replaceFragment(AddConditionFragment.newInstance(selectedRoom, aiSet));
    }

    @Override
    public void setDateCondition(DateCondition date) {
        aiSet.setDateCondition(date);
        replaceFragment(AddConditionFragment.newInstance(selectedRoom, aiSet));
    }

    @Override
    public void addAI_Interaction(AISet set) {
        this.aiSet = set;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();



        startActivity(UserSettingAIActivity.getStartIntent(this, roomArrayList));
    }

    @Override
    public void onSelectDeviceInteraction(Device device) {
        replaceFragment(DeviceWorkingFragment.newInstance(device,""));
    }


}
