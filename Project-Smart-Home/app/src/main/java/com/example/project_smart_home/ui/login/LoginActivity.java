package com.example.project_smart_home.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project_smart_home.MainActivity;
import com.example.project_smart_home.R;

import static com.example.project_smart_home.utils.Constants.APP_TEST;
import static com.example.project_smart_home.utils.Constants.USER_ID;

// 로그인 액티비티
public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginView{
    EditText etxtId, etxtPw;

    LoginPresenter loginPresenter;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        checkUserId();

        loginPresenter = new LoginPresenter(this);

        etxtId = findViewById(R.id.input_email_login);
        etxtPw = findViewById(R.id.input_pass_login);
        Button btn_login = findViewById(R.id.login_btn);
        btn_login.setOnClickListener(this);
    }

    // 기존에 로그인한 아이디가 있는지 체크하여 있을 경우 MainActivity로 넘어간다.
    private void checkUserId(){
        if(mSharedPreferences.getString(USER_ID, "").equals(""))
            startMainActivity();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_btn:
                if(APP_TEST){
                    startMainActivity();
                    break;
                }
                loginPresenter.login(etxtId.getText().toString(), etxtPw.getText().toString());
                break;
        }
    }

    @Override
    public void saveUserInfo(String id) {           // 유저 아이디를 앱에 저장한다.
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(USER_ID, id);
        editor.apply();
    }

    public void startMainActivity(){
        Intent i = MainActivity.getStartIntent(this);
        startActivity(i);
        finish();
    }

    @Override
    public void failLogin() {

    }
}
