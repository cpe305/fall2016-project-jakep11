package com.triathlon;

import static org.junit.Assert.assertEquals;

import model.Time;
import model.TriathlonTime;

import org.junit.Test;

public class TriathlonTimeTest {

  @Test
  public void testGetTotalTimeInSeconds() {
    TriathlonTime triathtime1 = new TriathlonTime(new Time(0, 0, 2), new Time(0, 0, 3),
        new Time(0, 0, 4), new Time(0, 5, 0), new Time(1, 0, 0));
    assertEquals(3909, triathtime1.getTotalTimeInSeconds());
  }

  @Test
  public void testGetTotalTimeInSeconds2() {
    TriathlonTime triathtime1 = new TriathlonTime(new Time(0, 0, 2), new Time(0, 0, 4),
        new Time(0, 0, 4), new Time(0, 5, 0), new Time(1, 0, 0));
    assertEquals(3910, triathtime1.getTotalTimeInSeconds());
  }

  @Test 
  public void testGettersAndSetters() {
    TriathlonTime triTime = new TriathlonTime();
    triTime.setSwimTime(new Time(234));
    triTime.setT1Time(new Time(32));
    triTime.setBikeTime(new Time(1800));
    triTime.setT2Time(new Time(15));
    triTime.setRunTime(new Time(1111));
    assertEquals(triTime, new TriathlonTime(new Time(234),new Time(32),new Time(1800),new Time(15),new Time(1111)));
  }

}
