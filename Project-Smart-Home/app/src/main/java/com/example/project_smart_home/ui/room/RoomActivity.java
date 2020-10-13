package com.example.project_smart_home.ui.room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.project_smart_home.R;
import com.example.project_smart_home.object.MeasuredData;
import com.example.project_smart_home.object.Room;

import static com.example.project_smart_home.utils.Constants.EXTRA_MESSAGE_ROOM;
import static com.example.project_smart_home.utils.Constants.TEXT_MEASUREDDATA;

// 방 액티비티
// 방의 실시간 측정 데이터를 확인하는 UI.
public class RoomActivity extends AppCompatActivity {
    Room room;                                      // 방 정보
    View[] dataViews = new View[4];                 // 측정 데이터 ui
    TextView[] dataValues = new TextView[4];        // 데이터 값
    TextView[] optimalDataValues = new TextView[4]; // 권장 데이터 값
    TextView txtRecommend;                          // 권장 메시지

    int[] viewId = { R.id.temp_item, R.id.hum_item, R.id.dust_item, R.id.disc_item };   // layout id

    MeasuredData sampleData;                        // 측정 데이터

    // 샘플 데이터
    private void setSampleData(){
        sampleData = room.getMeasuredData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        setSampleData();
        this.room = (Room) getIntent().getSerializableExtra(EXTRA_MESSAGE_ROOM);

        Toolbar toolbar = findViewById(R.id.room_name_toolbar);
        toolbar.setTitle(room.getRoom());
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bindViews();
        setDataValues();
    }

    // ui 적용
    private void bindViews(){
        for(int i = 0; i<4; i++) {
            dataViews[i] = findViewById(viewId[i]);
            setViewText(dataViews[i], TEXT_MEASUREDDATA.get(i));
            dataValues[i] = dataViews[i].findViewById(R.id.data_value_txt);
            optimalDataValues[i] = dataViews[i].findViewById(R.id.optimal_value_txt);
        }
        txtRecommend = findViewById(R.id.recommend_txt);
        txtRecommend.setText("권장메시지가 없습니다.");
    }

    private void setViewText(View v, String text){
        TextView txtName = v.findViewById(R.id.data_name_txt);
        txtName.setText(text);
        TextView txtOptimal = v.findViewById(R.id.optimal_data_txt);
        txtOptimal.setText("적정 " + text);
    }

    private void setDataValues(){
        dataValues[0].setText(String.valueOf(sampleData.getTemperature()));
        dataValues[1].setText(String.valueOf(sampleData.getHumidity()) + "%");
        dataValues[2].setText(String.valueOf(sampleData.getDust()));
        dataValues[3].setText("모름");
        optimalDataValues[0].setText("26");
        optimalDataValues[1].setText("30%");
        optimalDataValues[2].setText("0");
        optimalDataValues[3].setText("25");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:             //뒤로가기
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent getStartIntent(Context context, Room room){
        Intent intent = new Intent(context, RoomActivity.class);
        intent.putExtra(EXTRA_MESSAGE_ROOM,room);       // 방 정보
        return intent;
    }
}
