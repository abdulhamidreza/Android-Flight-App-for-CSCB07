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

import Comparator.ItineraryCostComparator;
import Comparator.ItineraryTimeComparator;
import Flight.Flight;
import Itinerary.Itinerary;
import driver.Driver;

/**
 * @author lucsteph
 *
 */
public class FlightManager implements FlightService {

	private Map<String, List<Flight>> mapOfFlights;


  private static DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public FlightManager(List<Flight> flights) {
		mapOfFlights = new HashMap<String, List<Flight>>();
		for (Flight flight: flights) {
			if (mapOfFlights.containsKey(flight.getOrigin())) {
				mapOfFlights.get(flight.getOrigin()).add(flight);				
			} else {
				mapOfFlights.put(flight.getOrigin(), new ArrayList<Flight>());
				mapOfFlights.get(flight.getOrigin()).add(flight);	
			}
		}
/*		System.out.println(mapOfFlights.keySet());
		
		System.out.println(mapOfFlights);*/
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
		
		for (Flight flight: mapOfFlights.get(origin)) {
		  // Confirms departure dates are the same
			if (flight.getDepartureDate().get(Calendar.YEAR) == departYear
			    && flight.getDepartureDate().get(Calendar.MONTH) == departMonth
			    && flight.getDepartureDate().get(Calendar.DATE) == departDate) {
				if(flight.getDestination().equals(destination)) {
					Stack<Flight> temp = new Stack<Flight>();
					temp.push(flight);
					paths.add(temp);
				} else if (mapOfFlights.containsKey(flight.getDestination())){
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
		for (Flight nextFlight: mapOfFlights.get(flight.getDestination())){
		  /*System.out.println(flight.timeBetweenFlights(nextFlight).compareTo(Driver.MIN_LAYOVER));
		  System.out.println(flight.timeBetweenFlights(nextFlight).compareTo(Driver.MAX_LAYOVER));
			*/if (flight.timeBetweenFlights(nextFlight).compareTo(Driver.MIN_LAYOVER) >= 0
					&& flight.timeBetweenFlights(nextFlight).compareTo(Driver.MAX_LAYOVER) <= 0) {
				if(nextFlight.getDestination().equals(destination)) {
					Stack<Flight> temp = new Stack<Flight>();
					for(Flight flights: connection) {
						temp.push(flights);
					}
					temp.push(nextFlight);
					paths.add(temp);
				} else if (!location.contains(nextFlight.getOrigin())
				    && mapOfFlights.containsKey(nextFlight.getDestination())) {
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
	public void sortFlight(List<Itinerary> itineraries, String sortBy) {
		switch (sortBy) {
		  case "Time":
		    ItineraryTimeComparator itinTimeComp = new ItineraryTimeComparator();
		    itineraries.sort(itinTimeComp);
		    break;
		  case "Cost":
		    ItineraryCostComparator itinCostComp = new ItineraryCostComparator();
        itineraries.sort(itinCostComp);
        break;
      default:
        System.out.println(String.format("Was told to sort by %1$s", sortBy));
        break;        
		}
	}
}
