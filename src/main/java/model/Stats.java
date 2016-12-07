package model;

import java.util.ArrayList;
import java.util.List;


public final class Stats {

  private Stats() {
    throw new AssertionError();
  }

  public static double bikeSpeed(Triathlon tri) {
    return tri.getDistance().getBike() / tri.getTime().getBikeTime().getTimeInHours();
  }

  public static double runSpeed(Triathlon tri) {
    return tri.getTime().getRunTime().getTimeInMinutes() / tri.getDistance().getRun();
  }

  public static double swimPace(Triathlon tri) {
    return tri.getTime().getSwimTime().getTimeInMinutes() / tri.getDistance().getSwim() * 100;
  }

  public static double getAverageSwimDistance(List<Triathlon> triathlons) {
    double tempSwimD = 0;
    double count = 0;

    for (Triathlon t : triathlons) {
      tempSwimD += t.getDistance().getSwim();
      count++;
    }
    return tempSwimD / count;
  }

  public static double getAverageBikeDistance(List<Triathlon> triathlons) {
    double tempBikeD = 0;
    double count = 0;

    for (Triathlon t : triathlons) {
      tempBikeD += t.getDistance().getBike();
      count++;
    }
    return tempBikeD / count;
  }

  public static double getAverageRunDistance(List<Triathlon> triathlons) {
    double tempRunD = 0;
    double count = 0;

    for (Triathlon t : triathlons) {
      tempRunD += t.getDistance().getRun();
      count++;
    }
    return tempRunD / count;
  }

  public static TriathlonDistance getAverageDistanceFromAllTris(List<Triathlon> triathlons) {
    return new TriathlonDistance(getAverageSwimDistance(triathlons),
        getAverageBikeDistance(triathlons), getAverageRunDistance(triathlons));
  }

  public static Time getAverageSwimTime(List<Triathlon> triathlons) {
    Time swimT = new Time(0);
    double count = 0;

    for (Triathlon t : triathlons) {
      if (t.getTime() == null) {
        continue;
      }
      swimT.setTime(t.getTime().getSwimTime().getTimeInSeconds() + swimT.getTimeInSeconds());
      count++;
    }

    swimT.setTime(swimT.getTimeInSeconds() / (int) count);
    return swimT;
  }

  public static Time getAverageT1Time(List<Triathlon> triathlons) {
    Time t1T = new Time(0);
    double count = 0;

    for (Triathlon t : triathlons) {
      t1T.setTime(t.getTime().getT1Time().getTimeInSeconds() + t1T.getTimeInSeconds());
      count++;
    }

    t1T.setTime(t1T.getTimeInSeconds() / (int) count);
    return t1T;
  }

  public static Time getAverageBikeTime(List<Triathlon> triathlons) {
    Time bikeT = new Time(0);
    double count = 0;

    for (Triathlon t : triathlons) {
      if (t.getTime() == null) {
        continue;
      }
      bikeT.setTime(t.getTime().getBikeTime().getTimeInSeconds() + bikeT.getTimeInSeconds());

      count++;
    }

    bikeT.setTime(bikeT.getTimeInSeconds() / (int) count);
    return bikeT;
  }

  public static Time getAverageT2Time(List<Triathlon> triathlons) {
    Time t2T = new Time(0);
    double count = 0;

    for (Triathlon t : triathlons) {
      t2T.setTime(t.getTime().getT2Time().getTimeInSeconds() + t2T.getTimeInSeconds());
      count++;
    }

    t2T.setTime(t2T.getTimeInSeconds() / (int) count);
    return t2T;
  }

  public static Time getAverageRunTime(List<Triathlon> triathlons) {
    Time runT = new Time(0);
    double count = 0;

    for (Triathlon t : triathlons) {
      if (t.getTime() == null) {
        continue;
      }
      runT.setTime(t.getTime().getRunTime().getTimeInSeconds() + runT.getTimeInSeconds());
      count++;
    }

    runT.setTime(runT.getTimeInSeconds() / (int) count);
    return runT;
  }

  public static TriathlonTime getAverageTimeFromAllTris(List<Triathlon> triathlons) {

    return new TriathlonTime(getAverageSwimTime(triathlons), getAverageT1Time(triathlons),
        getAverageBikeTime(triathlons), getAverageT2Time(triathlons),
        getAverageRunTime(triathlons));
  }

  public static Triathlon getAveragePaceFromAllTris(List<Triathlon> triathlons) {
    Triathlon newTri = new Triathlon();

    ArrayList<Triathlon> triathlonArrayList = new ArrayList<>(triathlons);
    newTri.setDistance(getAverageDistanceFromAllTris(triathlonArrayList));
    newTri.setTime(getAverageTimeFromAllTris(triathlons));

    return newTri;
  }
}

