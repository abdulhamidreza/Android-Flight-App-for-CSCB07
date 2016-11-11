/**
 * 
 */
package FlightServices;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import Flight.Flight;

/**
 * @author lucsteph
 *
 */
public class FlightManager implements FlightService {

	private Map<String, List<Flight>> allFlights;
	
	
	public FlightManager(List<Flight> flights) {
		allFlights = new HashMap<String, List<Flight>>();
		for (Flight flight: flights) {
			if (allFlights.containsKey(flight.getOrigin())) {
				allFlights.get(flight.getOrigin()).add(flight);				
			} else {
				allFlights.put(flight.getOrigin(), new ArrayList<Flight>());
				allFlights.get(flight.getOrigin()).add(flight);	
			}
		}		
	}
	
	/* (non-Javadoc)
	 * @see FlightServices.FlightService#getItinerary(java.lang.String, java.lang.String, java.util.Date)
	 */
	@Override
	public List<?> getItinerary(String origin, String deestination, Date departDate) {
		Stack<String> location = new Stack<String>();
		Stack<Flight> connection = new Stack<Flight>();
		List<Stack<Flight>> paths = new ArrayList<Stack<Flight>>();
		location.push(origin);
		for (Flight flight: allFlights.get(origin)) {
			if (flight.getDepartureDate().equals(departDate)) { // change to compare departure dates
				// add in other if else statements and stuff then lead to recursive algo
			}
		}
		
		return paths;
	}

	/* (non-Javadoc)
	 * @see FlightServices.FlightService#sortFlight(java.util.List, java.lang.String)
	 */
	@Override
	public void sortFlight(List<?> itinerary, String sortBy) {
		// TODO Auto-generated method stub

	}

}
