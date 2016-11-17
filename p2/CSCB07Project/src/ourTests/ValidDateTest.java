/**
 * 
 */
package ourTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import flightServices.InvalidDateException;
import util.ValidDate;

/**
 * @author lucsteph
 *
 */
public class ValidDateTest {
	
	String[] date;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		date = new String[10];
		date[0] = "2016-30-20 24:05"; // not valid cause month
		date[1] = "2016-10-20 26:05"; // not valid cause hour
		date[2] = "2016-10-9 1:80";   // not valid cause minute
		date[3] = "2016-05-80 2:05";  // not valid cause day
		date[4] = "2016-05-20 24:05"; // valid
		date[5] = "2016-02-29 24:05"; // valid
		date[6] = "2016-02-30 24:05"; // not valid cause feb dont have 30 days
		date[7] = "2017-02-29 24:05"; // not valid cause feb isnt leap year
		date[8] = "2017-02-28 24:05"; // valid
		date[9] = "2016-06-20 24:05"; // valid
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link util.ValidDate#validate(java.lang.String)}.
	 */
	@Test
	public void testValidate() {
		for (int i = 0; i < 10; i++) {
			try {
				System.out.println(ValidDate.validate(date[i]));
			} catch (InvalidDateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
