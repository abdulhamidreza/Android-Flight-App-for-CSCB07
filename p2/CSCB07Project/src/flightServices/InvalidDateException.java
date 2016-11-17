/**
 * 
 */
package flightServices;

/**
 * @author lucsteph
 *
 */
public class InvalidDateException extends Exception {

	private static final long serialVersionUID = 6195210167316260991L;

	public InvalidDateException() {
		// TODO Auto-generated constructor stub
	}
	
	public InvalidDateException (String message){
		super(message);
	}
}
