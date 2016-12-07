package com.triathlon;

import static org.junit.Assert.assertEquals;

import model.Estimator;
import model.Stats;
import model.Time;
import model.Triathlon;
import model.Triathlon.WeatherConditions;
import model.TriathlonDistance;
import model.TriathlonElevation;
import model.TriathlonTime;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class EstimatorTest {

  Triathlon tri1;
  Triathlon tri2;
  TriathlonTime triTime;
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
    triTime = new TriathlonTime(time1, time2, time3, time4, time5);

    WeatherConditions weather1 = WeatherConditions.SUNNY;
    WeatherConditions weather2 = WeatherConditions.SUNNY;

    double temp = 68;

    tri1 = new Triathlon(triDist, triElev, triTime, "TestTri", "Venus", date, "6:00AM", weather1,
        temp);
    tri2 = new Triathlon(triDist, triElev, triTime, "TestTri2", "Mars", date, "7:00AM", weather2,
        temp);

    triList = new ArrayList<Triathlon>();
    triList.add(tri1);
    triList.add(tri2);
  }

  @Test
  public void estimatorTest1() {
    Estimator.estimateNewTriathlon(triList, tri1);
  }

  @Test
  public void estimatorTest2() {
    Estimator.estimateNewTriathlon(triList, tri2);
  }

  @Test
  public void scaleTest1() {
    Estimator.scaleSwimTime(1.1, triTime);
    Estimator.scaleBikeTime(1.2, triTime);
    Estimator.scaleRunTime(1.05, triTime);

  }

}
