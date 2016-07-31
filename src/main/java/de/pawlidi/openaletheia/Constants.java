package de.pawlidi.openaletheia;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 
 * @author PAWLIDIM
 * 
 */
public abstract class Constants {

	/** Define the software version */
	public static final String ALETHEIA_VERSION = "1.0.0";

	/** Define the private key */
	public static final String ALETHEIA_PRIVATE_KEY = "";
	/** Define the public key */
	public static final String ALETHEIA_PUBLIC_KEY = "";

	/**
	 * Defines an instance of SimpleDateFormat used for formatting the string
	 * representation of date (month/day/year)
	 */
	public static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("MM/dd/yyyy");
}
