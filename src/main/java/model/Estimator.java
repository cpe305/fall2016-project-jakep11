package model;

import java.util.ArrayList;

public class Estimator {

  public static Triathlon estimateNewTriathlon(ArrayList<Triathlon> previousTris,
      Triathlon newTri) {
    
    Stats.getAverageDistanceFromAllTris(previousTris);
    return new Triathlon();
  }
}
