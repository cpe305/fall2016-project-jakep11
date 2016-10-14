package com.triathlon;


public class Time {

   private int hour;
   private int minute;
   private int second;

   public Time(int hour, int minute, int second) {
	   this.hour = hour;
	   this.minute = minute;
	   this.second = second;
   }
   
   public void setTime(int newHour, int newMinute, int newSecond) {
      hour = newHour;
      minute = newMinute;
      second = newSecond;
   }

   public int getTimeInSeconds() {
      return 3600 * hour + 60 * minute + second;
   }
}
