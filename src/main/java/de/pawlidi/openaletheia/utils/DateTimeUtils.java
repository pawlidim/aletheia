package de.pawlidi.openaletheia.utils;

import java.io.IOException;
import java.net.InetAddress;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

public class DateTimeUtils {

	public static long getCurrentTime() {
		try {
			NTPUDPClient timeClient = new NTPUDPClient();
			InetAddress inetAddress = InetAddress.getByName("time-c.nist.gov");
			TimeInfo timeInfo = timeClient.getTime(inetAddress);
			return timeInfo.getReturnTime();
		} catch (IOException e) {
			// TODO: handle exception
		}
		return -1;
	}
}
