package com.example.project_smart_home.object;

import java.io.Serializable;

public class MeasuredData {
    private double temperature;
    private double humidity;
    private double dust;
    private double discomfort;

    public MeasuredData(double temperature, double humidity, double dust, double discomfort) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.dust = dust;
        this.discomfort = discomfort;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getDust() {
        return dust;
    }

    public double getDiscomfort() {
        return discomfort;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setDust(double dust) {
        this.dust = dust;
    }

    public void setDiscomfort(double discomfort) {
        this.discomfort = discomfort;
    }
}
