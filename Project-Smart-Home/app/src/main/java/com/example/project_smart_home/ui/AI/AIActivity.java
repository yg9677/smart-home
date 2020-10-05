package com.example.project_smart_home.ui.AI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;

import com.example.project_smart_home.MainActivity;
import com.example.project_smart_home.R;
import com.example.project_smart_home.object.Room;

import java.util.ArrayList;

import static com.example.project_smart_home.utils.Constants.EXTRA_MESSAGE_ROOM_LIST;

// 인공지능 액티비티
public class AIActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Room> roomArrayList = new ArrayList<Room>();
    ConstraintLayout[] menus = new ConstraintLayout[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai);
        setTitle("인공지능");
        Toolbar toolbar = findViewById(R.id.ai_toolbar) ;
        setSupportActionBar(toolbar);

        roomArrayList = (ArrayList<Room>) getIntent().getSerializableExtra(EXTRA_MESSAGE_ROOM_LIST);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setComponent();
    }

    void setComponent(){
        menus[0] = findViewById(R.id.ai_item2);
        menus[0].setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            startActivity(MainActivity.getStartIntent(this));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent getStartIntent(Context context, ArrayList<Room> roomList){
        Intent intent = new Intent(context, AIActivity.class);
        intent.putExtra(EXTRA_MESSAGE_ROOM_LIST, roomList);
        return intent;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == menus[0].getId()){
            startActivity(UserSettingAIActivity.getStartIntent(this, roomArrayList));
        }
    }
}
