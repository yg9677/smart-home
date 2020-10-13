package com.example.project_smart_home.ui.AI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.project_smart_home.R;
import com.example.project_smart_home.object.AISet;
import com.example.project_smart_home.object.Room;

import java.util.ArrayList;

import static com.example.project_smart_home.utils.Constants.EXTRA_MESSAGE_ROOM_LIST;

// 사용자 정의 인공지능 액티비티
public class UserSettingAIActivity extends AppCompatActivity {
    ArrayList<Room> roomArrayList = new ArrayList<Room>();

    AISetListAdapter aiSetAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting_ai);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        roomArrayList = (ArrayList<Room>) getIntent().getSerializableExtra(EXTRA_MESSAGE_ROOM_LIST);

        Toolbar toolbar = findViewById(R.id.user_setting_ai_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView listAISet = findViewById(R.id.user_setting_ai_recycler);
        listAISet.setLayoutManager(new LinearLayoutManager(this));
        aiSetAdapter = new AISetListAdapter();
        listAISet.setAdapter(aiSetAdapter);
        setList();
    }

    private void setList(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_setting_ai, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_add:
                // 인공지능 추가
                startActivity(AddConditionActivity.getStartIntent(this, roomArrayList));
                break;
            case R.id.action_delete:
                // 인공지능 삭제

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent getStartIntent(Context context, ArrayList<Room> roomList){
        Intent intent = new Intent(context, UserSettingAIActivity.class);
        intent.putExtra(EXTRA_MESSAGE_ROOM_LIST, roomList);
        return intent;
    }
}
