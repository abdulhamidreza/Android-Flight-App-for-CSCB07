package cs.b07.cscb07courseproject.itinerary;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.time.Duration;
//import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cs.b07.cscb07courseproject.flight.Flight;

public class Itinerary implements Serializable {

  private List<Flight> flights;
  private double totalCost; // the total cost in $
  private long totalTime; // the total time in duration

  private static DateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");

  /**
   * Constructs an Itinerary object, and initializes the fields to starting
   * values.
   * 
   */
  public Itinerary() {

    // declare variables in Constructor, for good form.
    this.totalCost = 0;
    this.totalTime = 0;
    this.flights = new ArrayList<Flight>();

  }

  /**
   * Adds this flight to the Itinerary.
   * 
   * @param flight
   *          the flight to add to the Itinerary
   * @return whether the flight was successfully added
   */
  public boolean addFlight(Flight flight) {

    totalCost += flight.getCost();

    boolean canAdd = flights.add(flight);

    // the total time is the time from the beginning of the first flight to the
    // end of the last
    // flight
    setTotalTime();

    return canAdd;

  }

  /**
   * Removes a flight from this Itinerary.
   * 
   * @param flight
   *          this flight
   * @return whether the flight was successfully removed.
   */
  public boolean removeFlight(Flight flight) {

    totalCost -= flight.getCost();

    boolean canRemove = flights.remove(flight);

    // the total time is the time from the beginning of the first flight to the
    // end of the second
    // last flight (after the last is removed)
    setTotalTime();

    return canRemove;

  }

  /**
   * Returns the unique Itinerary ID for this Itinerary.
   * 
   * @return this Itinerary's unique ID
   */
  public int getItineraryId() {
    // we use the Itinerary's hash as a unique identifier.
    return this.hashCode();
  }

  /**
   * Returns all the flights in this Itinerary.
   * 
   * @return a list of all flights in this Itinerary
   */
  public List<Flight> getFlights() {
    return flights;
  }

  /**
   * Returns the total cost of this Itinerary.
   * 
   * @return a double representing the cost
   */
  public double getTotalCost() {
    return totalCost;
  }

  /**
   * Returns the total time of this Itinerary.
   * 
   * @return the total time of this Itinerary
   */
  public long getTotalTime() {
    return totalTime;
  }

  public String getTotalTimeString(){
    return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toHours(totalTime),TimeUnit.MILLISECONDS.toMinutes(totalTime)
            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(totalTime)));
  }

  /**
   * Refreshes the total time of this Itinerary.
   */
  private void setTotalTime() {
    // the difference in time between the departure of the first flight, and the
    // arrival of the last
    // flight
    if(flights.size() == 0) {
      totalTime = 0;
    } else if (flights.size() == 1) {
      totalTime = flights.get(0).getDuration();
    } else {
      totalTime = (flights.get(flights.size()-1).getArrivalDate().getTimeInMillis() - flights.get(0).getDepartureDate().getTimeInMillis());
    }

  }

  /**
   * Generates this itinerary's hashcode.
   * @return the hashcode representation of this itinerary
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((flights == null) ? 0 : flights.hashCode());
    long temp;
    temp = Double.doubleToLongBits(totalCost);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + (int) (totalTime ^ (totalTime >>> 32));
    return result;
  }

  /**
   * Check if this itinerary is equal to the given object.
   * @param obj the object being compared to this itinerary
   * @return returns true if the two are equal
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Itinerary other = (Itinerary) obj;
    if (flights == null) {
      if (other.flights != null)
        return false;
    } else if (!flights.equals(other.flights))
      return false;
    if (Double.doubleToLongBits(totalCost) != Double.doubleToLongBits(other.totalCost))
      return false;
    if (totalTime != other.totalTime)
      return false;
    return true;
  }

  public byte[] getBytes() {
    String itineraryFormat = "";
    for (Flight flight : flights) {
      itineraryFormat += String.format("%s;%s;%s;%s;%s;%s", flight.getFlightNum(),
              dateTime.format(flight.getDepartureDate().getTime()),
              dateTime.format(flight.getArrivalDate().getTime()), flight.getAirline(),
              flight.getOrigin(), flight.getDestination());
    }

    return itineraryFormat.getBytes();
  }

  public String oneLine() {
    String itineraryFormat = "[";
    for (int i = 0; i < flights.size() ; i++ ) {
      Flight flight = flights.get(i);
      itineraryFormat += String.format("%s;%s;%s;%s;%s;%s", flight.getFlightNum(),
              dateTime.format(flight.getDepartureDate().getTime()),
              dateTime.format(flight.getArrivalDate().getTime()), flight.getAirline(),
              flight.getOrigin(), flight.getDestination());
      if( i != flights.size() - 1) {
        itineraryFormat += ",";
      }
    }
    itineraryFormat += "]";
    return itineraryFormat;

  }

  /**
   * Generates this itinerary's string representation.
   * @return the string representation of this itinerary
   */
  @Override
  public String toString() {
    String itineraryFormat = "";
    for (Flight flight : flights) {
      itineraryFormat += String.format("%s;%s;%s;%s;%s;%s%n", flight.getFlightNum(),
          dateTime.format(flight.getDepartureDate().getTime()),
          dateTime.format(flight.getArrivalDate().getTime()), flight.getAirline(),
          flight.getOrigin(), flight.getDestination());
    }

    itineraryFormat += String.format("%.2f%n%s", totalCost, getTotalTimeString());

    return itineraryFormat;
  }

}
