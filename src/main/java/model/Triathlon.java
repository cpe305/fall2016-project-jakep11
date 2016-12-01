package model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

@Entity
public class Triathlon {

  public enum WeatherConditions {
    SUNNY, WINDY, LIGHT_RAIN, HEAVY_RAIN, DRY, HUMID
  }

  public enum Temperature {
    COLD, COOL, MODERATE, WARM, HOT
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @OneToOne(cascade = CascadeType.ALL)
//  @JoinTable(
//      name = "Branch_Merge_Commit",
//      joinColumns = @JoinColumn(
//          name = "commit_id", 
//          referencedColumnName = "id"),
//      inverseJoinColumns = @JoinColumn(
//          name = "branch_merge_id", 
//          referencedColumnName = "id")
//  )
  private TriathlonElevation elevation;
  
  @OneToOne(cascade = CascadeType.ALL)
  private TriathlonDistance distance;
  
  @OneToOne(cascade = CascadeType.ALL)
  private TriathlonTime time;
  
  private String name;
  private String location;
  private Date date;
  
  @OneToOne(cascade = CascadeType.ALL)
  private Time startTime;
  private WeatherConditions weather;
  private Temperature temperature;

  public Long getID() {
    return id;
  }
  
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

  public Time getStartTime() {
    return startTime;
  }

  public void setStartTime(Time startTime) {
    this.startTime = startTime;
  }



  public Triathlon(TriathlonDistance distance, TriathlonElevation elevation, TriathlonTime time,
      String name, String location, Date date, Time startTime, WeatherConditions weather,
      Temperature temperature) {
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
