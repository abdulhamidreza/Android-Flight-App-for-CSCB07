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
    // Number;DepartureDateTime;ArrivalDateTime;Airline;Origin;Destination;Price
    for (String line : Files.readAllLines(Paths.get(flightDir))) {
      // Usually this is a terrible way to parse value seperated files. This is acceptable here,
      // because data cannot contain semicolons
      String[] strArr = line.split(";");
      // String origin, String destonation, String airline, String departureDate, String
      // arrivalDate, int availableSeats, double cost
      flights.add(new Flight(strArr[0], strArr[4], strArr[5], strArr[3], strArr[1], strArr[2], 999,
              Double.valueOf(strArr[6])));
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
      clients.add(new Client(strArr[2], "default", strArr[1], strArr[0], strArr[3], strArr[4],
              date.parse(strArr[5])));
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

  void AddFlightsFromFile(String path) {


  }

  public void UpdateClient(Client client) {



  }

  public void UpdateAdmin(Admin admin) {



  }

  public void UpdateFlight(Flight flight) {



  }


}
