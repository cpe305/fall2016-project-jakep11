package model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Triathlon {

  public enum WeatherConditions {
    SUNNY, WINDY, LIGHT_RAIN, HEAVY_RAIN, DRY, HUMID
  }


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @OneToOne(cascade = CascadeType.ALL)
  private TriathlonElevation elevation;
  
  @OneToOne(cascade = CascadeType.ALL)
  private TriathlonDistance distance;
  
  @OneToOne(cascade = CascadeType.ALL)
  private TriathlonTime time;
  
  private String name;
  private String location;
  private Date date;
  
  
  private String startTime;
  private WeatherConditions weather;
  private double temperature;

  public Long getId() {
    return id;
  }
  
  public WeatherConditions getWeather() {
    return weather;
  }

  public void setWeather(WeatherConditions weather) {
    this.weather = weather;
  }

  public double getTemperature() {
    return temperature;
  }

  public void setTemperature(double temperature) {
    this.temperature = temperature;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "Triathlon", cascade = CascadeType.MERGE)
  public TriathlonDistance getDistance() {
    return distance;
  }

  public void setDistance(TriathlonDistance distance) {
    this.distance = distance;
  }

  //@OneToOne(fetch = FetchType.LAZY, mappedBy = "Triathlon", cascade = CascadeType.MERGE)
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

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }



  /**
   * Triathlon constructor.
   * @param distance distance
   * @param elevation elevation
   * @param time time
   * @param name name
   * @param location location
   * @param date date
   * @param startTime startTime
   * @param weather weather
   * @param temperature temperature
   */
  public Triathlon(TriathlonDistance distance, TriathlonElevation elevation, TriathlonTime time,
      String name, String location, Date date, String startTime, WeatherConditions weather,
      double temperature) {
    this.distance = distance;
    this.elevation = elevation;
    this.time = time;
    this.name = name;
    this.location = location;
    this.date = date;
    this.startTime = startTime;
    this.weather = weather;
    this.temperature = temperature;
  }

  public Triathlon() {

  }


}
