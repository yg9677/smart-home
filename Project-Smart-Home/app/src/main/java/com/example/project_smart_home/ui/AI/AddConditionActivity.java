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

import com.example.project_smart_home.MainActivity;
import com.example.project_smart_home.R;
import com.example.project_smart_home.Task.AISetTask;
import com.example.project_smart_home.object.AISet;
import com.example.project_smart_home.object.DataCondition;
import com.example.project_smart_home.object.DateCondition;
import com.example.project_smart_home.object.Device;
import com.example.project_smart_home.object.DeviceWorking;
import com.example.project_smart_home.object.Room;
import com.example.project_smart_home.order.AIsetOrder;
import com.example.project_smart_home.refine.DateRefine;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.example.project_smart_home.utils.Constants.AI_CONDITION_KEY_DATA;
import static com.example.project_smart_home.utils.Constants.AI_CONDITION_KEY_DATE;
import static com.example.project_smart_home.utils.Constants.APP_TEST;
import static com.example.project_smart_home.utils.Constants.CONNECTED_MODE_SERVER;
import static com.example.project_smart_home.utils.Constants.EXTRA_MESSAGE_ROOM_LIST;
import static com.example.project_smart_home.utils.Constants.PATH_CODE_AISET;
import static com.example.project_smart_home.utils.Constants.TOKEN_AISET;
import static com.example.project_smart_home.utils.Constants.TYPE_OF_SEND;

// 조건 추가 액티비티
public class AddConditionActivity extends AppCompatActivity implements OnFragmentInteractionListener{
    ArrayList<Room> roomArrayList = new ArrayList<Room>();
    Room selectedRoom;
    AISet aiSet;

    Toolbar toolbar;
    CollapsingToolbarLayout toolbarLayout;

    boolean activityBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_condition);

        activityBack = true;

        aiSet = new AISet();
        roomArrayList = (ArrayList<Room>) getIntent().getSerializableExtra(EXTRA_MESSAGE_ROOM_LIST);

        toolbar = findViewById(R.id.add_condition_toolbar);
        toolbarLayout = findViewById(R.id.add_condition_toolbar_layout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("조건 추가");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        replaceFragment(SelectRoomFragment.newInstance(roomArrayList, ""));
    }

    public void replaceFragment(Fragment frag){
        activityBack = false;
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_frame, frag).addToBackStack(null).commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if (activityBack)
                    finish();
                else
                    super.onBackPressed();
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
        activityBack = true;
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
        toolbarLayout.setTitle("동작 추가");
        replaceFragment(DeviceSelectFragment.newInstance(room, ""));
    }

    @Override
    public void addDeviceWorking(DeviceWorking dw) {
        toolbarLayout.setTitle("조건 추가");
        aiSet.addWorking(dw);
        replaceFragment(AddConditionFragment.newInstance(selectedRoom, aiSet));
        activityBack = true;
    }

    @Override
    public void addDataCondition(DataCondition data) {
        aiSet.addDataCondition(data);
        replaceFragment(AddConditionFragment.newInstance(selectedRoom, aiSet));
        activityBack = true;
    }

    @Override
    public void setDateCondition(DateCondition date) {
        aiSet.setDateCondition(date);
        replaceFragment(AddConditionFragment.newInstance(selectedRoom, aiSet));
        activityBack = true;
    }

    @Override
    public void addAI_Interaction(AISet set) {
        AIsetOrder ao = new AIsetOrder(CONNECTED_MODE_SERVER, TYPE_OF_SEND, "send_aiset", aiSet);

        if (!APP_TEST)
            MainActivity.getMainController().request(ao);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        ArrayList<AISet> aiSets = new ArrayList<>();
        Type type = new TypeToken<ArrayList<AISet>>() {}.getType();

        Gson gson = new GsonBuilder().create();
        String straisetlist = sharedPreferences.getString(TOKEN_AISET, "");

        if (!straisetlist.equals("")) {
            aiSets = gson.fromJson(straisetlist, type);
            int newId = 0;
            for (AISet a : aiSets){
                if (newId < a.getAiSetID())
                    newId = a.getAiSetID();
            }
            aiSet.setAiSetID(newId + 1);
        }
        else {
            aiSet.setAiSetID(1);
        }

        aiSets.add(aiSet);

        straisetlist = gson.toJson(aiSets, type);
        editor.putString(TOKEN_AISET, straisetlist);
        editor.commit();

        System.out.println("저장 : " + straisetlist);

        startActivity(UserSettingAIActivity.getStartIntent(this, roomArrayList));
        finish();
    }

    @Override
    public void onSelectDeviceInteraction(Device device) {
        replaceFragment(DeviceWorkingFragment.newInstance(device,""));
    }
}
