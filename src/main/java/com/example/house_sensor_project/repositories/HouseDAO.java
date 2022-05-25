package com.example.house_sensor_project.repositories;

import com.example.house_sensor_project.models.Avg;
import com.example.house_sensor_project.models.sensorData;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class HouseDAO {

    Properties p = new Properties();

    public void HouseDaoDB ()   {
        try {
            p.load(new FileInputStream("src/main/resources/application.properties"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public List<sensorData> getAllData(){
        List<sensorData> dataList = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sensor_project",
                "root2", "hejhej");

             Statement stmt = con.createStatement();

             ResultSet rs = stmt.executeQuery("select timestamp, data from Sensor")){

            while (rs.next()) {
                String time = rs.getString("timestamp");
                String data = rs.getString("data");
                dataList.add(new sensorData(data, time));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return dataList;
    }

        public sensorData getLatest(){

            try (Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sensor_project",
                    "root2", "hejhej");

                 Statement stmt = con.createStatement();

                 ResultSet rs = stmt.executeQuery("SELECT * FROM sensor_project.Sensor order by timestamp desc limit 1")){

                while (rs.next()) {
                    String time = rs.getString("timestamp");
                    String data = rs.getString("data");
                    return new sensorData(data, time);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return new sensorData();
        }
    public sensorData getLatestTemp(){

        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sensor_project",
                "root2", "hejhej");

             Statement stmt = con.createStatement();

             ResultSet rs = stmt.executeQuery("SELECT * FROM sensor_project.Sensor inner join Sensor_type on Sensor.sensor_type_id=Sensor_type.idsensor_type where Sensor_type.sensor_name = 'temp' order by timestamp desc limit 1")){

            while (rs.next()) {
                String time = rs.getString("timestamp");
                String data = rs.getString("data");
                return new sensorData(data, time);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new sensorData();

    }
    public sensorData getLatestHumidity(){

        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sensor_project",
                "root2", "hejhej");

             Statement stmt = con.createStatement();

             ResultSet rs = stmt.executeQuery("SELECT * FROM sensor_project.Sensor inner join Sensor_type on Sensor.sensor_type_id=Sensor_type.idsensor_type where Sensor_type.sensor_name = 'humidity' order by timestamp desc limit 1")){

            while (rs.next()) {
                String time = rs.getString("timestamp");
                String data = rs.getString("data");
                return new sensorData(data, time);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new sensorData();
    }
        public sensorData getLatestEnergy(){

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sensor_project", "root2", "hejhej");

             Statement stmt = con.createStatement();

             ResultSet rs = stmt.executeQuery("SELECT * FROM sensor_project.Energy_price order by timestamp desc limit 1")){

            while (rs.next()) {
                String time = rs.getString("timestamp");
                String data = rs.getString("Energy_price");
                return new sensorData(data, time);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new sensorData();
    }
    public boolean addEnergyPrice(String price){
        String query = "INSERT INTO `sensor_project`.`Energy_price` (`Energy_price`) VALUES (?)";
        int rowChanged = 0;

        try (
                Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/sensor_project", "root2", "hejhej");
                PreparedStatement stmt = con.prepareStatement(query)){

            stmt.setString(1, price);
            rowChanged = stmt.executeUpdate();
            if (rowChanged == 1){
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public List<sensorData> avgTempB(){
        List<sensorData> theList = new ArrayList<>();

        String query1 = "select House_section.section_name as HusSektion, avg(data) as Genomsnitt, date(timestamp) as Datum, Sensor_type.sensor_name as Sensor from Sensor  inner join House_section on Sensor.house_sensor_id = House_section.idHouse_section inner join Sensor_type on Sensor.sensor_type_id = Sensor_type.idsensor_type   where date(timestamp) = date(timestamp) and House_section.section_name = 'B' and Sensor_type.sensor_name = 'temp' group by date(timestamp), Sensor.sensor_type_id, House_section.idHouse_section  order by date(timestamp) desc;";

        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sensor_project",
                "root2", "hejhej");

             Statement stmt = con.createStatement();

             ResultSet rs = stmt.executeQuery(query1)
        ){

            while (rs.next()) {
                String data = rs.getString("Genomsnitt");
                String time = rs.getString("Datum");
                theList.add(new sensorData(data, time));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return theList;

    }
}