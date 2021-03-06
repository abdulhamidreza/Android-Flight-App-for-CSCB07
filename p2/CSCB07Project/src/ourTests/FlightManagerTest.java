package ourTests;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import flight.Flight;

import org.junit.After;
import org.junit.Before;

import flightServices.FlightManager;
import flightServices.InvalidSortException;
import itinerary.Itinerary;

/**
 * Tests for class FlightManager.
 * 
 * @author Stephen
 *
 */
public class FlightManagerTest {

  private Flight flight1;
  private Flight flight2;
  private Flight flight3;
  private Flight flight4;
  private Flight flight5;
  private Flight flight6;
  private Flight flight7;
  private Flight flight8;
  private Flight flight9;
  private Flight flight10;
  private Flight flight11;

  private FlightManager fm;
  private List<Flight> allFlights;

  /**
   * Set up Flight objects for testing.
   * 
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {

    flight1 = new Flight("AC489", "Toronto", "New York", "Air Canada", "2016-05-30 23:05",
        "2016-06-30 05:05", 240, 400);
    flight2 = new Flight("AC489", "Toronto", "China", "Air Canada", "2016-05-30 13:05",
        "2016-06-30 05:05", 300, 100);
    flight3 = new Flight("AC489", "Toronto", "New Zealand", "PPC", "2016-05-27 23:05",
        "2016-06-30 05:05", 240, 400);
    flight4 = new Flight("AC489", "Toronto", "Jamaica", "Air Canada", "2016-05-30 23:05",
        "2016-05-31 05:05", 240, 400);
    flight5 = new Flight("AC489", "China", "New York", "Air Canada", "2016-06-30 06:00",
        "2017-05-31 05:05", 240, 400);
    flight6 = new Flight("AC489", "China", "Germany", "Air Canada", "2016-06-30 05:45",
        "2016-07-28 05:05", 240, 400);
    flight7 = new Flight("AC489", "China", "New York", "Air Canada", "2016-06-30 23:05",
        "2016-05-31 05:05", 240, 400);
    flight8 = new Flight("AC489", "China", "Jamaica", "Air Canada", "2016-06-30 05:35",
        "2016-11-30 05:05", 240, 400);
    flight9 = new Flight("AC489", "New Zealand", "Denmark", "Air Canada", "2016-05-30 23:05",
        "2016-05-31 05:05", 240, 400);
    flight10 = new Flight("AC489", "Germany", "Japan", "Air Canada", "2016-07-28 05:55",
        "2016-08-23 05:05", 240, 400);
    flight11 = new Flight("AC489", "Japan", "New York", "Air Canada", "2016-08-23 05:59",
        "2016-10-31 05:05", 240, 400);

    allFlights = new ArrayList<Flight>();
    allFlights.add(flight1);
    allFlights.add(flight2);
    allFlights.add(flight3);
    allFlights.add(flight4);
    allFlights.add(flight5);
    allFlights.add(flight6);
    allFlights.add(flight7);
    allFlights.add(flight8);
    allFlights.add(flight9);
    allFlights.add(flight10);
    allFlights.add(flight11);

    fm = new FlightManager(allFlights);

  }

  /**
   * Empty memory to prepare for next test.
   * 
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
    System.out.println("\n\n");
  }

  /**
   * Test method for
   * {@link flightServices.FlightManager#FlightManager(java.util.List)}.
   * 
   * @throws ParseException
   */
  @Test
  public void testFlightManager() throws ParseException {
    System.out.println("Flight Manager");
    for (Itinerary flights : fm.getItinerary("Toronto", "New York", "2016-05-30")) {
      System.out.println(flights);
    }
  }

  /**
   * Test method for
   * 
   * {@link flightServices.FlightManager#sortFlight(java.util.List, java.lang.String)}.
   * 
   * @throws ParseException
   */
  @Test
  public void testSortFlight() throws ParseException {
    List<Itinerary> paths = fm.getItinerary("Toronto", "New York", "2016-05-30");
    try {
      fm.sortItineraries(paths, "Time", true);
      for (Itinerary flights : paths) {
        System.out.println(flights);
      }
    } catch (InvalidSortException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      System.out.println("Couldn't sort itineraries.");
    }

  }

}
