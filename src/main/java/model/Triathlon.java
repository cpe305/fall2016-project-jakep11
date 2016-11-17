package model;

import java.util.Date;

public class Triathlon {

  public enum WeatherConditions {
    SUNNY, WINDY, LIGHT_RAIN, HEAVY_RAIN, DRY, HUMID
  }
  
  public enum Temperature {
    COLD, COOL, MODERATE, WARM, HOT
  }

  private WeatherConditions weather;
  private Temperature temperature;
  

  private Date date;
  private TriathlonDistance distance;
  private TriathlonElevation elevation;
  private TriathlonTime time;
  private String name;
  private String location;
  private Time startTime;
  
  public WeatherConditions getWeather() {
    return weather;
  }

  public void setWeather(WeatherConditions weather) {
    this.weather = weather;
  }

  public Temperature getTemperature() {
    return temperature;
  }

  public void setTemperature(Temperature temperature) {
    this.temperature = temperature;
  }
  
  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public TriathlonDistance getDistance() {
    return distance;
  }

  public void setDistance(TriathlonDistance distance) {
    this.distance = distance;
  }

  public TriathlonElevation getElevation() {
    return elevation;
  }

  public void setElevation(TriathlonElevation elevation) {
    this.elevation = elevation;
  }

  public TriathlonTime getTime() {
    return time;
  }

  public void setTime(TriathlonTime time) {
    this.time = time;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Time getStartTime() {
    return startTime;
  }

  public void setStartTime(Time startTime) {
    this.startTime = startTime;
  }

  public Triathlon(WeatherConditions weather, Date date, TriathlonDistance distance,
      TriathlonElevation elevation, TriathlonTime time, String name, String location,
      Time startTime) {
    super();
    this.weather = weather;
    this.date = date;
    this.distance = distance;
    this.elevation = elevation;
    this.time = time;
    this.name = name;
    this.location = location;
    this.startTime = startTime;
  }

    public Triathlon() {
      
    }


}
