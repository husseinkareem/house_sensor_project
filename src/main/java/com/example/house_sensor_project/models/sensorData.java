package com.example.house_sensor_project.models;

import java.io.Serializable;
import lombok.Data;

@Data
public class sensorData implements Serializable {
    private String data;
    private String dateTime;

    public sensorData(String Data, String dateTime){
        this.data = Data;
        this.dateTime = dateTime;
    }
    public sensorData(){}
}
