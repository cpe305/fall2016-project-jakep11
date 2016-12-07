package com.triathlon;

import static org.junit.Assert.assertEquals;

import model.Time;
import org.junit.Test;


public class TimeTest {

  @Test
  public void testSetTime() {
    Time time1 = new Time(1, 2, 3);
    assertEquals(3723, time1.getTimeInSeconds());
    time1.setTime(2, 2, 10);
    assertEquals(7330, time1.getTimeInSeconds());

    Time time2 = new Time(0, 0, 20);
    assertEquals(20, time2.getTimeInSeconds());
    time2.setTime(2, 0, 25);
    assertEquals(7225, time2.getTimeInSeconds());
    
    Time time3 = new Time(59);
    assertEquals(59, time3.getTimeInSeconds());
    Time time4 = new Time();
  }
}
