package cs.b07.cscb07courseproject.database;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cs.b07.cscb07courseproject.flight.Flight;
import cs.b07.cscb07courseproject.itinerary.Itinerary;
import cs.b07.cscb07courseproject.users.Admin;
import cs.b07.cscb07courseproject.users.Client;


// The class for storing information in JSON files.
public class Database implements Serializable {

    private static List<Client> clients;
    private static List<Admin> admins;
    private static List<Flight> flights;
    private String dir;

    /**
     * Constructs this Database object, initializing it's fields as empty Lists.
     *
     * @throws IOException
     *
     */
    public Database(String dir) {
        // reads in data from files
        this.clients = new ArrayList<Client>();
        this.admins = new ArrayList<Admin>();
        this.flights = new ArrayList<Flight>();
        this.dir = dir;
        try {
            File client = new File(dir, "client.txt");
            if(client.exists()) {
                this.readClientTxt(dir);
            } else {
                client.createNewFile();
            }
            File admin = new File(dir, "admin.txt");
            if(admin.exists()) {
                this.readAdminTxt(dir);
            } else {
                admin.createNewFile();
            }
            File flight = new File(dir, "flight.txt");
            if(client.exists()) {
                this.readFlightTxt(dir, "flight.txt");
            } else {
                flight.createNewFile();
            }
        }catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }


