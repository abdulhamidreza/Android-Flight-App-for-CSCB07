package database;

import flight.Flight;
import users.Admin;
import users.Client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


// The class for storing information in JSON files.
public class Database {

  private List<Client> clients;
  private List<Admin> admins;
  private List<Flight> flights;

  /**
   * Constructs this Database object, initializing it's fields as empty Lists.
   *
   */
  public Database() {
    // reads in data from files
    clients = new ArrayList<Client>();
    admins = new ArrayList<Admin>();
    flights = new ArrayList<Flight>();

  }

  /**
   * Reads all Flights in from a Semicolon-Seperated values text file.
   *
   * @param flightDir the directory of the .txt file
   * @throws IOException thrown when I/O error occurs
   * @throws NumberFormatException thrown if Price in file is not a valid Double
   * @throws ParseException thrown if arrival time and/or departure time are not valid Dates
   */
  public void readFlightTxt(String flightDir)
          throws IOException, NumberFormatException, ParseException {
    // Number;DepartureDateTime;ArrivalDateTime;Airline;Origin;Destination;Price;NumSeats
    for (String line : Files.readAllLines(Paths.get(flightDir))) {
      // Usually this is a terrible way to parse value seperated files. This is acceptable here,
      // because data cannot contain semicolons
      String[] strArr = line.split(";");
      // String origin, String destonation, String airline, String departureDate, String
      // arrivalDate, int availableSeats, double cost
      Flight toAdd = new Flight(strArr[0], strArr[4], strArr[5], strArr[3], strArr[1], strArr[2], Integer.valueOf(strArr[7]),
              Double.valueOf(strArr[6]));
      //Check if this Flight is already contained in the Database
      Flight exists = this.getFlight(toAdd.getFlightNum());

      if( exists != null ) {

        this.deleteFlight(exists);


      }
      this.addNewFlight(toAdd);


    }

  }

  /**
   * Returns a list of Clients in this Database.
   *
   * @return a list of Clients in this Database.
   */
  public List<Client> getClients() {
    return clients;
  }

  /**
   * Returns a list of Admins in this Database.
   *
   * @return a list of Admins in this Database.
   */
  public List<Admin> getAdmins() {
    return admins;
  }

  /**
   * Returns a list of Flights in this Database.
   *
   * @return a list of Flights in this Database.
   */
  public List<Flight> getFlights() {
    return flights;
  }


  /**
   * Reads all Clients in from a Semicolon-Separated values text file.
   *
   * @param clientDir the directory of the .txt file
   * @throws IOException thrown when I/O error occurs
   * @throws ParseException thrown if Expiry date is not in specified format
   */
  public void readClientTxt(String clientDir) throws IOException, ParseException {
    // LastName;FirstNames;Email;Address;CreditCardNumber;ExpiryDate
    for (String line : Files.readAllLines(Paths.get(clientDir))) {
      // Usually this is a terrible way to parse value separated files. This is acceptable here,
      // because data cannot contain semicolons
      String[] strArr = line.split(";");
      // email,password,first,last,address, CC
      DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
      Client toAdd = new Client(strArr[2], "default", strArr[1], strArr[0], strArr[3], strArr[4],
              date.parse(strArr[5]));
      //Check if this Client is already contained in the Database
      Client exists = this.getClient(toAdd.getEmail());

      if( exists != null ) {

        this.deleteClient(exists);


      }
      this.AddNewClient(toAdd);
    }


  }

  /**
   * returns a Client object in this Database with a specific email.
   *
   * @param email the email that corresponds to a Client
   * @return a Client object with email "email"
   */
  public Client getClient(String email) {

    for (Client curr : clients) {

      if (curr.getEmail().equals(email)) {

        return curr;

      }

    }
    // if no client is found, return nothing
    return null;

  }

  /**
   * returns an Admin object in this Database with a specific email.
   *
   * @param email the email that corresponds to a Admin
   * @return a Admin object with email "email"
   */
  public Admin getAdmin(String email) {

    for (Admin curr : admins) {

      if (curr.getEmail().equals(email)) {

        return curr;

      }

    }
    // if no admin is found, return nothing
    return null;

  }

  /**
   * returns a Flight object in this Database with a specific number.
   *
   * @param flightNum, the flight number that corresponds to a Flight
   * @return a Flight object with num "flightNum"
   */
  public Flight getFlight(String flightNum) {

    for (Flight curr : flights) {

      if (curr.getFlightNum().equals(flightNum)) {

        return curr;

      }

    }
    // if no Flight is found, return nothing
    return null;

  }



  public boolean AddNewClient(Client client) {

    return clients.add(client);

  }

  public boolean AddNewAdmin(Admin admin) {

    return admins.add(admin);

  }

  boolean addNewFlight(Flight flight) {

    return flights.add(flight);

  }

  void deleteFlight(Flight flight) {

    flights.remove(flight);

  }

  void deleteClient(Client client) {

    clients.remove(client);

  }

  void deleteAdmin(Admin admin) {

    admins.remove(admin);

  }

  public void UpdateClient(Client client) {

    for( Client curr : clients ) {

      if(curr.getEmail().equals(client.getEmail()) && !curr.equals(client)) {

        clients.remove(curr);

      }

    }

    clients.add(client);

  }

  public void UpdateAdmin(Admin admin) {

    for( Admin curr : admins ) {

      if(curr.getEmail().equals(admin.getEmail()) && !curr.equals(admin)) {

        admins.remove(curr);

      }

    }

    admins.add(admin);

  }


  public void UpdateFlight(Flight flight) {

    for( Flight curr : flights ) {

      if(curr.getFlightNum().equals(flight.getFlightNum()) && !curr.equals(flight)) {

        flights.remove(curr);

      }

    }

    flights.add(flight);

  }
}