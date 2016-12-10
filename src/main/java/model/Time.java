package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class that models elapsed time. The elapsed time is stored in hours, minutes, and seconds. The
 * default Java Time class included unnecessary overhead for storing elapsed time instead of time of
 * day.
 * 
 * @author Jake Pickett
 *
 */
@Entity
public class Time {

  static final int SEC_PER_HOUR = 3600;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private int hour;
  private int minute;
  private int second;

  /**
   * Time constructor.
   * 
   * @param hour a
   * @param minute a
   * @param second a
   */
  public Time(int hour, int minute, int second) {
    this.hour = hour;
    this.minute = minute;
    this.second = second;
  }

  /**
   * Time default constructor.
   */
  public Time() {
    this.hour = 0;
    this.minute = 0;
    this.second = 0;
  }

  /**
   * Time constructor.
   * 
   * @param second time in seconds
   */
  public Time(int second) {
    this.hour = second / SEC_PER_HOUR;
    this.minute = (second % SEC_PER_HOUR) / 60;
    this.second = second % 60;
  }

  /**
   * setTime method.
   * 
   * @param newHour a
   * @param newMinute a
   * @param newSecond a
   */
  public void setTime(int newHour, int newMinute, int newSecond) {
    getTimeInSeconds();
    hour = newHour % 24 + newMinute / 60 + newSecond / SEC_PER_HOUR;
    minute = newMinute % 60 + newSecond / 60;
    second = newSecond % 60;
  }

  /**
   * setTime method.
   * 
   * @param second a
   */
  public void setTime(int second) {
    this.hour = second / SEC_PER_HOUR;
    this.minute = (second / 60) % 60;
    this.second = second % 60;
  }

  public int getTimeInSeconds() {
    return SEC_PER_HOUR * hour + 60 * minute + second;
  }

  public double getTimeInMinutes() {
    return 60 * hour + minute + second / 60.0;
  }

  public double getTimeInHours() {
    return hour + minute / 60.0 + second / (double) SEC_PER_HOUR;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj.getClass() != Time.class) {
      return false;
    }
    Time time = (Time) obj;

    return time.getTimeInSeconds() == getTimeInSeconds();

  }

  @Override
  public String toString() {
    return Integer.toString(getTimeInSeconds());
  }

  @Override
  public int hashCode() {
    return 1;
  }
}
