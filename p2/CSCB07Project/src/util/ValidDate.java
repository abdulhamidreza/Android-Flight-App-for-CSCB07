/**
 * 
 */
package util;

import flightServices.InvalidDateException;

/**
 * @author lucsteph
 *
 */
public class ValidDate {
	public static boolean validate(String date) throws InvalidDateException{
		String[] splitDate = date.split(" ");
		String[] day = splitDate[0].split("-");
		String[] time = splitDate[1].split(":");
		
		if (!(Integer.parseInt(day[1]) <= 12 
				&& Integer.parseInt(day[1]) >= 1)){
			throw new InvalidDateException("Not a valid month");
		}
		if (Integer.parseInt(day[0])%4 == 0
				&& Integer.parseInt(day[1]) == 2 
				&& !(Integer.parseInt(day[2]) >= 0
				&& Integer.parseInt(day[2]) <= 29)){
			throw new InvalidDateException("Not a valid leap year");
		} else if (Integer.parseInt(day[1]) == 2 
				&& !(Integer.parseInt(day[2]) >= 0
				&& Integer.parseInt(day[2]) <= 28)) {
			throw new InvalidDateException("Not a valid feb");
		} else if (!(Integer.parseInt(day[2]) >= 0
				&& Integer.parseInt(day[2]) <= 31)) {
			throw new InvalidDateException("Not a valid day");
		}	
		
		if (!(Integer.parseInt(time[0]) <= 24 
				&& Integer.parseInt(time[0]) >= 0)){
			throw new InvalidDateException("Not a valid time");
		}
		if (!(Integer.parseInt(time[1]) <= 60 
				&& Integer.parseInt(time[1]) >= 0)){
			throw new InvalidDateException("Not a valid minute");
		}
		
		return true;
	}
}
