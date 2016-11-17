/**
 * 
 */
package util;

import java.util.Arrays;
import java.util.List;

import flightServices.InvalidDateException;

/**
 * @author lucsteph
 *
 */
public class ValidDate {
	public static final List<Integer> daysInMonth = Arrays.asList(31,28,31,30,31,30,31,31,30,31,30,31,29);
	public static boolean validate(String date) throws InvalidDateException{
		String[] splitDate = date.split(" ");
		String[] day = splitDate[0].split("-");
		String[] time = splitDate[1].split(":");
		
		String message = date + " is not a valid %s";				
		
		if (!(Integer.parseInt(time[0]) <= 24 
				&& Integer.parseInt(time[0]) >= 0)){
			throw new InvalidDateException(time[0] + " is not a valid hour");
		}
		if (!(Integer.parseInt(time[1]) <= 60 
				&& Integer.parseInt(time[1]) >= 0)){
			throw new InvalidDateException(time[1] + " is not a valid minute");
		}
		
		if (!(Integer.parseInt(day[1]) <= 12 
				&& Integer.parseInt(day[1]) >= 1)){
			throw new InvalidDateException(String.format(message, "month"));
		}
		if (Integer.parseInt(day[0])%4 == 0
				&& Integer.parseInt(day[1]) == 02
				&& Integer.parseInt(day[2]) >= 1
				&& Integer.parseInt(day[2]) <= daysInMonth.get(12)){
			return true;
		} else if (!(Integer.parseInt(day[2]) >= 1
				&& Integer.parseInt(day[2]) <= daysInMonth.get(Integer.parseInt(day[1]) - 1))) {
			throw new InvalidDateException(String.format(message, "day in the month"));
		}			
		
		return true;
	}
}
