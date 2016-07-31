/**
 * 
 */
package de.pawlidi.openaletheia.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author PAWLIDIM
 *
 */
public class ConverterTest {

	@Test
	public void getBytesUtf8Test() {
		byte[] utf8 = Converter.getBytesUtf8("MyTest");
		Assert.assertNotNull(utf8);
	}

	@Test
	public void getBytesIso8859Test() {
		byte[] bytes = Converter.getBytesIso8859("MyTest");
		Assert.assertNotNull(bytes);
	}

	@Test
	public void hexToStringTest() throws UnknownHostException, SocketException {

		InetAddress ip = InetAddress.getLocalHost();
		NetworkInterface network = NetworkInterface.getByInetAddress(ip);
		byte[] mac = network.getHardwareAddress();
		String javaMacAdress = Converter.hexToString(mac);
		Assert.assertNotNull(javaMacAdress);
	}

}
