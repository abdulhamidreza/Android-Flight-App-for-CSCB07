/**
 * 
 */
package FlightServices;

import java.text.ParseException;
import java.util.List;

import itinerary.Itinerary;

/**
 * @author lucsteph
 *
 */
public interface FlightService {
	public List<?> getItinerary(String origin, String deestination, String departDate) throws ParseException;
	public void sortFlight(List<Itinerary> itinerary, String sortBy, boolean increasing);
}
