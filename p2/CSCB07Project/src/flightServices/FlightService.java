/**
 * 
 */
package flightServices;

import java.text.ParseException;
import java.util.List;

import itinerary.Itinerary;

/**
 * @author lucsteph
 *
 */
public interface FlightService {
	public List<Itinerary> getItinerary(String origin, String deestination, String departDate) throws ParseException, InvalidSortException;
	public void sortFlight(List<Itinerary> itinerary, String sortBy, boolean increasingOrder) throws InvalidSortException;
}
