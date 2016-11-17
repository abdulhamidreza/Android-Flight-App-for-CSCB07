package itinerary;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import flight.Flight;


public class Itinerary {

	private List<Flight> flights;
	private double totalCost; //the total cost in $
	private Duration totalTime; //the total time in duration
	
	public Itinerary() {
		
		this.totalCost = 0;
		this.totalTime = Duration.ZERO;
		this.flights = new ArrayList<Flight>();
		
	}
	
	public boolean addFlight(Flight flight) {
		
		totalCost += flight.getCost();	

		boolean canRemove = flights.add(flight);
		
		//the total time is the time from the beginning of the first flight to the end of the last flight
		setTotalTime();
		
		return canRemove;
		
	}
	
	public boolean removeFlight(Flight flight) {
		
		totalCost -= flight.getCost();
		
		boolean canRemove = flights.remove(flight);
		
		//the total time is the time from the beginning of the first flight to the end of the second last flight (after the last is removed)
		setTotalTime();
		
		return canRemove;
		
	}
	
	public int getItineraryId(){
		
		return this.hashCode();
		
	}
 
	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public Duration getTotalTime() {
		return totalTime;
	}

	private void setTotalTime() {
		long min = ChronoUnit.MINUTES.between(
				flights.get(0).getDepartureDate().toInstant(), flights.get(flights.size() - 1).getArrivalDate().toInstant());
	    this.totalTime = Duration.ofMinutes(min);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flights == null) ? 0 : flights.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (totalTime.getSeconds() ^ (totalTime.getSeconds() >>> 32));
		return result;
	}

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
		if (Double.doubleToLongBits(totalCost) != Double
				.doubleToLongBits(other.totalCost))
			return false;
		if (totalTime != other.totalTime)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Itinerary [" + flights + "]";
	}
	
	
}
