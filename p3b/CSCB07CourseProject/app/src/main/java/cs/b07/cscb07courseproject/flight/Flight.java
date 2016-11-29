package cs.b07.cscb07courseproject.flight;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.Duration;
//import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;

import cs.b07.cscb07courseproject.util.ValidDate;

/**
 * A representation of a flight from one city to another that leaves on a
 * certain date and time and arrives on another date and time. Also has a flight
 * number and an airline which the flight will take. Provides number of seats
 * available, cost and total flying time.
 * 
 * @author lucsteph
 *
 */
public class Flight {
  // Attributes
  private String flightNum;
  private Calendar arrivalDate;
  private Calendar departureDate;
  private String origin;
  private String destination;
  private String airline;
  private static int availableSeats;
  private long flightDuration;
  private double cost;

  private static DateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");

  /**
   * Creates a new <code>Flight</code> with the following attributes.
   * 
   * @param flightNum
   *          Unique flight number.
   * @param origin
   *          City which flight leaves from.
   * @param destination
   *          City which flight lands at.
   * @param airline
   *          The flight's airline.
   * @param departureDate
   *          Date flight leaves on.
   * @param arrivalDate
   *          Date flight lands on
   * @param availableSeats
   *          Number of seats left.
   * @param cost
   *          Cost for flight.
   * @throws ParseException
   *           if date is not correctly inputed.
   */
  public Flight(String flightNum, String origin, String destination, String airline,
      String departureDate, String arrivalDate, int availableSeats, double cost)
      throws ParseException {
    this.flightNum = flightNum;
    this.origin = origin;
    this.destination = destination;
    this.airline = airline;
    this.departureDate = new GregorianCalendar();
    this.arrivalDate = new GregorianCalendar();
    setDepartureDate(departureDate);
    setArrivalDate(arrivalDate);
    Flight.availableSeats = availableSeats;
    setDuration();
    this.cost = cost;
  }

  /**
   * Returns flight number.
   * 
   * @return flight number.
   */
  public String getFlightNum() {
    return flightNum;
  }

  /**
   * Set flight number.
   * 
   * @param flightNum
   *          new flight number.
   */
  public void setFlightNum(String flightNum) {
    this.flightNum = flightNum;
  }

  /**
   * Return arrival date.
   * 
   * @return arrival date.
   */
  public Calendar getArrivalDate() {
    return arrivalDate;
  }

  /**
   * Set arrival date.
   * 
   * @param arrivalDate
   *          new arrival date.
   * @throws ParseException
   *           if new arrival date is not valid.
   */
  public void setArrivalDate(String arrivalDate) throws ParseException {
    if (ValidDate.validDateTime(arrivalDate)) {
      this.arrivalDate.setTime(dateTime.parse(arrivalDate));
      this.setDuration();
    }
  }

  /**
   * Return departure date.
   * 
   * @return departure date.
   */
  public Calendar getDepartureDate() {
    return departureDate;
  }

  /**
   * Set departure date.
   * 
   * @param departureDate
   *          new departure date.
   * @throws ParseException
   *           if new departure date is not valid.
   */
  public void setDepartureDate(String departureDate) throws ParseException {
    if (ValidDate.validDateTime(departureDate)) {
      this.departureDate.setTime(dateTime.parse(departureDate));
      this.setDuration();
    }
  }

  /**
   * Return origin city.
   * 
   * @return origin.
   */
  public String getOrigin() {
    return origin;
  }

  /**
   * Set origin city.
   * 
   * @param origin
   *          new origin city.
   */
  public void setOrigin(String origin) {
    this.origin = origin;
  }

  /**
   * Return destination city.
   * 
   * @return destination city.
   */
  public String getDestination() {
    return destination;
  }

  /**
   * Set destination city.
   * 
   * @param destination
   *          new destination city.
   */
  public void setDestination(String destination) {
    this.destination = destination;
  }

  /**
   * Return airline.
   * 
   * @return airline.
   */
  public String getAirline() {
    return airline;
  }

  /**
   * Set new airline.
   * 
   * @param airline
   *          new airline.
   */
  public void setAirline(String airline) {
    this.airline = airline;
  }

  /**
   * Return number of seats available.
   * 
   * @return number of seats available.
   */
  public int getAvailableSeats() {
    return availableSeats;
  }

  /**
   * Set number of seats available.
   * 
   * @param availableSeats
   *          new number of seats available.
   */
  public void setAvailableSeats(int availableSeats) {
    Flight.availableSeats = availableSeats;
  }

  /**
   * Return flight time.
   * 
   * @return flight time.
   */
  public long getDuration() {
    return flightDuration;
  }

  /**
   * Sets flight time by getting difference between departure time and arrival
   * time.
   */
  private void setDuration() {
    this.flightDuration =
            (departureDate.getTimeInMillis() - arrivalDate.getTimeInMillis())/1000;
  }

  /**
   * Returns cost.
   * 
   * @return cost.
   */
  public double getCost() {
    return cost;
  }

  /**
   * Sets cost.
   * 
   * @param cost
   *          new cost.
   */
  public void setCost(double cost) {
    this.cost = cost;
  }

  /**
   * Gets time between a <code>Flight</code>'s arrival time and another
   * <code>Flight</code>'s departure time.
   * 
   * @param flight
   *          <code>Flight</code> wanted to be compared to.
   * @return time difference between both <code>Flight</code>s in seconds.
   */
  public long timeBetweenFlights(Flight flight) {
    return (arrivalDate.getTimeInMillis() - flight.getDepartureDate().getTimeInMillis())/1000;
  }

  @Override
  public String toString() {
    return String.format("%s;%s;%s;%s;%s;%s;%.2f", flightNum,
        dateTime.format(departureDate.getTime()), dateTime.format(arrivalDate.getTime()), airline,
        origin, destination, cost);
  }

}
