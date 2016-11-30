package cs.b07.cscb07courseproject.database;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cs.b07.cscb07courseproject.flight.Flight;
import cs.b07.cscb07courseproject.users.Admin;
import cs.b07.cscb07courseproject.users.Client;


// The class for storing information in JSON files.
public class Database {

    private List<Client> clients;
    private List<Admin> admins;
    private List<Flight> flights;
    private String clientDir;
    private String adminDir;
    private String flightDir;

    /**
     * Constructs this Database object, initializing it's fields as empty Lists.
     *
     * @throws IOException
     *
     */
    public Database(String inClientDir, String inAdminDir, String inFlightDir) throws IOException {
        // reads in data from files
        this.clients = new ArrayList<Client>();
        this.admins = new ArrayList<Admin>();
        this.flights = new ArrayList<Flight>();
        this.clientDir = inClientDir;
        this.adminDir = inAdminDir;
        this.flightDir = inFlightDir;

        //This is the part where files will be created, or read in.
    }



    /**
     * Reads all Flights in from a Semicolon-Seperated values text file.
     *
     * @param inFlightDir the directory of the .txt file
     * @throws IOException thrown when I/O error occurs
     * @throws NumberFormatException thrown if Price in file is not a valid Double
     * @throws ParseException thrown if arrival time and/or departure time are not valid Dates
     */
    public void readFlightTxt(String inFlightDir)
            throws IOException, NumberFormatException, ParseException {
        File FlightTxt = new File(inFlightDir, "flight.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream((FlightTxt))));
        // Number;DepartureDateTime;ArrivalDateTime;Airline;Origin;Destination;Price;NumSeats
        String line;
        while ((line = reader.readLine()) != null ) {
            // Usually this is a terrible way to parse value seperated files. This is acceptable here,
            // because data cannot contain semicolons
            String[] strArr = line.split(";");
            // String origin, String destonation, String airline, String departureDate, String
            // arrivalDate, int availableSeats, double cost
            Flight toAdd = new Flight(strArr[0], strArr[4], strArr[5], strArr[3], strArr[1], strArr[2],
                    Integer.valueOf(strArr[7]), Double.valueOf(strArr[6]));
            // Check if this Flight is already contained in the Database
            Flight exists = this.getFlight(toAdd.getFlightNum());

            if (exists != null) {

                this.deleteFlight(exists);


            }
            this.addNewFlight(toAdd);


        }

        updateFlight();

    }

    public void readAdminTxt(String inAdminDir) throws IOException {
        // Email;Password
        File adminTxt = new File(inAdminDir, "admin.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(adminTxt)));
        String line;
        while ((line = reader.readLine()) != null) {
            // Usually this is a terrible way to parse value separated files. This is acceptable here,
            // because data cannot contain semicolons
            String[] strArr = line.split(";");

            Admin toAdd = new Admin(strArr[0], strArr[1]);

            Admin exists = this.getAdmin(toAdd.getEmail());

            if (exists != null) {

                this.deleteAdmin(exists);

            }
            this.AddNewAdmin(toAdd);

        }

        updateAdmin();

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
     * @param inClientDir the directory of the .txt file
     * @throws IOException thrown when I/O error occurs
     * @throws ParseException thrown if Expiry date is not in specified format
     */
    public void readClientTxt(String inClientDir) throws IOException, ParseException {
        // LastName;FirstNames;Email;Address;CreditCardNumber;ExpiryDate
        File adminTxt = new File(inClientDir, "client.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(adminTxt)));
        String line;
        while ((line = reader.readLine()) != null) {
            // Usually this is a terrible way to parse value separated files. This is acceptable here,
            // because data cannot contain semicolons
            String[] strArr = line.split(";");
            // email,password,first,last,address, CC
            DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            Client toAdd = new Client(strArr[2], "default", strArr[1], strArr[0], strArr[3], strArr[4],
                    strArr[5]);
            // Check if this Client is already contained in the Database
            Client exists = this.getClient(toAdd.getEmail());

            if (exists != null) {

                this.deleteClient(exists);


            }
            this.AddNewClient(toAdd);
        }

        updateClient();


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



    public void AddNewClient(Client client) {

        String clientEmail = client.getEmail();
        boolean alreadyIn = false;

        for (Client curr : clients) {

            if (curr.getEmail().equals(clientEmail) && curr != client) {

                clients.remove(curr);
                alreadyIn = true;

            }
        }

        if (!alreadyIn) {

            clients.add(client);

        }

        updateClient();
    }


    public void AddNewAdmin(Admin admin) {

        String adminEmail = admin.getEmail();
        boolean alreadyIn = false;

        for (Admin curr : admins) {

            if (curr.getEmail().equals(adminEmail) && curr != admin) {

                admins.remove(curr);
                alreadyIn = true;

            }
        }

        if (!alreadyIn) {

            admins.add(admin);

        }

        updateAdmin();
    }


    public void addNewFlight(Flight flight) {

        String flightNum = flight.getFlightNum();
        boolean alreadyIn = false;

        for (Flight curr : flights) {

            if (curr.getFlightNum().equals(flightNum) && curr != flight) {

                flights.remove(curr);
                alreadyIn = true;

            }
        }

        if (!alreadyIn) {

            flights.add(flight);

        }

        updateFlight();

    }

    void deleteFlight(Flight flight) {

        flights.remove(flight);

        updateFlight();

    }

    void deleteClient(Client client) {

        clients.remove(client);

        updateClient();

    }

    void deleteAdmin(Admin admin) {

        admins.remove(admin);

        updateAdmin();

    }

    void updateClient() {

        FileOutputStream outStream;

        try {
            File db = new File("client.txt");
            db.delete();
            db.createNewFile();
            outStream = new FileOutputStream("client.txt");
            for( Client curr : clients) {
                outStream.write(curr.toString().getBytes());
            }
            outStream.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    void updateAdmin() {

        FileOutputStream outStream;

        try {
            File db = new File("admin.txt");
            db.delete();
            db.createNewFile();
            outStream = new FileOutputStream("admin.txt");
            for( Admin curr : admins) {
                outStream.write(curr.toString().getBytes());
            }
            outStream.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    void updateFlight() {

        FileOutputStream outStream;

        try {
            File db = new File("flight.txt");
            db.delete();
            db.createNewFile();
            outStream = new FileOutputStream("flight.txt");
            for( Flight curr : flights) {
                outStream.write(curr.toString().getBytes());
            }
            outStream.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Updates all stored data.
     *
     */
    void update() {

        updateAdmin();
        updateClient();
        updateFlight();

    }
}
