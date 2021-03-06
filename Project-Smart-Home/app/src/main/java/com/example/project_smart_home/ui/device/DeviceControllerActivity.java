package com.example.project_smart_home.ui.device;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.project_smart_home.R;
import com.example.project_smart_home.object.Device;
import com.example.project_smart_home.ui.member.MemberActivity;

import static com.example.project_smart_home.utils.Constants.EXTRA_MESSAGE_DEVICE;
import static com.example.project_smart_home.utils.Constants.EXTRA_MESSAGE_ROOM_NAME;
import static com.example.project_smart_home.utils.Constants.FUNCTION_KEY_TEMP;

// 디바이스 상세 조정 액티비티
public class DeviceControllerActivity extends AppCompatActivity implements View.OnClickListener {
    Device device;                                  // 디바이스 정보
    String roomname;                                // 소속된 방 이름

    View view;                                      // 온도 기능 ui
    TextView txtValue;                              // 온도 수치 텍스트
    ImageButton btnOnOff, btnTempUp, btnTempDown;   // on/off, 온도 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_controller);

        device = (Device)getIntent().getSerializableExtra(EXTRA_MESSAGE_DEVICE);
        roomname = getIntent().getStringExtra(EXTRA_MESSAGE_ROOM_NAME);

        Toolbar toolbar = findViewById(R.id.device_controller_toolbar);
        toolbar.setTitle(device.getName());
        toolbar.setSubtitle(roomname);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setItem();
    }

    // ui 세팅
    private void setItem(){
        btnOnOff = findViewById(R.id.cdc_onoff_btn);
        btnOnOff.setOnClickListener(this);
        view = findViewById(R.id.controll_temp_item);

        // 디바이스 기능이 온도가 아닐 경우
        if(!device.getDeviceKind().equals(FUNCTION_KEY_TEMP)){
            view.setVisibility(View.GONE);
        }else {
            // 디바이스 기능이 온도일 경우
            txtValue = findViewById(R.id.controll_temp_txt);
            txtValue.setText(device.getName());
            btnTempUp = findViewById(R.id.temp_up_btn);
            btnTempUp.setOnClickListener(this);
            btnTempDown = findViewById(R.id.temp_down_btn);
            btnTempDown.setOnClickListener(this);
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            // 뒤로가기
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent getStartIntent(Context context, Device d, String roomname){
        Intent intent = new Intent(context, DeviceControllerActivity.class);
        intent.putExtra(EXTRA_MESSAGE_DEVICE, d);           // 디바이스 정보
        intent.putExtra(EXTRA_MESSAGE_ROOM_NAME, roomname); // 방 정보
        return intent;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cdc_onoff_btn:
                break;
            case R.id.temp_up_btn:
                break;
            case R.id.temp_down_btn:
                break;
        }

    }
}
