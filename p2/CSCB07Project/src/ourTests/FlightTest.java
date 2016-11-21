package ourTests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import flight.Flight;

/**
 * Tests for Flight class.
 * 
 * @author Stephen
 *
 */
public class FlightTest {

  private Flight flight1;
  /*
   * private Flight flight2; private Flight flight3;
   */
  private Flight flight4;

  private static DateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");

  /**
   * Set up Flight objects for testing.
   * 
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {

    flight1 = new Flight("AC489", "Toronto", "New York", "Air Canada", "2016-05-30 23:05",
        "2016-05-31 05:05", 240, 400);
    /*
     * flight2 = new Flight("AC489", "Toronto", "Vancouver", "Air Canada",
     * "2016-06-30 13:05", "2016-06-30 15:35", 300, 100); flight3 = new
     * Flight("AC489", "China", "Germany", "PPC", "2016-05-30 23:05",
     * "2016-06-30 05:05", 240, 400);
     */
    flight4 = new Flight("AC489", "Toronto", "New York", "Air Canada", "2016-05-31 23:05",
        "2016-05-31 05:05", 240, 400);
  }

  /**
   * Tears down and prepare for next test.
   * 
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  /**
   * Test method for {@link flight.Flight#getArrivalDate()}.
   * 
   * @throws ParseException
   */
  @Test
  public void testGetArrivalDate() throws ParseException {
    Calendar date = new GregorianCalendar();
    date.setTime(dateTime.parse("2016-05-31 05:05"));
    assertEquals("Flight1 arrival date should be ", date, flight1.getArrivalDate());
  }

  /**
   * Test method for {@link flight.Flight#setArrivalDate(java.lang.String)}.
   * 
   * @throws ParseException
   */
  @Test
  public void testSetArrivalDate() throws ParseException {
    Calendar date = new GregorianCalendar();
    date.setTime(dateTime.parse("2016-05-31 05:05"));
    try {
      flight1.setArrivalDate("2016-06-31 05:05");
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    assertEquals("Flight1 arrival date after change should be ", date, flight1.getArrivalDate());
  }

  /**
   * Test method for {@link flight.Flight#getDepartureDate()}.
   * 
   * @throws ParseException
   */
  @Test
  public void testGetDepartureDate() throws ParseException {
    Calendar date = new GregorianCalendar();
    date.setTime(dateTime.parse("2016-05-30 23:05"));
    assertEquals("Flight1 departure date should be ", date, flight1.getDepartureDate());
  }

  /**
   * Test method for {@link flight.Flight#setDepartureDate(java.lang.String)}.
   * 
   * @throws ParseException
   */
  @Test
  public void testSetDepartureDate() throws ParseException {
    Calendar date = new GregorianCalendar();
    date.setTime(dateTime.parse("2016-05-30 23:05"));

    try {
      flight1.setDepartureDate("2016-06-32 05:05");
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    assertEquals("Flight1 departure date after change should be ", date,
        flight1.getDepartureDate());
  }

  /**
   * Test method for {@link flight.Flight#getOrigin()}.
   */
  @Test
  public void testGetOrigin() {
    assertEquals("Flight1 origin should be ", "Toronto", flight1.getOrigin());
  }

  /**
   * Test method for {@link flight.Flight#setOrigin(java.lang.String)}.
   */
  @Test
  public void testSetOrigin() {
    flight1.setOrigin("Beijing");
    assertEquals("Flight1 origin should be ", "Beijing", flight1.getOrigin());
  }

  /**
   * Test method for {@link flight.Flight#getDestination()}.
   */
  @Test
  public void testGetDestination() {
    assertEquals("Flight1 origin should be ", "New York", flight1.getDestination());
  }

  /**
   * Test method for {@link flight.Flight#setDestination(java.lang.String)}.
   */
  @Test
  public void testSetDestination() {
    flight1.setDestination("Beijing");
    assertEquals("Flight1 origin should be ", "Beijing", flight1.getDestination());
  }

  /**
   * Test method for {@link flight.Flight#getAirline()}.
   */
  @Test
  public void testGetAirline() {
    assertEquals("Flight1 origin should be ", "Air Canada", flight1.getAirline());
  }

  /**
   * Test method for {@link flight.Flight#setAirline(java.lang.String)}.
   */
  @Test
  public void testSetAirline() {
    flight1.setAirline("Pacific Airline");
    assertEquals("Flight1 origin should be ", "Pacific Airline", flight1.getAirline());
  }

  /**
   * Test method for {@link flight.Flight#getAvailableSeats()}.
   */
  @Test
  public void testGetAvailableSeats() {
    assertEquals("Flight1 origin should be ", 240, flight1.getAvailableSeats());
  }

  /**
   * Test method for {@link flight.Flight#setAvailableSeats(int)}.
   */
  @Test
  public void testSetAvailableSeats() {
    flight1.setAvailableSeats(65);
    assertEquals("Flight1 origin should be ", 65, flight1.getAvailableSeats());
  }

  /**
   * Test method for {@link flight.Flight#getCost()}.
   */
  @Test
  public void testGetCost() {
    assertEquals("Flight1 origin should be ", 400, flight1.getCost(), 0);
  }

  /**
   * Test method for {@link flight.Flight#setCost(double)}.
   */
  @Test
  public void testSetCost() {
    flight1.setCost(65.3);
    assertEquals("Flight1 origin should be ", 65.3, flight1.getCost(), 0.0005);
  }

  /**
   * Test method for {@link flight.Flight#getFlightTime()}.
   */
  @Test
  public void testGetDuration() {
    assertEquals("Flight1 origin should be ", Duration.ofHours(6), flight1.getDuration());
  }

  /**
   * Test method for {@link flight.Flight#setFlightTime(java.time.Duration)}.
   * 
   * @throws ParseException
   */
  @Test
  public void testSetDuration() throws ParseException {
    try {
      flight1.setArrivalDate("2016-05-31 10:45");
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    assertEquals("Flight1 origin should be ", Duration.ofMinutes(700), flight1.getDuration());
  }

  /**
   * Test method for {@link flight.Flight#timeBetweenFlights(flight.Flight)}.
   */
  @Test
  public void testTimeBetweenFlights() {
    assertEquals("Flight1 origin should be ", Duration.ofHours(18),
        flight1.timeBetweenFlights(flight4));
  }

}
