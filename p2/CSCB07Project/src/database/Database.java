package database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import flight.Flight;
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
	
	/**Reads all Flights in from a Semicolon-Seperated values text file.
	 * @param flightDir the directory of the .txt file
	 * @throws IOException thrown when I/O error occurs
	 * @throws NumberFormatException thrown if Price in file is not a valid Double
	 * @throws ParseException thrown if arrival time and/or departure time are not valid Dates
	 */
	public void readFlightTxt(String flightDir) throws IOException, NumberFormatException, ParseException {
		//Number;DepartureDateTime;ArrivalDateTime;Airline;Origin;Destination;Price
		for (String line : Files.readAllLines(Paths.get(flightDir))) {
			//Usually this is a terrible way to parse value seperated files. This is acceptable here, because data cannot contain semicolons
			String[] strArr = line.split(";");
			//String origin, String destonation, String airline, String departureDate, String arrivalDate, int availableSeats, double cost
			flights.add(new Flight(strArr[0], strArr[4],strArr[5],strArr[3],strArr[1],strArr[2],999,Double.valueOf(strArr[6])));
		}
		
	}
	
	/**Returns a list of Clients in this Database.
	 * @return a list of Clients in this Database.
	 */
	public List<Client> getClients() {
		return clients;
	}

	/**Returns a list of Admins in this Database.
	 * @return a list of Admins in this Database.
	 */
	public List<Admin> getAdmins() {
		return admins;
	}

	/**Returns a list of Flights in this Database.
	 * @return a list of Flights in this Database.
	 */
	public List<Flight> getFlights() {
		return flights;
	}

	
	/**Reads all Clients in from a Semicolon-Separated values text file.
	 * @param clientDir the directory of the .txt file
	 * @throws IOException thrown when I/O error occurs
	 */
	public void readClientTxt(String clientDir) throws IOException {
		//LastName;FirstNames;Email;Address;CreditCardNumber;ExpiryDate
		for (String line : Files.readAllLines(Paths.get(clientDir))) {
			//Usually this is a terrible way to parse value separated files. This is acceptable here, because data cannot contain semicolons
		    String[] strArr = line.split(";");
		    //email,password,first,last,address, CC
		    clients.add(new Client(strArr[2],"default",strArr[1],strArr[0], strArr[3], strArr[4]));
		}
		
		
	}
	
	/** returns a Client object in this Database with a specific email.
	 * @param email the email that corresponds to a Client
	 * @return a Client object with email "email"
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
	This code is all apart of the JSON implementation of the database. Since we only need to read TXT data, this is not used.
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
