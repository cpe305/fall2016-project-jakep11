package model;

import java.util.ArrayList;


public class Estimator {

  /**
   * estimateNewTriathlon estimates a new Triathlon.
   * @param previousTris previousTris
   * @param newTri newTri
   * @return returns a new estimated triathlon
   */
  public static Triathlon estimateNewTriathlon(ArrayList<Triathlon> previousTris,
      Triathlon newTri) {
    
    Stats.getAverageDistanceFromAllTris(previousTris);
    return new Triathlon();
  }
}
