package com.example.project_smart_home.ui.message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project_smart_home.R;
import com.example.project_smart_home.object.NoticeMessage;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {
    ArrayList<NoticeMessage> messageList = new ArrayList<NoticeMessage>();
    ListView lvMassage;
    TextView txtEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Toolbar toolbar = findViewById(R.id.message_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setComponent();
    }

    private void setComponent(){
        txtEmpty = findViewById(R.id.empty_message_txt);
        if (!messageList.isEmpty()) {
            txtEmpty.setVisibility(View.GONE);
            lvMassage = findViewById(R.id.message_list);
            setList();
        }
    }

    private void setList(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.message, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_message_delete:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent getStartIntent(Context context){
        Intent intent = new Intent(context, MessageActivity.class);
        return intent;
    }
}
