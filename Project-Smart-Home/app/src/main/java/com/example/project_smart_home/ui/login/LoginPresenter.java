package com.example.project_smart_home.ui.login;

import com.example.project_smart_home.MainActivity;
import com.example.project_smart_home.Task.CommunicateTask;

import java.util.concurrent.ExecutionException;

import static com.example.project_smart_home.utils.Constants.PATH_CODE_LOGIN;
import static com.example.project_smart_home.utils.Constants.SEND_SERVER_LOGIN;

public class LoginPresenter {
    LoginView loginView;
    CommunicateTask communicateTask;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public void login(String id, String pw){
            String loginInfo = "";                              // 아이디, 비밀번호 정보
            communicateTask = new CommunicateTask();
            communicateTask.selectTask(SEND_SERVER_LOGIN);
            communicateTask.selectPath(PATH_CODE_LOGIN);
        try {
            String response = communicateTask.execute(loginInfo).get();
            if (response.equals("success")){            // 로그인 성공 ( 성공여부 메시지는 회의 후 결정 )
                loginView.saveUserInfo(id);
                loginView.startMainActivity();
                MainActivity.myCode=id;
            } else {                                    // 로그인 실패
                loginView.failLogin();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
