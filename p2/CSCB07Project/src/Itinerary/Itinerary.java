package Itinerary;

import java.util.ArrayList;
import java.util.List;

import Flight.Flight;


public class Itinerary {

	private List<Flight> flights = new ArrayList<Flight>();
	private double totalCost = 0;
	private double totalTime = 0;
	
	public Itinerary() {
		
	}
	
	public boolean addFlight(Flight flight) {
		
		totalCost += flight.getCost();
		
		return flights.add(flight);
		
	}
	
	public boolean removeFlight(Flight flight) {
		
		totalCost -= flight.getCost();
		
		return flights.remove(flight);
		
	}
	
}
