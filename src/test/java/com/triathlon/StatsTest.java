package com.triathlon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import model.Stats;
import model.Time;
import model.Triathlon;
import model.TriathlonDistance;
import model.TriathlonElevation;
import model.TriathlonTime;
import model.Triathlon.Temperature;
import model.Triathlon.WeatherConditions;

public class StatsTest {

//  private TriathlonDistance distance;
//  private TriathlonElevation elevation;
//  private TriathlonTime time;
//  private String name;
//  private String location;
//  private Date date;
//  private Time startTime;
//  private WeatherConditions weather;
//  private Temperature temperature;
  
  
  Triathlon tri1, tri2, tri3;
  ArrayList<Triathlon> triList; 
  double epsilon = 0.0001;
  
  @Before
  public void setup() {
    
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
    Temperature temp = Temperature.HOT;
    
    tri1 = new Triathlon();
    tri2 = new Triathlon(triDist, triElev, triTime, "TestTri", "Venus", date, time1, weather, temp);
    tri3 = new Triathlon(triDist, triElev, triTime, "TestTri2", "Mars", date, time5, weather, temp);

    triList = new ArrayList<Triathlon>();
    triList.add(tri2);
    triList.add(tri3);
  }
  
  @Test
  public void testBikeSpeed() {
    
    assertEquals(23.8016, Stats.bikeSpeed(tri2), epsilon);
  }

  @Test
  public void testRunSpeed() {
    assertEquals(6.69444444, Stats.runSpeed(tri2), epsilon);
  }

  @Test
  public void testSwimPace() {
    assertEquals(1.7, Stats.swimPace(tri2), epsilon);
  }

  @Test
  public void testGetAverageDistanceFromAllTris() {
    TriathlonDistance averageTriDist = Stats.getAverageDistanceFromAllTris(triList);

    assertEquals(tri2.getDistance(), averageTriDist);
  }

  @Test
  public void testGetAverageTimeFromAllTris() {
    TriathlonTime averageTriTime = Stats.getAverageTimeFromAllTris(triList);
    System.out.println(tri2.getTime());
    System.out.println(averageTriTime);
    assertEquals(tri2.getTime(), averageTriTime);
  }

  @Test
  public void testGetAveragePaceFromAllTris() {
    Triathlon averageTri = Stats.getAveragePaceFromAllTris(triList);
    assertEquals(tri2.getDistance(), averageTri.getDistance());
    assertEquals(tri2.getTime(), averageTri.getTime());
    
  }

}
