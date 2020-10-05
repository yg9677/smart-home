package com.example.project_smart_home.ui.Enrollment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.project_smart_home.MainActivity;
import com.example.project_smart_home.R;
import com.example.project_smart_home.Task.KeySetTask;
import com.example.project_smart_home.Task.KeyTouchTask;

import java.security.Key;
import java.util.concurrent.ExecutionException;

public class KeyActivity extends AppCompatActivity implements Button.OnClickListener { //implements 버튼 등록시 필요한 인터페이스
    private String fristCode, secondCode, thirdCode, fouthCode;
    private EditText edit1, edit2, edit3, edit4;
    private Button savebtn;
    private String keySend;
    KeySetTask setTask;
    KeyTouchTask touchTask;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_keyset);
        Toolbar toolbar = findViewById(R.id.key_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        touchTask=new KeyTouchTask();
        try {
            touchTask.execute("door/insert").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setComponent();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.saveButton:
                setTask=new KeySetTask();
                fristCode=edit1.getText().toString();
                secondCode=edit2.getText().toString();
                thirdCode=edit3.getText().toString();
                fouthCode=edit4.getText().toString();
                System.out.println(fristCode+" "+secondCode+" "+thirdCode+" "+fouthCode);
                keySend=fristCode+" "+secondCode+" "+thirdCode+" "+fouthCode;
                try {
                    String result = setTask.execute(keySend).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(MainActivity.getStartIntent(this));
                finish();
                break;
        }
    }
    private void setComponent(){ //컴포넌트 등록
        edit1=(EditText)findViewById(R.id.editTextTextPersonName1);
        edit2=(EditText)findViewById(R.id.editTextTextPersonName2);
        edit3=(EditText)findViewById(R.id.editTextTextPersonName3);
        edit4=(EditText)findViewById(R.id.editTextTextPersonName4);
        savebtn=(Button)findViewById(R.id.saveButton);
        savebtn.setOnClickListener(this);

    }
    public static Intent getStartIntent(Context context){
        Intent intent = new Intent(context, KeyActivity.class);
        return intent;
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
}
