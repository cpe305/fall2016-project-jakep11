package com.triathlon;

/** 
 * @author Jake Pickett
 * @author Sonia Narayanan
 * @version October 12, 2016
 */


public class TriathlonTime {
   
   private Time swimTime;
   private Time t1Time;
   private Time bikeTime;
   private Time t2Time;
   private Time runTime;

   public TriathlonTime(Time swim, Time t1, Time bike, Time t2, Time run) {
      this.swimTime = swim;
      this.t1Time = t1;
      this.bikeTime = bike;
      this.t2Time = t2;
      this.runTime = run;
   }

   public Time getSwimTime() {
      return swimTime;
   }
   
   public void setSwimTime(Time newSwimTime) {
      this.swimTime = newSwimTime;
   }

   public Time getT1Time() {
      return t1Time;
   }

   public void setT1Time(Time newT1Time) {
      this.t1Time = newT1Time;
   }

   public Time getBikeTime() {
      return bikeTime;
   }

   public void setBikeTime(Time newBikeTime) {
      this.bikeTime = newBikeTime;
   }
   
   public Time getT2Time() {
      return t2Time;
   }

   public void setT2Time(Time newT2Time) {
      this.t2Time = newT2Time;
   }

   public Time getRunTime() {
      return runTime;
   }

   public void setRunTime(Time newRunTime) {
      this.runTime = newRunTime;
   }

   public int getTotalTimeInSeconds() {
      return swimTime.getTimeInSeconds() + t1Time.getTimeInSeconds() + bikeTime.getTimeInSeconds() 
      		+ t2Time.getTimeInSeconds() + runTime.getTimeInSeconds();
   }
}




