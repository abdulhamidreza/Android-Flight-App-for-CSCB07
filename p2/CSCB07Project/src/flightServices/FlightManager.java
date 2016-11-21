package flightServices;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import itinerary.Itinerary;
import util.ItineraryCostComparator;
import util.ItineraryTimeComparator;
import driver.Driver;
import flight.Flight;

/**
 * Class that will manage the search and sorting algorithms for
 * <code>Flight</code> and <code>Itinerary</code>
 * 
 * @author lucsteph
 *
 */
public class FlightManager implements FlightService {

  // Map of flights with the key being the origin and value is a List of
  // <code>Flight</code> from that origin
  private Map<String, List<Flight>> mapOfFlights;
  private static DateFormat date = new SimpleDateFormat("yyyy-MM-dd");

  /**
   * Creates the <code>FlightManager</code>.
   * 
   * @param flights
   *          List of all <code>Flight</code> from database.
   */
  public FlightManager(List<Flight> flights) {
    mapOfFlights = new HashMap<String, List<Flight>>();
    for (Flight flight : flights) {
      if (!mapOfFlights.containsKey(flight.getOrigin())) {        
        mapOfFlights.put(flight.getOrigin(), new ArrayList<Flight>());        
      }
      mapOfFlights.get(flight.getOrigin()).add(flight);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see FlightServices.FlightService#getItinerary(java.lang.String,
   * java.lang.String, java.util.Date)
   */
  @Override
  public List<Itinerary> getItinerary(String origin, String destination, String departureDate)
      throws ParseException {
    Stack<String> location = new Stack<String>(); // all visited cities
    Stack<Flight> connection = new Stack<Flight>(); // all connection
                                                    // <code>Flight</code>
    // List of all valid <code>Itinerary</code>
    List<Itinerary> allItineraries = new ArrayList<Itinerary>();

    location.push(origin);

    // Loop that goes through all the flights from origin city
    for (Flight flight : mapOfFlights.get(origin)) {

      // Checks departure dates are the same
      if (date.format(flight.getDepartureDate().getTime()).equals(departureDate)) {
        // Checks if the <code>Flight</code> destination is wanted destination
        // This is for direct flights
        if (flight.getDestination().equals(destination)) {
          // Adds Flight as a valid path to destination
          /*Stack<Flight> temp = new Stack<Flight>();
          temp.push(flight);
          paths.add(temp);*/
          
          Itinerary newPath = new Itinerary();
          newPath.addFlight(flight);
          allItineraries.add(newPath);

          // Else if checks to make sure the <code>Flight</code> destination
          // has <code>Flight</code>'s leaving from that destination
          // This is for transfer flights
        } else if (mapOfFlights.containsKey(flight.getDestination())) {
          connection.push(flight);
          // Calls private recursive method to find all connection
          // <code>Flight</code>
          getFlights(flight, destination, location, connection, allItineraries);
          connection.pop();
        }
      }
    }
    return allItineraries;
  }

  /**
   * Recursive method which finds all possible connection flights to the desired
   * destination.
   * 
   * @param flight
   *          Current <code>Flight</code>.
   * @param destination
   *          Desired destination.
   * @param location
   *          All cities already visited.
   * @param connection
   *          Previous <code>Flight</code>s.
   * @param allItineraries
   *          All valid paths so far.
   */
  private void getFlights(Flight flight, String destination, Stack<String> location,
      Stack<Flight> connection, List<Itinerary> allItineraries) {
    // Loops through list of all <code>Flight</code> leaving from city which
    // current <code>Flight</code> will land at
    for (Flight nextFlight : mapOfFlights.get(flight.getDestination())) {
      // Checks to make sure the layover time between transfers is within the
      // limit
      if (flight.timeBetweenFlights(nextFlight).compareTo(Driver.MIN_LAYOVER) >= 0
          && flight.timeBetweenFlights(nextFlight).compareTo(Driver.MAX_LAYOVER) <= 0) {
        // Checks if next <code>Flight</code> goes to desired destination
        if (nextFlight.getDestination().equals(destination)) {
          // Adds new valid path into paths
          Itinerary newPath = new Itinerary();
          for (Flight flights : connection) {            
              newPath.addFlight(flights);
          }
          newPath.addFlight(nextFlight);
          allItineraries.add(newPath);          

          // Makes sure the next <code>Flight</code>'s destination has not been
          // visited before and that city has <code>Flight</code> leaving it
        } else if (!location.contains(nextFlight.getOrigin())
            && mapOfFlights.containsKey(nextFlight.getDestination())) {
          location.push(nextFlight.getOrigin());
          connection.push(nextFlight);
          // Recursive call with the next <code>Flight</code>
          getFlights(nextFlight, destination, location, connection, allItineraries);
          location.pop();
          connection.pop();
        }
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see FlightServices.FlightService#sortFlight(java.util.List,
   * java.lang.String)
   */
  @Override
  public void sortItineraries(List<Itinerary> itineraries, String sortBy, boolean increasingOrder)
      throws InvalidSortException {
    Comparator<Itinerary> comparator;
    switch (sortBy) {
      case "Time":
        comparator = new ItineraryTimeComparator();
        break;
      case "Cost":
        comparator = new ItineraryCostComparator();

        break;
      default:
        throw new InvalidSortException(String.format("Was told to sort by %s", sortBy));
    }
    // Sort in increasing order
    if (increasingOrder) {
      itineraries.sort(comparator);
      // Sort in decreasing order
    } else {
      itineraries.sort(comparator.reversed());
    }
  }
}
