/**
 * 
 */
package com.ubs.opsit.interviews;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Avinash More
 * This class represents a junit test cases for testing Berlin clock Time Converter service components. 
 *
 */

public class BerlinClockJunitTest {
	
	TimeConverterImpl timeConverterService;
	
	@Before
	public void init(){
		// Initialize timeConverter service
		timeConverterService = new TimeConverterImpl();
	}
	
	/**
	 * Test case to check status of top lamp of a Berlin clock at a given time 
	 */
	@Test
	public void testStatusOfTopLamp(){
		String time = "15:46:29";
		String expected = "O";
		assertEquals(expected,timeConverterService.calculateStatusOfTopLamp(time));
	}

	/**
	 * Test case to check status of top lamp of a Berlin clock at a given time 
	 */
	@Test
	public void testStatusOfTopLampWithBoundaryCondition(){
		String time = "24:00:00";
		String expected = "Y";
		assertEquals(expected,timeConverterService.calculateStatusOfTopLamp(time));
		time = "00:00:00";
		expected = "Y";
		assertEquals(expected,timeConverterService.calculateStatusOfTopLamp(time));
	}

	
	/**
	 * Test case to check status of hour lamps of the first row of Berlin clock at a give time 
	 */
	@Test
	public void testStatusOfFirstRowHourLamps(){
		String time = "15:46:29";
		String expected = "RRRO";
		assertEquals(expected,timeConverterService.calculateStatusOfFirstRowHourLamps(time));
	}
	
	/**
	 * Test case to check status of hour lamps of the first row of Berlin clock at a give time 
	 */
	@Test
	public void testStatusOfFirstRowHourLampsWithBoundaryCondition(){
		String time = "24:00:00";
		String expected = "RRRR";
		assertEquals(expected,timeConverterService.calculateStatusOfFirstRowHourLamps(time));
		time = "00:00:00";
		expected = "OOOO";
		assertEquals(expected,timeConverterService.calculateStatusOfFirstRowHourLamps(time));
	}
	
	/**
	 * Test case to check status of hour lamps of the second row of Berlin clock at a give time 
	 */
	@Test
	public void testStatusOfSecondRowHourLamps(){
		String time = "15:46:29";
		String expected = "OOOO";
		assertEquals(expected,timeConverterService.calculateStatusOfSecondRowHourLamps(time));
	}

	/**
	 * Test case to check status of hour lamps of the second row of Berlin clock at a give time 
	 */
	@Test
	public void testStatusOfSecondRowHourLampsWithBoundaryCondition(){
		String time = "24:00:00";
		String expected = "RRRR";
		assertEquals(expected,timeConverterService.calculateStatusOfSecondRowHourLamps(time));
		time = "00:00:00";
		expected = "OOOO";
		assertEquals(expected,timeConverterService.calculateStatusOfSecondRowHourLamps(time));

	}

	
	/**
	 * Test case to check status of minute lamps of the first row of Berlin clock at a give time 
	 */
	@Test
	public void testStatusOfFirstRowMinuteLamps(){
		String time = "15:46:29";
		String expected = "YYRYYRYYROO";
		assertEquals(expected,timeConverterService.calculateStatusOfFirstRowMinuteLamps(time));
	}

	/**
	 * Test case to check status of minute lamps of the first row of Berlin clock at a give time 
	 */
	@Test
	public void testStatusOfFirstRowMinuteLampsWithBoundaryCondition(){
		String time = "24:00:00";
		String expected = "OOOOOOOOOOO";
		assertEquals(expected,timeConverterService.calculateStatusOfFirstRowMinuteLamps(time));
		time = "00:00:00";
		expected = "OOOOOOOOOOO";
		assertEquals(expected,timeConverterService.calculateStatusOfFirstRowMinuteLamps(time));
	}


	/**
	 * Test case to check status of minute lamps of the first row of Berlin clock at a give time 
	 */
	@Test
	public void testStatusOfSecondRowMinuteLamps(){
		String time = "15:46:29";
		String expected = "YOOO";
		assertEquals(expected,timeConverterService.calculateStatusOfSecondRowMinuteLamps(time));
	}

	/**
	 * Test case to check status of minute lamps of the first row of Berlin clock at a give time 
	 */
	@Test
	public void testStatusOfSecondRowMinuteLampsWithBoundaryCondition(){
		String time = "24:00:00";
		String expected = "OOOO";
		assertEquals(expected,timeConverterService.calculateStatusOfSecondRowMinuteLamps(time));
		time = "00:00:00";
		expected = "OOOO";
		assertEquals(expected,timeConverterService.calculateStatusOfSecondRowMinuteLamps(time));
	}

	/**
	 * Test case to check the quarter of hour represented by Berlin clock at a give time 
	 */
	@Test
	public void testQuarterofHour(){
		String time = "15:46:29";
		String expected = "last";
		assertEquals(expected,timeConverterService.calculateQuarterofHour(time));
	}

	/**
	 * Test case to check the quarter of hour represented by Berlin clock at a give time 
	 */
	@Test
	public void testQuarterofHourWithBoundaryCondition(){
		String time = "24:00:00";
		String expected = "undefined";
		assertEquals(expected,timeConverterService.calculateQuarterofHour(time));
		time = "00:00:00";
		expected = "undefined";
		assertEquals(expected,timeConverterService.calculateQuarterofHour(time));
	}

}
