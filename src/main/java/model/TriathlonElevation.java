package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TriathlonElevation {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private double bikeElevation;
  private double runElevation;
  
  public TriathlonElevation(double bike, double run) {
    this.bikeElevation = bike;
    this.runElevation = run;
  }
  
  public TriathlonElevation() {
    this.bikeElevation = 0;
    this.runElevation = 0;
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
