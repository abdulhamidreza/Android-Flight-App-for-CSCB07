package itinerary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import flight.Flight;


public class Itinerary {

	private List<Flight> flights;
	private double totalCost; //the total cost in $
	private Duration totalTime; //the total time in duration

  private static DateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	/**Constructs an Itinerary object, and initializes the fields to starting values.
	 * 
	 */
	public Itinerary() {
		
		//declare variables in Constructor, for good form.
		this.totalCost = 0;
		this.totalTime = Duration.ZERO;
		this.flights = new ArrayList<Flight>();
		
	}
	
	/**Adds this flight to the Itinerary. 
	 * @param flight
	 * @return
	 */
	public boolean addFlight(Flight flight) {
		
		totalCost += flight.getCost();	

		boolean canRemove = flights.add(flight);
		
		//the total time is the time from the beginning of the first flight to the end of the last flight
		setTotalTime();
		
		return canRemove;
		
	}
	
	/**Removes a flight from this Itinerary.
	 * @param flight this flight
	 * @return whether the flight was successfully removed.
	 */
	public boolean removeFlight(Flight flight) {
		
		totalCost -= flight.getCost();
		
		boolean canRemove = flights.remove(flight);
		
		//the total time is the time from the beginning of the first flight to the end of the second last flight (after the last is removed)
		setTotalTime();
		
		return canRemove;
		
	}
	
	/**Returns the unique Itinerary ID for this Itinerary.
	 * @return this Itinerary's unique ID
	 */
	public int getItineraryId(){	
		//we use the Itinerary's hash as a unique identifier. 
		return this.hashCode();		
	}
	
	/**Returns all the flights in this Itinerary.
	 * @return a list of all flights in this Itinerary
	 */
	public List<Flight> getFlights() {
	  return flights;
	}
 
	/**Returns the total cost of this Itinerary.
	 * @return a double representing the cost
	 */
	public double getTotalCost() {
		return totalCost;
	}

	/**Returns the total time of this Itinerary.
	 * @return the total time of this Itinerary
	 */
	public Duration getTotalTime() {
		return totalTime;
	}

	/**Refreshes the total time of this Itinerary.s
	 * 
	 */
	private void setTotalTime() {
		//the difference in time between the departure of the first flight, and the arrival of the last flight
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
	  String itineraryFormat= "";
	  for (Flight flight: flights) {
	    itineraryFormat += String.format("%s;%s;%s;%s;%s;%s\n", 
	        flight.getFlightNum(), dateTime.format(flight.getDepartureDate().getTime()), 
	        dateTime.format(flight.getArrivalDate().getTime()), flight.getAirline(), 
	        flight.getOrigin(), flight.getDestination());
	  }	 
	  
	  itineraryFormat += String.format("%.2f\n%.2f", totalCost, 
	     Math.floor((totalTime.toMinutes() / 60.0)*100)/100);
	  
	  return itineraryFormat;
	}
	
	
}
