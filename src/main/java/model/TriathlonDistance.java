package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class that models the distance of a triathlon. Consists of swim, bike, and run distance.
 * 
 * @author Jake Pickett
 *
 */
@Entity
public class TriathlonDistance {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private double swim;
  private double bike;
  private double run;

  public enum StandardDistance {
    SPRINT, OLYMPIC, HALFIRON, IRON
  }

  /**
   * TriathlonDistance creates a default distance of a triathlon.
   * 
   * @param dist distance of triathlon
   */
  public TriathlonDistance(StandardDistance dist) {
    switch (dist) {
      case SPRINT:
        this.swim = 750;
        this.bike = 12.1;
        this.run = 3.1;
        break;
      case OLYMPIC:
        this.swim = 1500;
        this.bike = 12.1;
        this.run = 6.2;
        break;
      case HALFIRON:
        this.swim = 1931.2;
        this.bike = 56;
        this.run = 13.1;
        break;
      case IRON:
        this.swim = 3862.4;
        this.bike = 112;
        this.run = 26.2;
        break;
      default:
        break;
    }
  }

  /**
   * TriathlonDistance constructor.
   * 
   * @param swim swim
   * @param bike bike
   * @param run run
   */
  public TriathlonDistance(double swim, double bike, double run) {
    this.swim = swim;
    this.bike = bike;
    this.run = run;
  }

  public TriathlonDistance() {
    this.swim = 0;
    this.bike = 0;
    this.run = 0;
  }

  public double getSwim() {
    return swim;
  }

  public void setSwim(double swim) {
    this.swim = swim;
  }

  public double getBike() {
    return bike;
  }

  public void setBike(double bike) {
    this.bike = bike;
  }

  public double getRun() {
    return run;
  }

  public void setRun(double run) {
    this.run = run;
  }


  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj.getClass() != TriathlonDistance.class) {
      return false;
    }
    TriathlonDistance triDist = (TriathlonDistance) obj;
    double epsilon = 0.0001;
    return ((swim - triDist.getSwim()) < epsilon) && ((bike - triDist.getBike()) < epsilon)
        && ((run - triDist.getRun()) < epsilon);
  }

  @Override
  public int hashCode() {
    return 1;
  }
}
