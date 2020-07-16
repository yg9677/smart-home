package com.example.project_smart_home.ui.room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.project_smart_home.R;
import com.example.project_smart_home.ui.member.MemberActivity;

public class RoomListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
    }

    public static Intent getStartIntent(Context context){
        Intent intent = new Intent(context, RoomListActivity.class);
        return intent;
    }
}
