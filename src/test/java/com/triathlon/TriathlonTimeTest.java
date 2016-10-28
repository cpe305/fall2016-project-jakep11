package com.triathlon;

import static org.junit.Assert.*;

import org.junit.Test;

public class TriathlonTimeTest {

	@Test
	public void testGetTotalTimeInSeconds() {
		TriathlonTime triathtime1 = new TriathlonTime(new Time(0, 0, 2), new Time(0, 0, 3),
				new Time(0, 0, 4), new Time(0, 5, 0), new Time(1, 0, 0));
		assertEquals(3909, triathtime1.getTotalTimeInSeconds());
	}

}
