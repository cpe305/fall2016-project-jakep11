package model;

public class TriathlonDistance {
  private double swim;
  private double bike;
  private double run;
  
  public enum StandardDistance {
    SPRINT, OLYMPIC, HALFIRON, IRON
  }
  
  public TriathlonDistance (StandardDistance d) {
    switch (d) {
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
    }
  }
  
  public TriathlonDistance() {
    
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

  public TriathlonDistance(double swim, double bike, double run) {
    this.swim = swim;
    this.bike = bike;
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
    
    return swim == triDist.getSwim() && bike == triDist.getBike() && run == triDist.getRun();
  }
}
