package model;

public class TriathlonElevation {
  private double bikeElevation;
  private double runElevation;
  
  public TriathlonElevation(double bike, double run) {
    this.bikeElevation = bike;
    this.runElevation = run;
  }

  public double getBikeElevation() {
    return bikeElevation;
  }

  public void setBikeElevation(double bikeElevation) {
    this.bikeElevation = bikeElevation;
  }

  public double getRunElevation() {
    return runElevation;
  }

  public void setRunElevation(double runElevation) {
    this.runElevation = runElevation;
  }
}
