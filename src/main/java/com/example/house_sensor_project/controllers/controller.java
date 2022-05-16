package com.example.house_sensor_project.controllers;

import com.example.house_sensor_project.models.sensorData;
import com.example.house_sensor_project.repositories.HouseDAO;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/DBService")
public class controller {

    private HouseDAO dataDAO = new HouseDAO();

    @GetMapping("/alldata")
    public List<sensorData> getAllData(){
        return dataDAO.getAllData();
    }
    @GetMapping("/latesttemp")
    public sensorData getLatestTemp(){
        return dataDAO.getLatestTemp();
    }
    @GetMapping("/latesthumidity")
    public sensorData getLatestHumidity(){
        return dataDAO.getLatestHumidity();

    }
    @GetMapping("/latestenergy")
    public sensorData getLatestEnergy(){
        return dataDAO.getLatestEnergy();
    }
    @PostMapping ("/addenergyprice")
    public boolean addEnergyPrice(@RequestBody String price){
        return dataDAO.addEnergyPrice(price);
    }
}
