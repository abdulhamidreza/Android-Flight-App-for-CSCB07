package users;

import java.util.Date;

public class Admin extends User {

  public Admin(String email, String password) {
    super(email, password);
  }

  public String getClientEmail(Client client) {
    return client.getEmail();
  }

  public String getClientPassword(Client client) {
    return client.getPassword();
  }

  public String getClientName(Client client) {
    return (client.getFirstName() + " " + client.getLastName());
  }

  public String getClientAddress(Client client){
    return client.getAddress();
  }

  public String getClientCC(Client client) {
    return client.getCreditCard();
  }
  
  public Date getClientExpiry(Client client) {
	  
	  return client.getCreditExpiry();
	  
  }

  public void setClientEmail(Client client, String setEmail) {
    client.setEmail(setEmail);
  }

  public void setClientPassword(Client client, String setPassword) {
    client.setPassword(setPassword);
  }

  public void setClientName(Client client, String setFirstName, 
                            String setLastName) {
    client.setFirstName(setFirstName);
    client.setLastName(setLastName);
  }

  public void setClientAddress(Client client, String setAddress){
    client.setAddress(setAddress);
  }

  public void setClientCC(Client client, String setCC){
    client.setCreditCard(setCC);
  }
  
 public void setClientExpiry(Client client, Date expiry) {
	  
	  client.setCreditExpiry(expiry);
	  
  }
}
