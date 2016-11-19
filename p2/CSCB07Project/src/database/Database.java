package database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import flight.Flight;
import flightServices.InvalidDateException;
import users.Admin;
import users.Client;

//The class for storing information in JSON files.
public class Database {

	private List<Client> clients;
	private List<Admin> admins;
	private List<Flight> flights;
	
	public Database() {
		//reads in data from files
		clients = new ArrayList<Client>();
		admins = new ArrayList<Admin>();
		flights = new ArrayList<Flight>();
		
	}
	
	public void readFlightCsv(String flightDir) throws IOException, NumberFormatException, ParseException, InvalidDateException {
		//Number;DepartureDateTime;ArrivalDateTime;Airline;Origin;Destination;Price
		for (String line : Files.readAllLines(Paths.get(flightDir))) {
			String[] strArr = line.split(";");
			//String origin, String destonation, String airline, String departureDate, String arrivalDate, int availableSeats, double cost
			flights.add(new Flight(strArr[0], strArr[4],strArr[5],strArr[3],strArr[1],strArr[2],420,Double.valueOf(strArr[6])));
		}
		
	}
	
	public List<Client> getClients() {
		return clients;
	}

	public List<Admin> getAdmins() {
		return admins;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void readClientCsv(String clientDir) throws IOException {
		//LastName;FirstNames;Email;Address;CreditCardNumber;ExpiryDate
		for (String line : Files.readAllLines(Paths.get(clientDir))) {
		    String[] strArr = line.split(";");
		    //email,password,first,last,address, CC
		    clients.add(new Client(strArr[2],"default",strArr[1],strArr[0], strArr[3], strArr[4]));
		}
		
		
	}
	
	/** returns the Client object with the email address "email".
	 * @param email
	 * @return a Client object
	 */
	public Client getClient(String email) {
		
		for( Client curr : clients ) {
			
			if( curr.getEmail().equals(email) ) {
				
				return curr;
				
			}
			
		}
		//if no client is found, return nothing
		return null;
		
	}
	
	
	/*
	public void readAll() {
		
		
		
	}
	
	private List read() {
		
		
		
		try {
			InputStream is = new FileInputStream(dir);
			JsonReader rdr = Json.createReader(is);
			
			JsonObject obj = rdr.readObject();
			
				
			
			
		
		} catch (FileNotFoundException e) {
			//if the file is not found
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	public boolean AddNewClient(Client client) {
		
		return clients.add(client);
		
	}
	
	//Not used for this phase
	public boolean AddNewAdmin(Admin admin) {
		
		JsonArray toAdd = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("email", admin.getEmail())
						.add("password", admin.getPassword()))
				.build();
		
		OutputStream output;
		try {
			output = new FileOutputStream(adminDir);
			JsonWriter writer = Json.createWriter(output);
			writer.writeArray(toAdd);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
				
		
		return admins.add(admin);
		
		
	}
	
	//not used for this phase
	public boolean AddNewFlight(Flight flight) {
		
		return flights.add(flight);
		
	}
	
	//not used for this phase
	public boolean AddFlightsFromFile(String path) {
		
	return null;	
		
	}
	
	public void UpdateClient(Client client) {
		
		
		
	}
	
	public void UpdateAdmin(Admin admin) {
		
		
		
	}
	
	public void UpdateFlight(Flight flight) {
		
		
		
	}
	*/

}
