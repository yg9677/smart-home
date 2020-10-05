package com.example.project_smart_home.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project_smart_home.MainActivity;
import com.example.project_smart_home.R;

// 로그인 액티비티
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn_login = findViewById(R.id.login_btn);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_btn:
            startMainActivity();
        }
    }

    public void startMainActivity(){
        Intent i = MainActivity.getStartIntent(this);
        startActivity(i);
        finish();
    }
}
