package com.example.project_smart_home.refine;
import com.example.project_smart_home.object.MeasuredData;



import java.util.StringTokenizer;

public class MeasuredDataRefine {
    MeasuredData measuredData;

    int i=1;
    public MeasuredDataRefine(){}

    public MeasuredData getMeasuredData(String result){
        StringTokenizer token;
        token = new StringTokenizer(result,"/" );
        String temp;
        while (token.hasMoreTokens()) {
            temp = token.nextToken(); //값 받아오기
            switch(i){
                case 1:
                    measuredData =new MeasuredData();
                    measuredData.setNumber(Integer.parseInt(temp));
                    i++;
                    break;
                case 2:
                    measuredData.setRoom(temp);
                    i++;
                    break;
                case 3:
                    measuredData.setRegday(temp);
                    i++;
                    break;
                case 4:
                    measuredData.setTemperature(Integer.parseInt(temp));
                    i++;
                    break;
                case 5:
                    measuredData.setHumidity(Integer.parseInt(temp));
                    i++;
                    break;
                case 6:
                    measuredData.setDust(Integer.parseInt(temp));
                    i=1;
                    break;
            }
        }

        return measuredData;
    }
}
