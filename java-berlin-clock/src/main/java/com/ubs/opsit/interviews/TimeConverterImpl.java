/**
 * 
 */
package com.ubs.opsit.interviews;

/**
 * @author Avinash More
 * 
 * Service class providing implementation for methods to get Berlin time from the given standard time. 
 *
 */
public class TimeConverterImpl implements TimeConverter {
	
	// Ideally should be configured in an external property file.
	private static final int TOTAL_LAMPS_HOUR_FIRST_ROW=4;
	private static final int TOTAL_LAMPS_HOUR_SECOND_ROW=4;
	private static final int TOTAL_LAMPS_MINUTE_FIRST_ROW=11;
	private static final int TOTAL_LAMPS_MINUTE_SECOND_ROW=4;
	private static final String RED_LAMP_ON="R";
	private static final String YELLOW_LAMP_ON="Y";
	private static final String LAMP_OFF="O";
	private static final String FIRST_QUARTER="first";
	private static final String HALF_QUARTER="half";
	private static final String LAST_QUARTER="last";
	private static final String UNDEFINED = "undefined";

	/* (non-Javadoc)
	 * @see com.ubs.opsit.interviews.TimeConverter#convertTime(java.lang.String)
	 */
	@Override
	public String convertTime(String aTime) {
		// Get the status of top lamp
		StringBuffer berlineTimeString = new StringBuffer();
		berlineTimeString
			.append(calculateStatusOfTopLamp(aTime))// get the top lamp status
			.append("\r\n")
			.append(calculateStatusOfFirstRowHourLamps(aTime)) // get the status of hour lamps in first row
			.append("\r\n")
			.append(calculateStatusOfSecondRowHourLamps(aTime))// get the status of hour lamps in the second row
			.append("\r\n")
			.append(calculateStatusOfFirstRowMinuteLamps(aTime)) // get the status of minute lamps in the first row.
			.append("\r\n")
			.append(calculateStatusOfSecondRowMinuteLamps(aTime));// get the status of minute lamps in the second row
		return berlineTimeString.toString();
	}

	/**
	 * Method to get top lamp status in Berlin clock lamps
	 * @param aTime
	 * @return topLampStatus
	 */
	public String calculateStatusOfTopLamp(String aTime){
		int totalSeconds = getHoursFromTimeString(aTime) * 3600 + getMinutesFromTimeString(aTime) * 60
				+ getSecondsFromTimeString(aTime);
		return (totalSeconds%2==0)?YELLOW_LAMP_ON:LAMP_OFF;
		
	}

	/**
	 * Method to get hour lamps status for first row status of Berlin clock 
	 * @param aTime
	 * @return hour lamps status of first row
	 */
	public String calculateStatusOfFirstRowHourLamps(String aTime) {
		int hours = getHoursFromTimeString(aTime);
		int totalRedLampstoSwitchON = hours/5;
		StringBuffer lampStatusString = new StringBuffer(); 
		for(int i=0;i<TOTAL_LAMPS_HOUR_FIRST_ROW;i++){
			if(i<totalRedLampstoSwitchON){
				lampStatusString.append(RED_LAMP_ON);
			}
			else{
				lampStatusString.append(LAMP_OFF);
			}
		}
		return lampStatusString.toString();
	}

	/**
	 * Method to get hour lamp status for second row of Berlin clock
	 * @param time
	 * @return hour lamp status for second row
	 */
	public String calculateStatusOfSecondRowHourLamps(String aTime) {
		int hours = getHoursFromTimeString(aTime);
		int totalYellowLamps = hours%5;
		StringBuffer lampStatusString = new StringBuffer(); 
		for(int i=0;i<TOTAL_LAMPS_HOUR_SECOND_ROW;i++){
			if(i<totalYellowLamps){
				lampStatusString.append(RED_LAMP_ON);
			}
			else{
				lampStatusString.append(LAMP_OFF);
			}
		}
		return lampStatusString.toString();
	}

	/**
	 * Method to get minute lamps status of first row of Berlin clock
	 * @param time
	 * @return minute lamp status for first row
	 */
	public String calculateStatusOfFirstRowMinuteLamps(String aTime) {
		int minutes = getMinutesFromTimeString(aTime);
		int totalLampsToSwitchON = minutes/5;
		int totalRedLampsToSwitchON = minutes/15;
		StringBuffer lampStatusString = new StringBuffer();
		int redLampCount=0;
		for(int i=1; i<=TOTAL_LAMPS_MINUTE_FIRST_ROW ; i++){
			if((i==3 ||i==6||i==9) && redLampCount < totalRedLampsToSwitchON){
				lampStatusString.append(RED_LAMP_ON);
				redLampCount++;
			}
			else if(i<=totalLampsToSwitchON){
				lampStatusString.append(YELLOW_LAMP_ON);
			}
			else{
				lampStatusString.append(LAMP_OFF);
			}
		}
		
		return lampStatusString.toString();
	}

	
	/**
	 * Method to get minute lamps status for first second row in Berlin clock
	 * @param time
	 * @return minute lamps status of second row
	 */
	public String calculateStatusOfSecondRowMinuteLamps(String aTime) {
		int minutes = getMinutesFromTimeString(aTime);
		int totalLampsToSwitchON = minutes%5;
		StringBuffer lampStatusString = new StringBuffer();
		for(int i=0 ; i<TOTAL_LAMPS_MINUTE_SECOND_ROW;i++){
			if(i<totalLampsToSwitchON){
				lampStatusString.append(YELLOW_LAMP_ON);
			}
			else{
				lampStatusString.append(LAMP_OFF);
			}
		}
		return lampStatusString.toString();
	}

	/**
	 * Method to get which quarter of hour does the given time represent in Berlin clock
	 * @param time
	 * @return string representing the quarter name
	 */
	public String calculateQuarterofHour(String aTime) {
		int minutes = getMinutesFromTimeString(aTime);
		int totalRedLampsToSwitchON = minutes/15;
		String timeQuarter = null;
		switch(totalRedLampsToSwitchON){
			case 1 : timeQuarter=FIRST_QUARTER;
					 break;
			case 2 : timeQuarter=HALF_QUARTER;
			 		 break;
			case 3 : timeQuarter=LAST_QUARTER;
			 		 break;
			default : timeQuarter=UNDEFINED;
		}
		return timeQuarter;
	}
	
	/**
	 * @param timeString
	 * @return
	 */
	private int getHoursFromTimeString(String timeString){
		String[] parsedTimeString = timeString.split(":");
		int hours=0;
		if(parsedTimeString!=null & parsedTimeString.length>=1){
			hours=Integer.parseInt(parsedTimeString[0]);
		}
		return hours;
	}
	
	/**
	 * @param timeString
	 * @return
	 */
	private int getMinutesFromTimeString(String timeString){
		String[] parsedTimeString = timeString.split(":");
		int minutes=0;
		if(parsedTimeString!=null & parsedTimeString.length>=2){
			minutes=Integer.parseInt(parsedTimeString[1]);
		}
		return minutes;
	}
	
	/**
	 * @param timeString
	 * @return
	 */
	private int getSecondsFromTimeString(String timeString){
		String[] parsedTimeString = timeString.split(":");
		int seconds=0;
		if(parsedTimeString!=null & parsedTimeString.length>1){
			seconds=Integer.parseInt(parsedTimeString[0]);
		}
		return seconds;
	}
		
}
