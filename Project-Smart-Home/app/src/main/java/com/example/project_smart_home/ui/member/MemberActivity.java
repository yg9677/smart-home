package com.example.project_smart_home.ui.member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.project_smart_home.R;

public class MemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
    }

    public static Intent getStartIntent(Context context){
        Intent intent = new Intent(context, MemberActivity.class);
        return intent;
    }
}
