/**
 * 
 */
package Users;

/**
 * @author jungwon5
 *
 */
public abstract class User {
  private String email;

private String password;

  public User(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public boolean login(String otherEmail, String otherPassword) {
    if (otherEmail.equals(email) && otherPassword.equals(password)) {
      return true;
    } else {
      return false;
    }
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
