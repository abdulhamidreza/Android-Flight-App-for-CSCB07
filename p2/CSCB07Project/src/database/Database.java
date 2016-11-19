package database;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import flight.Flight;
import users.Admin;
import users.Client;

import javax.json.*;

//The class for storing information in JSON files.
public class Database {

	private List<Client> clients;
	private List<Admin> admins;
	private List<Flight> flights;
	
	private String adminDir;
	private String clientDir;
	private String flightDir;
	
	public Database(String dir) {
		//reads in data from files
		clients = new ArrayList<Client>();
		admins = new ArrayList<Admin>();
		flights = new ArrayList<Flight>();
	}
	
	public void readAll() {
		
		
		
	}
	
	private List read(String adminDir, String clientDir, String flightDir) {
		
		this.adminDir = adminDir;
		this.clientDir = clientDir;
		this.flightDir = flightDir;
		
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
	
	public boolean AddNewFlight(Flight flight) {
		
		return flights.add(flight);
		
	}
	
	public boolean AddFlightsFromFile(String path) {
		
		
		
	}
	
	public void UpdateClient(Client client) {
		
		
		
	}
	
	public void UpdateAdmin(Admin admin) {
		
		
		
	}
	
	public void UpdateFlight(Flight flight) {
		
		
		
	}

}
