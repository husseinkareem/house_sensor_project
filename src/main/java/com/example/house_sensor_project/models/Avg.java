package com.example.house_sensor_project.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class Avg implements Serializable {
    private String HusSektion;
    private String Genomsnitt;

    private String Datum;
    private String Sensor;


    public Avg(String husSektion, String genomsnitt, String datum, String sensor){
        this.HusSektion = husSektion;
        this.Genomsnitt = genomsnitt;
        this.Datum = datum;
        this.Sensor = sensor;
    }
    public Avg(){}

}
