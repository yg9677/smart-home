package com.example.project_smart_home.refine;

import java.util.StringTokenizer;

public class DateRefine {
    int i=1;
    String [] data = new String[3];
    public DateRefine(){}

    public String[] getData(String result){
        StringTokenizer token;
        token = new StringTokenizer(result,"/" );
        String temp;
        while (token.hasMoreTokens()) {
            temp = token.nextToken(); //값 받아오기
            switch(i){
                case 1:
                    data[0]=temp;
                    i++;
                    break;
                case 2:
                    data[1]=temp;
                    i++;
                    break;
                case 3:
                    data[2]=temp;
                    i++;
                    break;
            }
        }

        return data;
    }

}
