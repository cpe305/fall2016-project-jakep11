package com.triathlon;

import static org.junit.Assert.assertEquals;

import model.Time;
import model.TriathlonTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




@RunWith(SpringRunner.class)
@SpringBootTest
public class TriathlonTimeEstimatorApplicationTests {

  @Test
  public void contextLoads() {}

  @Test
  public void triathlonTimeTest() {
    TriathlonTime tt = new TriathlonTime(null, null, null, null, null);
    TriathlonTime tt1 = new TriathlonTime(new Time(0, 10, 0), new Time(0, 0, 15), new Time(1, 0, 0),
        new Time(0, 0, 15), new Time(0, 20, 0));

    assertEquals(tt.getBikeTime(), null);
    assertEquals(tt1.getBikeTime(), tt1.getBikeTime());

    assertEquals(tt1.getT1Time().getTimeInSeconds(), tt1.getT2Time().getTimeInSeconds());

    assertEquals(tt.getBikeTime(), null);


  }
}
