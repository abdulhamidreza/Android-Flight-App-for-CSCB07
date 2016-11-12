/**
 * 
 */
package FlightServices;

import java.text.ParseException;
import java.util.List;

/**
 * @author lucsteph
 *
 */
public interface FlightService {
	public List<?> getItinerary(String origin, String deestination, String departDate) throws ParseException;
	public void sortFlight(List<?> itinerary, String sortBy);
}
