package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Project Description.
 * 
 * @author Jake Pickett
 * @author Sonia Narayanan
 * @version October 12, 2016
 */

//@Entity
public class TriathlonTime {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Time swimTime;
  private Time t1Time;
  private Time bikeTime;
  private Time t2Time;
  private Time runTime;

  /**
   * Class to hold the time for a Triathlon.
   * 
   * @param swim swim Time
   * @param t1 t1 Time
   * @param bike bike Time
   * @param t2 t2 Time
   * @param run Run Time
   */
  public TriathlonTime(Time swim, Time t1, Time bike, Time t2, Time run) {
    this.swimTime = swim;
    this.t1Time = t1;
    this.bikeTime = bike;
    this.t2Time = t2;
    this.runTime = run;
  }
  
  public TriathlonTime() {
    
  }

  public Time getSwimTime() {
    return swimTime;
  }

  public void setSwimTime(Time newSwimTime) {
    this.swimTime = newSwimTime;
  }

  public Time getT1Time() {
    return t1Time;
  }

  public void setT1Time(Time newT1Time) {
    this.t1Time = newT1Time;
  }

  public Time getBikeTime() {
    return bikeTime;
  }

  public void setBikeTime(Time newBikeTime) {
    this.bikeTime = newBikeTime;
  }

  public Time getT2Time() {
    return t2Time;
  }

  public void setT2Time(Time newT2Time) {
    this.t2Time = newT2Time;
  }

  public Time getRunTime() {
    return runTime;
  }

  public void setRunTime(Time newRunTime) {
    this.runTime = newRunTime;
  }

  public int getTotalTimeInSeconds() {
    return swimTime.getTimeInSeconds() + t1Time.getTimeInSeconds() + bikeTime.getTimeInSeconds()
        + t2Time.getTimeInSeconds() + runTime.getTimeInSeconds();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj.getClass() != TriathlonTime.class) {
      return false;
    }
    TriathlonTime triTime = (TriathlonTime) obj;
    
    return swimTime.equals(triTime.getSwimTime()) && t1Time.equals(triTime.getT1Time())
        && bikeTime.equals(triTime.getBikeTime()) && t2Time.equals(triTime.getT2Time())
        && runTime.equals(triTime.getRunTime());
  }

  @Override
  public String toString() {
    return "S: " + swimTime + ". T1: " + t1Time + ". Bike: " + bikeTime + ". T2: " + t2Time
        + ". Run: " + runTime;
  }
}


