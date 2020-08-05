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

public class RoomActivity extends AppCompatActivity {
    Room room;
    View[] dataViews = new View[4];
    TextView[] dataValues = new TextView[4];
    TextView[] optimalDataValues = new TextView[4];
    TextView txtRecommend;

    int[] viewId = { R.id.temp_item, R.id.hum_item, R.id.dust_item, R.id.disc_item };

    MeasuredData sampleData;

    private void setSampleData(){
        sampleData = new MeasuredData(28.2, 56.2, 17.9, 35);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        setSampleData();
        this.room = (Room) getIntent().getSerializableExtra(EXTRA_MESSAGE_ROOM);

        Toolbar toolbar = findViewById(R.id.room_name_toolbar);
        toolbar.setTitle(room.getRoomname());
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bindViews();
        setDataValues();
    }

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
        dataValues[3].setText(String.valueOf(sampleData.getDiscomfort()));
        optimalDataValues[0].setText("26");
        optimalDataValues[1].setText("30%");
        optimalDataValues[2].setText("0");
        optimalDataValues[3].setText("25");
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

    public static Intent getStartIntent(Context context, Room room){
        Intent intent = new Intent(context, RoomActivity.class);
        intent.putExtra(EXTRA_MESSAGE_ROOM,room);
        return intent;
    }
}
