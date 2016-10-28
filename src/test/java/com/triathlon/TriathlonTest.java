package com.triathlon;

import static org.junit.Assert.*;

import org.junit.Test;

import com.triathlon.Triathlon.Weather;
import java.util.Date;

public class TriathlonTest {

	@Test
	public void testSetWeather() {
		Triathlon triath1 = new Triathlon(Weather.SUNNY, 0, new Date(), 0, 0, 0);
		assertEquals(Weather.SUNNY, triath1.getWeather());
		triath1.setWeather(Weather.WINDY);
		assertEquals(Weather.WINDY, triath1.getWeather());
	}
	
	@Test
	public void testSetElevationChange() {
		Triathlon triath1 = new Triathlon(Weather.SUNNY, 1.2, new Date(), 0, 0, 0);
		assertEquals(1.2, triath1.getElevationChange(), 0);
		triath1.setElevationChange(2.3);
		assertEquals(2.3, triath1.getElevationChange(), 0);
	}

	@Test
	public void testSetDate() {
		Triathlon triath1 = new Triathlon(Weather.SUNNY, 0, new Date(), 0, 0, 0);
		assertEquals(new Date(), triath1.getDate());
		triath1.setDate(new Date(2016, 10, 20));
		assertEquals(new Date(2016, 10, 20), triath1.getDate());
	}
	
	@Test
	public void testSetDistance() {
		Triathlon triath1 = new Triathlon(Weather.SUNNY, 0, new Date(), 7.0, 0, 0);
		assertEquals(7.0, triath1.getDistance(), 0);
		triath1.setDistance(7.45);
		assertEquals(7.45, triath1.getDistance(), 0);
	}

	@Test
	public void testSetLatitude() {
		Triathlon triath1 = new Triathlon(Weather.SUNNY, 0, new Date(), 0, 0.4, 0);
		assertEquals(0.4, triath1.getLatitude(), 0);
		triath1.setLatitude(1.23);
		assertEquals(1.23, triath1.getLatitude(), 0);
	}
	
	@Test
	public void testSetLongitude() {
		Triathlon triath1 = new Triathlon(Weather.SUNNY, 0, new Date(), 0, 0, 0.04);
		assertEquals(0.04, triath1.getLongitude(), 0);
		triath1.setLongitude(5000.23);
		assertEquals(5000.23, triath1.getLongitude(), 0);
	}



}
