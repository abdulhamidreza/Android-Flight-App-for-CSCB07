/**
 * 
 */
package cs.b07.cscb07courseproject.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cs.b07.cscb07courseproject.itinerary.Itinerary;

/**
 * @author jungwon5
 * A client.
 */
public class Client extends User {

  /**
   * Initialize variables.
   */
  private String firstName;
  private String lastName;
  private String address;
  private String creditCard;
  private Date creditExpiry;
  private List<Itinerary> bookedItinerary;
  
  /**
   * Initializes the client class.
   * @param email the email of the client
   * @param password the password of the client
   * @param firstName the first name of the client
   * @param lastName the last name of the client
   * @param address the last name of the client
   * @param creditCard the credit card information of the client
   * @param creditExpriy the credit card expiry date of the client
   */
  public Client(String email, String password, String firstName, 
                String lastName, String address, String creditCard, Date creditExpiry) {
    super(email, password);
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.creditCard = creditCard;
    this.creditExpiry = creditExpiry;
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
  public void setCreditExpiry(Date creditExpiry) {
    this.creditExpiry = creditExpiry;
  }

  /**
   * Books an itinerary to the client.
   * @param toBook the itinerary being booked
   */
  public void book(Itinerary toBook) {
    bookedItinerary.add(toBook);
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
   * @param the client's address being set to
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
   * @param the credit card information being set to
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
   * @param the booked itinerary being set to
   */
  public void setBookedItinerary(List<Itinerary> bookedItinerary) {
    this.bookedItinerary = bookedItinerary;
  }
}
