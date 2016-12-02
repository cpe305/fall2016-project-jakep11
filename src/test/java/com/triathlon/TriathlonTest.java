package com.triathlon;

import model.Time;
import model.Triathlon;
import model.TriathlonDistance;
import model.TriathlonElevation;
import model.TriathlonTime;
import model.Triathlon.WeatherConditions;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Date;


public class TriathlonTest {

  @Test
  public void testSetWeatherConditions() {
    TriathlonDistance triDist = new TriathlonDistance(500, 12, 3);
    TriathlonElevation triElev = new TriathlonElevation(500, 100);
    
    
    Date date = new Date(System.currentTimeMillis());
    
    Time time1 = new Time(0, 8, 30);
    Time time2 = new Time(30);
    Time time3 = new Time(0, 30, 15);
    Time time4 = new Time(10);
    Time time5 = new Time(0, 20, 5);
    TriathlonTime triTime = new TriathlonTime(time1, time2, time3, time4, time5);
    
    WeatherConditions weather = WeatherConditions.SUNNY;
    double temp = 88;
    
    Triathlon tri1 = new Triathlon();
    Triathlon tri2 = new Triathlon(triDist, triElev, triTime, "TestTri", "Venus", date, "7:00AM", weather, temp);
    Triathlon tri3 = new Triathlon(triDist, triElev, triTime, "TestTri2", "Mars", date, "7:00AM", weather, temp);
    assertNotSame(tri1, tri2);
    
  }
//
//  @Test
//  public void testSetElevationChange() {
//    Triathlon triath1 = new Triathlon(WeatherConditions.SUNNY, 1.2, new Date(), 0, 0, 0);
//    assertEquals(1.2, triath1.getElevationChange(), 0);
//    triath1.setElevationChange(2.3);
//    assertEquals(2.3, triath1.getElevationChange(), 0);
//  }
//
//  @Test
//  public void testSetDate() {
//    Triathlon triath1 = new Triathlon(WeatherConditions.SUNNY, 0, new Date(), 0, 0, 0);
//    assertEquals(new Date(), triath1.getDate());
//    triath1.setDate(new Date(2016, 10, 20));
//    assertEquals(new Date(2016, 10, 20), triath1.getDate());
//  }
//
//  @Test
//  public void testSetDistance() {
//    Triathlon triath1 = new Triathlon(WeatherConditions.SUNNY, 0, new Date(), 7.0, 0, 0);
//    assertEquals(7.0, triath1.getDistance(), 0);
//    triath1.setDistance(7.45);
//    assertEquals(7.45, triath1.getDistance(), 0);
//  }
//
//  @Test
//  public void testSetLatitude() {
//    Triathlon triath1 = new Triathlon(WeatherConditions.SUNNY, 0, new Date(), 0, 0.4, 0);
//    assertEquals(0.4, triath1.getLatitude(), 0);
//    triath1.setLatitude(1.23);
//    assertEquals(1.23, triath1.getLatitude(), 0);
//  }
//
//  @Test
//  public void testSetLongitude() {
//    Triathlon triath1 = new Triathlon(WeatherConditions.SUNNY, 0, new Date(), 0, 0, 0.04);
//    assertEquals(0.04, triath1.getLongitude(), 0);
//    triath1.setLongitude(5000.23);
//    assertEquals(5000.23, triath1.getLongitude(), 0);
//  }



}