        update();
    }



    /**
     * Reads all Flights in from a Semicolon-Seperated values text file.
     *
     * @param inFlightDir the directory of the .txt file
     * @throws IOException thrown when I/O error occurs
     * @throws NumberFormatException thrown if Price in file is not a valid Double
     * @throws ParseException thrown if arrival time and/or departure time are not valid Dates
     */
    public void readFlightTxt(String inFlightDir, String inFlightName)
            throws IOException, NumberFormatException, ParseException {
        File FlightTxt = new File(inFlightDir, inFlightName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream((FlightTxt))));
        // Number;DepartureDateTime;ArrivalDateTime;Airline;Origin;Destination;Price;NumSeats
        String line;
        while ((line = reader.readLine()) != null ) {
            // Usually this is a terrible way to parse value seperated files. This is acceptable here,
            // because data cannot contain semicolons
            String[] strArr = line.split(";");
            // String number, String origin, String destonation, String airline, String departureDate, String arrivalDate, int availableSeats, double cost
            Flight toAdd = new Flight(strArr[0],strArr[4],strArr[5],strArr[3],strArr[1],strArr[2],Integer.valueOf(strArr[7]),Double.valueOf(strArr[6]));
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
        // LastName;FirstNames;Email;Password;Address;CreditCardNumber;ExpiryDate
        File clientTxt = new File(inClientDir, "client.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(clientTxt)));
        String line;
        while ((line = reader.readLine()) != null) {
            // Usually this is a terrible way to parse value separated files. This is acceptable here,
            // because data cannot contain semicolons
            String[] strArr = line.split(";");
            // email,password,first,last,address, CC, CC Expiry]
            Client toAdd = new Client(strArr[2], strArr[3], strArr[1], strArr[0], strArr[4], strArr[5],
                    strArr[6]);
            String[] itinArr = strArr[7].split(",");
            if(itinArr.length == 1 && itinArr[0].equals("[]")) {

                //do Nothing

            } else if(itinArr.length == 1) {

                toAdd.book(parseItinerary(itinArr[0].substring(1, itinArr[0].length()-1)));

            } else {

                List<Itinerary> toBook = parseItinerarys(itinArr[0].substring(1, itinArr[0].length()-1));

                for(Itinerary book : toBook ) {

                    toAdd.book(book);

                }


            }
            // Check if this Client is already contained in the Database
            Client exists = getClient(toAdd.getEmail());

            if (exists != null) {

                this.deleteClient(exists);

            }
            this.AddNewClient(toAdd);
        }

        updateClient();


    }

    /**
     * Changes String into a list of Itineraries.
     * @param toParse the string being changed
     * @return the changed string in the form of a list of itineraries
     */
    public static List<Itinerary> parseItinerarys(String toParse) throws ParseException {

        if (toParse.equals("[]")) {
            return null;
        } else {

            List<Itinerary> toReturn = new ArrayList<Itinerary>();
            String strArr[] = toParse.split(",");
            for(int i = 0; i < strArr.length ; i++) {

                String currStr;
                if(i == 0) {

                    currStr = strArr[i].substring(1);

                } else if(i == strArr.length - 2) {

                    currStr = strArr[i].substring(0, strArr[i].length()-1);

                } else {

                    currStr = strArr[i];

                }

                toReturn.add(parseItinerary(currStr));

            }

            return toReturn;
        }

    }

    /**
     * Changes String into an Itinerary.
     * @param toParse the string being changed
     * @return the changed string in the form of an itinerary
     */
    public static Itinerary parseItinerary(String toParse) throws ParseException {

        if(toParse.equals("[]")) {

            return null;

        } else {

            Itinerary toReturn = new Itinerary();

            String strArr[] = toParse.split(",");

            for(int i = 0; i < strArr.length ; i++) {

                String currStr;
                if(i == 0) {

                    currStr = strArr[i].substring(1);

                } else if(i == strArr.length - 2) {

                    currStr = strArr[i].substring(0, strArr[i].length()-1);

                } else {

                    currStr = strArr[i];

                }

                toReturn.addFlight(parseFlight(currStr));
            }

            return toReturn;
        }

    }

    /**
     * Changes String into an Flight.
     * @param toParse the string being changed
     * @return the changed string in the form of a flight
     */
    public static Flight parseFlight(String toParse) throws ParseException {

        String strArr[] = toParse.split(";");

        Flight toReturn = new Flight(strArr[0],strArr[4],strArr[5],strArr[3],strArr[1],strArr[2],Integer.valueOf(strArr[7]),Double.valueOf(strArr[6]));

        return toReturn;

    }

    /**
     * Used for reading in data, in same form as Phase 2.
     *
     * @param inClientDir the directory of the .txt file
     * @throws IOException thrown when I/O error occurs
     * @throws ParseException thrown if Expiry date is not in specified format
     */
    public void readInClientTxt(String inClientDir, String fileName) throws IOException, ParseException {
        // LastName;FirstNames;Email;Address;CreditCardNumber;ExpiryDate
        File clientTxt = new File(inClientDir, fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(clientTxt)));
        String line;
        while ((line = reader.readLine()) != null) {
            // Usually this is a terrible way to parse value separated files. This is acceptable here,
            // because data cannot contain semicolons
            String[] strArr = line.split(";");
            // email,password,first,last,address, CC, CCexpiry
            Client toAdd = new Client(strArr[2], "default", strArr[1], strArr[0], strArr[3], strArr[4],
                    strArr[5]);

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


    /**
     * Adds a new client into the database.
     * @param client the client being added
     */
    public void AddNewClient(Client client) {

        String clientEmail = client.getEmail();
        boolean alreadyIn = false;
        Client toRemove = null;
        for (Client curr : clients) {

            if (curr.getEmail().equals(clientEmail) && curr != client) {

                toRemove = curr;
                alreadyIn = true;


            }
        }
        if(alreadyIn) {
            clients.remove(toRemove);
        }
        clients.add(client);


        updateClient();
    }

    /**
     * Adds a new admin to the database.
     * @param admin the admin being added
     */
    public void AddNewAdmin(Admin admin) {

        String adminEmail = admin.getEmail();
        boolean alreadyIn = false;
        Admin toRemove = null;

        for (Admin curr : admins) {

            if (curr.getEmail().equals(adminEmail) && curr != admin) {

                toRemove = curr;
                alreadyIn = true;

            }
        }

        if (alreadyIn) {
            admins.remove(toRemove);
        }
        admins.add(admin);

        updateAdmin();
    }

    /**
     * Adds a new flight to the database.
     * @param flight the flight being added
     */
    public void addNewFlight(Flight flight) {

        String flightNum = flight.getFlightNum();
        boolean alreadyIn = false;
        Flight toRemove = null;

        for (Flight curr : flights) {

            if (curr.getFlightNum().equals(flightNum) && curr != flight) {

                toRemove = curr;
                alreadyIn = true;

            }
        }

        if (!alreadyIn) {
            flights.remove(toRemove);
        }
        flights.add(flight);

        updateFlight();

    }

    /**
     * Deletes a flight from the database.
     * @param flight the flight being removed
     */
    void deleteFlight(Flight flight) {

        flights.remove(flight);

        updateFlight();

    }

    /**
     * Deletes a client from the database.
     * @param client the client being removed
     */
    void deleteClient(Client client) {

        clients.remove(client);

        updateClient();

    }

    /**
     * Deletes an admin from the database.
     * @param admin the client being removed
     */
    void deleteAdmin(Admin admin) {

        admins.remove(admin);

        updateAdmin();

    }

    /**
     * Updates client information in the database.
     */
    public void updateClient() {

        FileOutputStream outStream;

        try {
            File db = new File(dir, "client.txt");
            db.delete();
            outStream = new FileOutputStream(db, true);
            for( Client curr : clients) {
                outStream.write(curr.getBytes());
                outStream.write("\n".getBytes());
            }
            outStream.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Updates admin information in the database.
     */
    public void updateAdmin() {

        FileOutputStream outStream;

        try {
            File db = new File(dir, "admin.txt");
            db.delete();
            outStream = new FileOutputStream(db, true);
            for( Admin curr : admins) {
                outStream.write(curr.getBytes());
                outStream.write("\n".getBytes());
            }
            outStream.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Updates flight information in the database.
     */
    public void updateFlight() {

        FileOutputStream outStream;

        try {
            File db = new File(dir, "flight.txt");
            db.delete();
            outStream = new FileOutputStream(db, true);
            for( Flight curr : flights) {
                outStream.write(curr.getBytes());
                outStream.write("\n".getBytes());
            }
            outStream.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Updates all stored data.
     */
    public void update() {

        updateAdmin();
        updateClient();
        updateFlight();

    }
}
