/**
 * 
 */
package FlightServices;

import java.util.Date;
import java.util.List;

/**
 * @author lucsteph
 *
 */
public interface FlightService {
	public List<?> getItinerary(String origin, String deestination, Date departDate);
	public void sortFlight(List<?> itinerary, String sortBy);
}
