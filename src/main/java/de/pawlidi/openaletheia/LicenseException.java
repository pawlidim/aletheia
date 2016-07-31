/**
 * 
 */
package de.pawlidi.openaletheia;

/**
 * 
 * @author PAWLIDIM
 *
 * Create: 19:36:53 2015
 * 
 */
public class LicenseException extends Exception {

	/**
	 * @param arg0
	 */
	public LicenseException(String message) {
		super(message);
	}

	/**
	 * @param arg0
	 */
	public LicenseException(Throwable exception) {
		super(exception);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public LicenseException(String message, Throwable exception) {
		super(message, exception);
	}

}
