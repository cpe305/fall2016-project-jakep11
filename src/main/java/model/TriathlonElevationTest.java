package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TriathlonElevationTest {

  TriathlonElevation triElev, triElevRand;
  double epsilon = 0.0001;
  
  @Before
  public void setup() {
    triElev = new TriathlonElevation(1000.5, 200);
    triElevRand = new TriathlonElevation();

  }

  @Test
  public void testGetBikeElevation() {
    assertEquals(triElev.getBikeElevation(), 1000.5, epsilon);
  }

  @Test
  public void testSetBikeElevation() {
    triElevRand.setBikeElevation(677);
    assertEquals(triElevRand.getBikeElevation(), 677, epsilon);
  }

  @Test
  public void testGetRunElevation() {
    assertEquals(triElev.getRunElevation(), 200, epsilon);
  }

  @Test
  public void testSetRunElevation() {
    triElevRand.setRunElevation(22.6);
    assertEquals(triElevRand.getRunElevation(), 22.6, epsilon);
  }

}
