/**
 * 
 */
package Flight;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author lucsteph
 *
 */
public class Flight {
	private int flightId;
	private Calendar arrivalDate;
	private Calendar departureDate;
	private String origin;
	private String destination;
	private String airline;
	private static int availableSeats;
	private Duration flightDuration;
	private double cost;
	

  private static DateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public Flight (String origin, String destonation, String airline, String departureDate,
			String arrivalDate, int availableSeats, double cost) throws ParseException {
		this.origin = origin;
		this.destination = destonation;
		this.airline = airline;
		this.departureDate = new GregorianCalendar();
		this.arrivalDate = new GregorianCalendar();
		setDepartureDate(departureDate);
		setArrivalDate(arrivalDate);
		Flight.availableSeats = availableSeats; // see if this works
		setDuration();
		this.cost = cost;
		
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public Calendar getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) throws ParseException {
		this.arrivalDate.setTime(dateTime.parse(arrivalDate));
		this.setDuration();
	}

	public Calendar getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) throws ParseException {
		this.departureDate.setTime(dateTime.parse(departureDate));
		this.setDuration();
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		Flight.availableSeats = availableSeats;
	}

	public Duration getDuration() {
		return flightDuration;
	}
	
	private void setDuration() {
    long min = ChronoUnit.MINUTES.between(departureDate.toInstant(), arrivalDate.toInstant());
    this.flightDuration = Duration.ofMinutes(min);
  }

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public Duration timeBetweenFlights(Flight flight) {
    long min = ChronoUnit.MINUTES.between(arrivalDate.toInstant(), flight.getDepartureDate().toInstant());  
		return Duration.ofMinutes(min);
	}

  @Override
  public String toString() {
    return "Flight [ origin=" + origin
        + ", destination=" + destination + "]";
  }
	

}
