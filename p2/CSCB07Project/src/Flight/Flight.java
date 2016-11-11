/**
 * 
 */
package Flight;

import java.time.Duration;
import java.util.Date;

/**
 * @author lucsteph
 *
 */
public class Flight {
	private int flightId;
	private Date arrivalDate;
	private Date departureDate;
	private String origin;
	private String destination;
	private String airline;
	private static int availableSeats;
	private Duration flightTime;
	private double cost;
	
	public Flight (String origin, String destonation, String airline, Date departureDate,
			Date arrivalDate, int availableSeats, double cost) {
		this.origin = origin;
		this.destination = destonation;
		this.airline = airline;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		Flight.availableSeats = availableSeats; // see if this works
		this.cost = cost;
		
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
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

	public static int getAvailableSeats() {
		return availableSeats;
	}

	public static void setAvailableSeats(int availableSeats) {
		Flight.availableSeats = availableSeats;
	}

	public Duration getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(Duration flightTime) {
		this.flightTime = flightTime;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public Duration compareTimes(Date arrival, Date departure) {
		return null;
	}
	
	public Duration timeBetweenFlights(Flight flight) {
		return null;
	}
	

}
