package com.triathlon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.TriathlonDistance;
import model.TriathlonDistance.StandardDistance;

public class TriathlonDistanceTest {

  // @Test
  // public void testTriathlonDistanceStandardDistance() {
  // fail("Not yet implemented");
  // }
  //
  TriathlonDistance triDistSprint, triDistOlympic, triDistHalf, triDistIron, triDistRandom;
  double epsilon = 0.0001;

  @Before
  public void setup() {
    triDistSprint = new TriathlonDistance(StandardDistance.SPRINT);
    triDistOlympic = new TriathlonDistance(StandardDistance.OLYMPIC);
    triDistHalf = new TriathlonDistance(StandardDistance.HALFIRON);
    triDistIron = new TriathlonDistance(StandardDistance.IRON);
    triDistRandom = new TriathlonDistance();

  }

  @Test
  public void testGetSwim() {
    assertEquals(triDistSprint.getSwim(), 750, epsilon);
  }

  @Test
  public void testSetSwim() {
    triDistRandom.setSwim(499);
    assertEquals(triDistRandom.getSwim(), 499, epsilon);
  }

  @Test
  public void testGetBike() {
    assertEquals(triDistIron.getBike(), 112, epsilon);
  }

  @Test
  public void testSetBike() {
    triDistRandom.setBike(20.1);
    assertEquals(triDistRandom.getBike(), 20.1, epsilon);
  }

  @Test
  public void testGetRun() {
    assertEquals(triDistHalf.getRun(), 13.1, epsilon);
  }

  @Test
  public void testSetRun() {
    triDistRandom.setRun(4.3);
    assertEquals(triDistRandom.getRun(), 4.3, epsilon);
  }

  

}
