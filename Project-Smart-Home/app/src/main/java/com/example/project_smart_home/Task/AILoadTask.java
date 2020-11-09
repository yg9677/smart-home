package com.example.project_smart_home.Task;

import android.os.AsyncTask;
import android.util.Log;

import com.example.project_smart_home.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class AILoadTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        String path="";
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://"+ MainActivity.myIp+":8080/SmartHomeProject/"+path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                conn.setRequestMethod("POST");

                PrintWriter osw = new PrintWriter(conn.getOutputStream());
                sendMsg = "code="+strings[0];
                osw.write(sendMsg);
                osw.flush();
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return receiveMsg;
        }

        public void selectPath(String path){ this.path = path; }
}

