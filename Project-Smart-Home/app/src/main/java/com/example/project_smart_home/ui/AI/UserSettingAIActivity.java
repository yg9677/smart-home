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
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project_smart_home.R;
import com.example.project_smart_home.object.AISet;
import com.example.project_smart_home.object.Room;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.project_smart_home.utils.Constants.EXTRA_MESSAGE_ROOM_LIST;
import static com.example.project_smart_home.utils.Constants.TOKEN_AISET;

// 사용자 정의 인공지능 액티비티
public class UserSettingAIActivity extends AppCompatActivity implements AISetListAdapter.AISetListAdapterListener, View.OnClickListener {
    ArrayList<Room> roomArrayList = new ArrayList<Room>();
    ArrayList<AISet> aiSetArrayList = new ArrayList<>();

    LinearLayout deleteLayout;
    Button btnDelete, btnDeleteCancle;
    TextView txtEmpty;

    AISetListAdapter aiSetAdapter;
    SharedPreferences sharedPreferences;
    Type type;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting_ai);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        type = new TypeToken<ArrayList<AISet>>() {}.getType();

        gson = new GsonBuilder().create();
        String straisetlist = sharedPreferences.getString(TOKEN_AISET, "");
        System.out.println("불러오기 : " + straisetlist);
        if (!straisetlist.equals("")) {
            aiSetArrayList = gson.fromJson(straisetlist, type);
        }

        roomArrayList = (ArrayList<Room>) getIntent().getSerializableExtra(EXTRA_MESSAGE_ROOM_LIST);

        Toolbar toolbar = findViewById(R.id.user_setting_ai_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        deleteLayout = findViewById(R.id.aiset_delete_layout);

        btnDelete = findViewById(R.id.aiset_delete_btn);
        btnDelete.setOnClickListener(this);

        btnDeleteCancle = findViewById(R.id.aiset_delete_cancle_btn);
        btnDeleteCancle.setOnClickListener(this);

        txtEmpty = findViewById(R.id.empty_usa_txt);
        if (!aiSetArrayList.isEmpty())
            txtEmpty.setVisibility(View.GONE);

        RecyclerView listAISet = findViewById(R.id.user_setting_ai_recycler);
        listAISet.setLayoutManager(new LinearLayoutManager(this));
        aiSetAdapter = new AISetListAdapter(this);
        listAISet.setAdapter(aiSetAdapter);
        setList();
    }

    private void updateAISetList(){
        String strList = gson.toJson(aiSetArrayList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_AISET, strList);
        editor.commit();
    }

    private void setList(){
        for (AISet a : aiSetArrayList){
            aiSetAdapter.addItem(a);
        }
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
                finish();
                break;
            case R.id.action_delete:
                // 인공지능 삭제
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                deleteLayout.setVisibility(View.VISIBLE);
                aiSetAdapter.activateDeleteMenu();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent getStartIntent(Context context, ArrayList<Room> roomList){
        Intent intent = new Intent(context, UserSettingAIActivity.class);
        intent.putExtra(EXTRA_MESSAGE_ROOM_LIST, roomList);
        return intent;
    }

    @Override
    public void deleteAISet(int[] index) {
        int size = aiSetArrayList.size();
        for (int i : index){
            for (int j = 0; j < size; j++){
                if (aiSetArrayList.get(j).getAiSetID() == i){
                    aiSetArrayList.remove(j);
                    size--;
                }
            }
        }
        if (aiSetArrayList.isEmpty())
            txtEmpty.setVisibility(View.VISIBLE);
        updateAISetList();
    }

    @Override
    public void changeAISetOn(int pos, int on) {
        aiSetArrayList.get(pos).setOn(on);
        updateAISetList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.aiset_delete_btn:
                aiSetAdapter.deleteAISet();
                break;
            case R.id.aiset_delete_cancle_btn:
                aiSetAdapter.cancleDeleteMenu();
                deleteLayout.setVisibility(View.GONE);
                if (aiSetArrayList.isEmpty())
                    txtEmpty.setVisibility(View.VISIBLE);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                break;
        }
    }
}
