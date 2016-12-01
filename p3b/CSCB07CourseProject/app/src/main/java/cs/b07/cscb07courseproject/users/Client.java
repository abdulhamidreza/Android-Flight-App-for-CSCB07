/**
 * 
 */
package cs.b07.cscb07courseproject.users;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cs.b07.cscb07courseproject.itinerary.Itinerary;
import cs.b07.cscb07courseproject.util.ValidDate;

/**
 * @author jungwon5
 * A client.
 */
public class Client extends User implements Serializable {

  /**
   * Initialize variables.
   */
  private String firstName;
  private String lastName;
  private String address;
  private String creditCard;
  private Date creditExpiry;
  private List<Itinerary> bookedItinerary;

  private static DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
  
  /**
   * Initializes the client class.
   * @param email the email of the client
   * @param password the password of the client
   * @param firstName the first name of the client
   * @param lastName the last name of the client
   * @param address the last name of the client
   * @param creditCard the credit card information of the client
   * @param creditExpiry the credit card expiry date of the client
   */
  public Client(String email, String password, String firstName, 
                String lastName, String address, String creditCard, String creditExpiry) throws ParseException{
    super(email, password);
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.creditCard = creditCard;
    setCreditExpiry(creditExpiry);
    this.bookedItinerary = new ArrayList<Itinerary>();
  }

  /**
   * Indicates the client's credit card expiry date.
   * @return the credit card expiry date
   */
  public Date getCreditExpiry() {
    return creditExpiry;
  }

  /**
   * Sets the client's credit card expiry date.
   * @param creditExpiry the client's credit card expiry date being set to
   */
  public void setCreditExpiry(String creditExpiry) throws ParseException {
    if (ValidDate.validDate(creditExpiry)) {
      this.creditExpiry = date.parse(creditExpiry);
    }
  }

  /**
   * Books an itinerary to the client.
   * @param toBook the itinerary being booked
   */
  public boolean book(Itinerary toBook) {
    Date today = new Date();
    if (creditExpiry.after(today)) {
      bookedItinerary.add(toBook);
      return true;
    }else{
      return false;
    }
  }

  /**
   * Indicates all itineraries the client has booked.
   * @return list of itineraries the client has booked
   */
  public List<Itinerary> getBooked() {
    return bookedItinerary;
  }

  /**
   * Indicates the client's first name.
   * @return the client's first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the client's first name.
   * @param firstName the first name being set to
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Indicates the client's last name.
   * @return the client's last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the client's last name.
   * @param lastName the last name being set to
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Indicates the client's address.
   * @return the client's address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets the client's address.
   * @param address the client's address being set to
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Indicates the client's credit card information.
   * @return the client's credit card information
   */
  public String getCreditCard() {
    return creditCard;
  }

  /**
   * Sets the client's credit card information.
   * @param creditCard the credit card information being set to
   */
  public void setCreditCard(String creditCard) {
    this.creditCard = creditCard;
  }

  /**
   * Indicates the itinerary the client has booked.
   * @return the itinerary the client has booked
   */
  public List<Itinerary> getBookedItinerary() {
    return bookedItinerary;
  }

  /**
   * Sets the client's booked itnerary.
   * @param bookedItinerary the booked itinerary being set to
   */
  public void setBookedItinerary(List<Itinerary> bookedItinerary) {
    this.bookedItinerary = bookedItinerary;
  }

  public byte[] getBytes() {
    // LastName;FirstNames;Email;Password;Address;CreditCardNumber;ExpiryDate
    return String.format("%s;%s;%s;%s;%s;%s;%s", getLastName(), getFirstName(), getEmail(), getPassword(), getAddress(), getCreditCard(), getCreditExpiry().toString()).getBytes();
  }


  public String toString() {
    return String.format("Email: %s\nFirst Name: %s\nLast Name: %s" +
            "\nAddress: %s\nCredit Card Number: %s\nCredit Card Expiry Date: %s" +
            "\nBooked Itinerary:\n%s",
            getEmail(), getFirstName(), getLastName(), getAddress(), getCreditCard(),
            date.format(getCreditExpiry()), getBookedItinerary());
  }
}
