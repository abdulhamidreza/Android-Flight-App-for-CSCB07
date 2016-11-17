/**
 * 
 */
package users;

import java.util.ArrayList;
import java.util.List;

import itinerary.Itinerary;

/**
 * @author jungwon5
 *
 */
public class Client extends User {
  private String firstName;
  private String lastName;
  private String address;
  private String creditCard;
  private List<Itinerary> bookedItinerary;
  
  /**
   * @param email
   * @param password
   */
  public Client(String email, String password, String firstName, 
                String lastName, String address, String creditCard) {
    super(email, password);
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.creditCard = creditCard;
    this.bookedItinerary = new ArrayList<Itinerary>();
  }

  public void book(Itinerary toBook){
    bookedItinerary.add(toBook);
  }

  public List<Itinerary> getBooked(){
    return bookedItinerary;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCreditCard() {
    return creditCard;
  }

  public void setCreditCard(String creditCard) {
    this.creditCard = creditCard;
  }

  public List<Itinerary> getBookedItinerary() {
    return bookedItinerary;
  }

  public void setBookedItinerary(List<Itinerary> bookedItinerary) {
    this.bookedItinerary = bookedItinerary;
  }
}
