package com.example.house_sensor_project.models;

public class OverAllData {
    String tempData;
    String humidityData;
    String price;

    public OverAllData(String tempData, String humidityData, String price){
        this.tempData = tempData;
        this.humidityData = humidityData;
        this.price = price;
    }
}
