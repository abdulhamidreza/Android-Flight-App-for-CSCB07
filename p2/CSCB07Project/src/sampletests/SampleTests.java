package sampletests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import driver.Driver;

import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SampleTests {

  public static final int TIMEOUT = 100;
  
  // NOTE: set the PATH variable to the location of the input files.
  // "C://Users/Stephen/Documents/3rd Year/3A/CSCB07/team_0595/p2/CSCB07Project/src/sampletests/"
  // leave this for testing ^^ you can leave your path here too if you want
  public static final String PATH = "C://Users/Stephen/Documents/3rd Year/3A/CSCB07/team_0595/p2/CSCB07Project/src/sampletests/";
  public static final String CLIENTS = PATH + "clients.txt";
  public static final String FLIGHTS1 = PATH + "flights1.txt";
  public static final String FLIGHTS2 = PATH + "flights2.txt";

  @Test(timeout = TIMEOUT)
  public void testGetClient() {
    
    Driver.uploadClientInfo(CLIENTS);
    /*try {
      Driver.uploadClientInfo(CLIENTS);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }*/

    String expected = "Roe;Richard;richard@email.com;21 First Lane Way;9999888877776666;2017-10-01";
    String found = Driver.getClient("richard@email.com");
    String msg =
        "Unexpected (incorrect or incorrectly formatted) client information "
        + "string was returned.";

    assertEquals(msg, expected, found);
  }

  @Test(timeout = TIMEOUT)
  public void testGetFlights() throws ParseException {

    Driver.uploadFlightInfo(FLIGHTS1);
    
    String expected = "KL490;2016-09-30 22:40;2016-10-01 01:59;Go Airline;New York;Boston;532.00";
    List<String> found = Driver.getFlights("2016-09-30", "New York", "Boston");
    String msg =
        "Unexpected (incorrect or incorrectly formatted) flight information "
        + "string was returned.";

    assertTrue("getFlights() should return exactly one flight", found.size() == 1);
    assertEquals(msg, expected, found.get(0));
  }

  @Test(timeout = TIMEOUT)
  public void testGetItineraries() {

    Driver.uploadFlightInfo(FLIGHTS2);

    Set<String> found = new HashSet<>(Driver.getItineraries("2016-09-30", "London", "Rome"));    
    
    String itinerary1 = 
        "UA490;2016-09-30 22:40;2016-10-01 01:59;Go Airline;London;Rome\n"
        + "532.99\n"
        + "3.32";
    String itinerary2 =
        "AC102;2016-09-30 16:37;2016-09-30 17:22;Go Airline;London;Paris\n"
            + "FA2499;2016-09-30 19:22;2016-09-30 22:40;Go Airline;Paris;Rome\n"
            + "580.00\n"
            + "6.05";    
    
    Set<String> expected = new HashSet<>();
    expected.add(itinerary1);
    expected.add(itinerary2);
    
    String msg =
        "Unexpected (incorrect or incorrectly formatted) itinerary information "
        + "output was returned.";
    
    assertTrue(msg, found.equals(expected));
  }
}