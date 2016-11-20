package flightServices;

/**
 * An <code>Exception</code> used when sort attribute is not valid.
 * 
 * @author lucsteph
 *
 */
public class InvalidSortException extends Exception {

  private static final long serialVersionUID = 7049791920311886002L;

  /**
   * Creates a new <code>InvalidSortException</code>.
   */
  public InvalidSortException() {
    // TODO Auto-generated constructor stub
  }

  /**
   * Creates a new <code>InvalidSortException</code> with the given message.
   * 
   * @param message
   *          the message for the new <code>InvalidSortException</code>.
   */
  public InvalidSortException(String message) {
    super(message);
  }
}
