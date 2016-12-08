package model;

import model.Triathlon.WeatherConditions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public final class Estimator {

  // Suppress default constructor for noninstantiability
  private Estimator() {
    throw new AssertionError();
  }

  /**
   * estimateNewTriathlon estimates a new Triathlon.
   * 
   * @param previousTris previousTris
   * @param newTri newTri
   * @return returns a new estimated triathlon
   */
  public static Triathlon estimateNewTriathlon(List<Triathlon> previousTris, Triathlon newTri) {

    ArrayList<Triathlon> previousTrisArrayList = new ArrayList<>(previousTris);
    Triathlon avgTriPace = Stats.getAveragePaceFromAllTris(previousTrisArrayList);

    double swimPace =
        avgTriPace.getDistance().getSwim() / avgTriPace.getTime().getSwimTime().getTimeInSeconds();
    double bikePace =
        avgTriPace.getDistance().getBike() / avgTriPace.getTime().getBikeTime().getTimeInSeconds();
    double runPace =
        avgTriPace.getDistance().getRun() / avgTriPace.getTime().getRunTime().getTimeInSeconds();


    scaleForElevation(newTri.getElevation(), newTri);

    double newSwimTime = newTri.getDistance().getSwim() / swimPace;
    double newBikeTime = newTri.getDistance().getBike() / bikePace;
    double newRunTime = newTri.getDistance().getRun() / runPace;

    newTri.setTime(new TriathlonTime(new Time((int) newSwimTime),
        Stats.getAverageT1Time(previousTrisArrayList), new Time((int) newBikeTime),
        Stats.getAverageT2Time(previousTrisArrayList), new Time((int) newRunTime)));

    scaleForWeather(newTri.getWeather(), newTri);
    scaleForTemperature(newTri.getTemperature(), newTri);
    scaleForStartTime(newTri.getStartTime(), newTri);

    return newTri;
  }

  public static Triathlon scaleForStartTime(String startTime, Triathlon tri) {
    String earlyDateString = "6:59 AM";
    String lateDateString = "10:01 AM";
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm aa");
    double scalar = 1;
    try {
      Date date = sdf.parse(startTime);
      Date date1 = sdf.parse(earlyDateString);
      Date date2 = sdf.parse(lateDateString);


      if (date.compareTo(date1) < 0) {
        scalar = 1.05;
      } else if (date.compareTo(date2) > 0) {
        scalar = 1.03;
      }
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }



    tri.setTime(scaleAllTimes(scalar, tri.getTime()));
    return tri;
  }

  public static Triathlon scaleForTemperature(double temperature, Triathlon tri) {
    if (temperature < 60) {
      double scalar = 1 + Math.abs(temperature - 60) / 300.0;
      tri.setTime(scaleAllTimes(scalar, tri.getTime()));
    } else if (temperature > 75) {
      double scalar = 1 + Math.abs(temperature - 75) / 300.0;
      tri.setTime(scaleAllTimes(scalar, tri.getTime()));
      tri.setTime(scaleSwimTime(1 / scalar, tri.getTime()));
    }
    return tri;
  }

  public static Triathlon scaleForWeather(WeatherConditions weather, Triathlon tri) {
    switch (weather) {
      case DRY:
        tri.setTime(scaleRunTime(1.1, tri.getTime()));
        break;
      case LIGHT_RAIN:
        tri.setTime(scaleAllTimes(1.05, tri.getTime()));
        break;
      case HEAVY_RAIN:
        tri.setTime(scaleAllTimes(1.2, tri.getTime()));
        break;
      case WINDY:
        tri.setTime(scaleAllTimes(1.1, tri.getTime()));
        tri.setTime(scaleBikeTime(1.1, tri.getTime()));
        break;
      case HUMID:
        tri.setTime(scaleBikeTime(1.05, tri.getTime()));
        tri.setTime(scaleRunTime(1.1, tri.getTime()));
        break;
      case SUNNY:
      default:
        break;
    }
    return tri;
  }

  public static Triathlon scaleForElevation(TriathlonElevation triElev, Triathlon tri) {
    double bikeScalar = triElev.getBikeElevation() / 500.0;
    double runScalar = triElev.getRunElevation() / 400.0;
    TriathlonDistance triDist = tri.getDistance();
    triDist.setBike(tri.getDistance().getBike() + bikeScalar);
    triDist.setRun(tri.getDistance().getRun() + runScalar);
    tri.setDistance(triDist);

    return tri;
  }

  public static TriathlonTime scaleAllTimes(double scalar, TriathlonTime triTime) {
    Time newBikeTime = new Time((int) (triTime.getBikeTime().getTimeInSeconds() * scalar));
    Time newRunTime = new Time((int) (triTime.getRunTime().getTimeInSeconds() * scalar));
    Time newSwimTime = new Time((int) (triTime.getSwimTime().getTimeInSeconds() * scalar));


    triTime.setBikeTime(newBikeTime);
    triTime.setRunTime(newRunTime);
    triTime.setSwimTime(newSwimTime);
    return triTime;
  }

  public static TriathlonTime scaleBikeTime(double scalar, TriathlonTime triTime) {
    Time newTime = new Time((int) (triTime.getBikeTime().getTimeInSeconds() * scalar));
    triTime.setBikeTime(newTime);
    return triTime;
  }

  public static TriathlonTime scaleRunTime(double scalar, TriathlonTime triTime) {
    Time newTime = new Time((int) (triTime.getRunTime().getTimeInSeconds() * scalar));
    triTime.setRunTime(newTime);
    return triTime;
  }

  public static TriathlonTime scaleSwimTime(double scalar, TriathlonTime triTime) {
    Time newTime = new Time((int) (triTime.getSwimTime().getTimeInSeconds() * scalar));
    triTime.setSwimTime(newTime);
    return triTime;
  }
}
