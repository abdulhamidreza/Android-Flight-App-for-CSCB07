/**
 * 
 */
package FlightServices;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import Flight.Flight;
import driver.Driver;

/**
 * @author lucsteph
 *
 */
public class FlightManager implements FlightService {

	private Map<String, List<Flight>> allFlights;

  private static DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	
	
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
	public List<?> getItinerary(String origin, String destination, String departureDate) throws ParseException {
		Stack<String> location = new Stack<String>();
		Stack<Flight> connection = new Stack<Flight>();
		List<Stack<Flight>> paths = new ArrayList<Stack<Flight>>();
		
		Calendar wantedDepartDate = new GregorianCalendar();
		wantedDepartDate.setTime(date.parse(departureDate));
		int departYear = wantedDepartDate.get(Calendar.YEAR);
		int departMonth = wantedDepartDate.get(Calendar.MONTH);
		int departDate = wantedDepartDate.get(Calendar.DATE);		
		
		location.push(origin);
		
		for (Flight flight: allFlights.get(origin)) {
		  // Confirms departure dates are the same
			if (flight.getDepartureDate().get(Calendar.YEAR) == departYear
			    && flight.getDepartureDate().get(Calendar.MONTH) == departMonth
			    && flight.getDepartureDate().get(Calendar.DATE) == departDate) {
				if(flight.getDestination().equals(destination)) {
					Stack<Flight> temp = new Stack<Flight>();
					temp.push(flight);
					paths.add(temp);
				} else {
					connection.push(flight);
					getFlights(flight, destination, location, connection, paths);
					connection.pop();
				}
			}
		}
		
		return paths;
	}
	
	private void getFlights(Flight flight, String destination, 
			Stack<String> location, Stack<Flight> connection, List<Stack<Flight>> paths) {
		for (Flight nextFlight: allFlights.get(flight.getDestination())){
			if (flight.timeBetweenFlights(nextFlight).compareTo(Driver.MIN_LAYOVER) >= 0
					&& flight.timeBetweenFlights(nextFlight).compareTo(Driver.MAX_LAYOVER) <= 0) {
				if(nextFlight.getDestination().equals(destination)) {
					Stack<Flight> temp = new Stack<Flight>();
					for(Flight flights: connection) {
						temp.push(flights);
					}
					temp.push(flight);
					paths.add(temp);
				} else if (!location.contains(nextFlight.getOrigin())) {
					location.push(nextFlight.getOrigin());
					connection.push(nextFlight);
					getFlights(nextFlight, destination, location, connection, paths);
					location.pop();
					connection.pop();
				}
			}
		}
	}
	/* (non-Javadoc)
	 * @see FlightServices.FlightService#sortFlight(java.util.List, java.lang.String)
	 */
	@Override
	public void sortFlight(List<?> itinerary, String sortBy) {
		// TODO Auto-generated method stub

	}

}
