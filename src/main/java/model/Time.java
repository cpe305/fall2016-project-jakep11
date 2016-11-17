package model;


public class Time {

  private int hour;
  private int minute;
  private int second;

  /**
   * Time constructor.
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
   * Time constructor.
   * @param second time in seconds
   */
  public Time(int second) {
    this.hour = second / 3600;
    this.minute = second / 60;
    this.second = second % 60;
  }

  /**
   * setTime method.
   * @param newHour a
   * @param newMinute a
   * @param newSecond a
   */
  public void setTime(int newHour, int newMinute, int newSecond) {
    getTimeInSeconds();
    hour = newHour % 24 + newMinute / 60 + newSecond / 3600;
    minute = newMinute % 60 + newSecond / 60;
    second = newSecond % 60;
  }
  
  public void setTime(int second) {
    this.hour = second / 3600;
    this.minute = (second / 60) % 60;
    this.second = second % 60;
  }

  public int getTimeInSeconds() {
    return 3600 * hour + 60 * minute + second;
  }
  
  public double getTimeInMinutes() {
    return 60 * hour + minute + second / 60.0;
  }
  
  public double getTimeInHours() {
    return hour + minute / 60.0 + second / 3600.0;
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
    return "" + getTimeInSeconds();
  }
}
