package com.example.house_sensor_project.controllers;

import com.example.house_sensor_project.repositories.HouseDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeleafController {
    private HouseDAO dataDAO = new HouseDAO();

    @RequestMapping (value = "/overalldata")
    public String overallData(Model model){
        model.addAttribute("temp", dataDAO.getLatestTemp());
        model.addAttribute("humidity", dataDAO.getLatestHumidity());
        model.addAttribute("price", dataDAO.getLatestEnergy());
        return "index";
    }
}
//avrage, groupby för vg

