package com.triathlon;

import java.util.Date;

public class Triathlon {

  public enum Weather {
    SUNNY, WINDY, RAINY
  }
  
  public Weather weather;
  public double elevationChange;
  public Date date;
  public double distance;
  public double latitude;
  public double longitude;
  
  
  public Triathlon(Weather weather, double elevationChange, Date date, double distance,
      double latitude, double longitude) {
    super();
    this.weather = weather;
    this.elevationChange = elevationChange;
    this.date = date;
    this.distance = distance;
    this.latitude = latitude;
    this.longitude = longitude;
  }
  
  public Weather getWeather() {
    return weather;
  }
  public void setWeather(Weather weather) {
    this.weather = weather;
  }
  public double getElevationChange() {
    return elevationChange;
  }
  public void setElevationChange(double elevationChange) {
    this.elevationChange = elevationChange;
  }
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  public double getDistance() {
    return distance;
  }
  public void setDistance(double distance) {
    this.distance = distance;
  }
  public double getLatitude() {
    return latitude;
  }
  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }
  public double getLongitude() {
    return longitude;
  }
  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }
}
