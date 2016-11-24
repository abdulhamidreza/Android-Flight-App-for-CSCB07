/**
 * 
 */
package cs.b07.cscb07courseproject.users;

/**
 * A user.
 * @author jungwon5
 */
public abstract class User {

  private String email;
  private String password;

  /**
   * Initializes the User's information.
   * @param email the email of the user
   * @param password the password of the user
   */
  public User(String email, String password) {
    this.email = email;
    this.password = password;
  }

  /**
   * Determines if entered login information is valid.
   * @param otherEmail the email entered
   * @param otherPassword the password entered
   * @return returns true if login information is valid, and false if invalid
   */
  public boolean login(String otherEmail, String otherPassword) {
    if (otherEmail.equals(email) && otherPassword.equals(password)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns the user's email.
   * @return returns the email of the user
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the user's email.
   * @param email the email being set to
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Returns the user's password.
   * @return returns the password of the user
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the user's password.
   * @param password the password being set to
   */
  public void setPassword(String password) {
    this.password = password;
  }
}
