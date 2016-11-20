package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Makes sure that inputed date is a valid date.
 * 
 * @author lucsteph
 *
 */
public class ValidDate {

  /**
   * Validates whether date is correctly inputed or not.
   * 
   * @param date
   *          Date that is going to be checked.
   * @return True if date is in a valid from, otherwise false.
   * 
   */
  public static boolean validate(String date) {

    if (date == null) {
      return false;
    }

    SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    dateTime.setLenient(false);

    try {
      // if not valid, it will throw ParseException
      dateTime.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
      return false;
    }
    
    return true;
  }
}
