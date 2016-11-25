package cs.b07.cscb07courseproject.users;

import java.io.Serializable;
import java.util.Date;

  /*
   * The admin class
   * @author jungwon5
   */
public class Admin extends User implements Serializable {

  /*
   * Initializes the admin class.
   * @param email the email of the admin
   * @param password the password of the admin
   */
  public Admin(String email, String password) {
    super(email, password);
  }

  /*
   * Indicates the target client's email
   * @param client the target client
   * @return the client's email
   */
  public String getClientEmail(Client client) {
    return client.getEmail();
  }

  /*
   * Indicates the target client's password
   * @param client the target client
   * @return the client's password
   */
  public String getClientPassword(Client client) {
    return client.getPassword();
  }

  /*
   * Indicates the target client's full name
   * @param client the target client
   * @return the client's full name
   */
  public String getClientName(Client client) {
    return (client.getFirstName() + " " + client.getLastName());
  }

  /*
   * Indicates the target client's address
   * @param client the target client
   * @return the client's address
   */
  public String getClientAddress(Client client) {
    return client.getAddress();
  }

  /*
   * Indicates the target client's credit card information
   * @param client the target client
   * @return the client's credit card information
   */
  public String getClientCC(Client client) {
    return client.getCreditCard();
  }

  /*
   * Indicates the target client's credit card expiry
   * @param client the target client
   * @return the client's credit card expiry
   */
  public Date getClientExpiry(Client client) {
    return client.getCreditExpiry();
  }

  /*
   * Sets the target client's email
   * @param client the target client
   * @param setEmail the email of the client being set to
   */
  public void setClientEmail(Client client, String setEmail) {
    client.setEmail(setEmail);
  }

  /*
   * Sets the target client's password
   * @param client the target client
   * @param setPassword the password of the client being set to
   */
  public void setClientPassword(Client client, String setPassword) {
    client.setPassword(setPassword);
  }

  /*
   * Sets the target client's full name
   * @param client the target client
   * @param setFirstName the first name of the client being set to
   * @param setLastName the last name of the client being set to
   */
  public void setClientName(Client client, String setFirstName, 
                            String setLastName) {
    client.setFirstName(setFirstName);
    client.setLastName(setLastName);
  }

  /*
   * Sets the target client's address
   * @param client the target client
   * @param setAddress the address of the client being set to
   */
  public void setClientAddress(Client client, String setAddress) {
    client.setAddress(setAddress);
  }

  /*
   * Sets the target client's credit card information
   * @param client the target client
   * @param setCC the credit card information of the client being set to
   */
  public void setClientCC(Client client, String setCC) {
    client.setCreditCard(setCC);
  }

  /*
   * Sets the target client's credit card expiry
   * @param client the target client
   * @param setAddress the credit card expiry of the client being set to
   */
  public void setClientExpiry(Client client, Date expiry) {
    client.setCreditExpiry(expiry);
  }

    @Override
    public String toString() {
      return "Admin [ email= " + getEmail() + " password= " + getPassword() + "]";
    }
  }

