package de.pawlidi.openaletheia.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;

import de.pawlidi.openaletheia.utils.exec.ProcessExecutor;

/**
 * 
 * @author PAWLIDIM
 *
 */
public final class AletheiaUtils {

	public static final String MAC_ADDRESS_REGEX = "^([[:xdigit:]]{2}){5}[[:xdigit:]]{2}$";

	/** Invisible default constructor */
	private AletheiaUtils() {
		super();
	}

	/**
	 * Returns current mac adress
	 * 
	 * @return macAdress
	 */
	public static String getMacAddress() {
		String macAddress = getJavaNetMacAddress();
		if (macAddress == null) {
			if (SystemUtils.IS_OS_WINDOWS) {
				macAddress = getWindowsMacAddress();
			} else if (SystemUtils.IS_OS_LINUX) {
				macAddress = getLinuxMacAddress();
			}
			if (macAddress == null) {
				throw new RuntimeException("Cannot read MAC address in operating system");
			}
		}
		macAddress = normalizeMacAddress(macAddress);
		return macAddress;
	}

	/**
	 * Read java networt mac adress
	 * 
	 * @return
	 */
	public static String getJavaNetMacAddress() {
		String javaMacAdress = null;
		try {
			InetAddress ip = InetAddress.getLocalHost();

			if (ip == null) {
				return null;
			}

			NetworkInterface network = NetworkInterface.getByInetAddress(ip);

			if (network == null) {
				return null;
			}

			byte[] mac = network.getHardwareAddress();

			javaMacAdress = Converter.hexToString(mac);

		} catch (UnknownHostException e) {
			// ignore exception
		} catch (SocketException e) {
			// ignore exception
		}
		return javaMacAdress;
	}

	/**
	 * 
	 * @return
	 */
	public static String getWindowsMacAddress() {
		final String ipConfigResponse = executeCommand("ipconfig", "/all");
		if (ipConfigResponse == null) {
			return null;
		}

		String localHost = getLocalhostAddress();
		if (localHost == null) {
			return null;
		}

		String lastMacAddressCandidate = null;
		StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse, "\n");
		while (tokenizer.hasMoreTokens()) {
			String line = tokenizer.nextToken().trim();

			if ((line.indexOf(localHost) >= 0) && (lastMacAddressCandidate != null)) {
				return lastMacAddressCandidate;
			}

			int colon = line.indexOf(":");
			if (colon <= 0) {
				continue;
			}
			String candidate = line.substring(colon + 1).trim();
			if (isMacAddressCandidate(candidate)) {
				lastMacAddressCandidate = normalizeMacAddress(candidate);
			}
		}
		return lastMacAddressCandidate;
	}

	private static String getLocalhostAddress() {
		String localhostAdress = null;
		try {
			final InetAddress localHost = InetAddress.getLocalHost();
			if (localHost != null) {
				localhostAdress = localHost.getHostAddress();
			}
		} catch (UnknownHostException ex) {
			// ignore exception
		}
		return localhostAdress;
	}

	private static boolean isMacAddressCandidate(String macAddress) {
		if (StringUtils.isBlank(macAddress)) {
			return false;
		}
		macAddress = macAddress.trim();
		if (!macAddress.matches(MAC_ADDRESS_REGEX)) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @return
	 */
	public static String getLinuxMacAddress() {
		String ipConfigResponse = executeCommand("ifconfig");
		if (ipConfigResponse == null) {
			ipConfigResponse = executeCommand("/sbin/ifconfig");
			if (ipConfigResponse == null) {
				return null;
			}

		}

		String localHost = getLocalhostAddress();
		if (localHost == null) {
			return null;
		}

		StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse, "\n");
		String lastMacAddress = null;
		while (tokenizer.hasMoreTokens()) {
			String line = tokenizer.nextToken().trim();
			boolean containsLocalHost = line.indexOf(localHost) >= 0;

			if ((containsLocalHost) && (lastMacAddress != null)) {
				return lastMacAddress;
			}

			int macAddressPosition = line.indexOf("HWaddr");
			if (macAddressPosition > 0) {
				String macAddressCandidate = line.substring(macAddressPosition + 6).trim();
				if (isMacAddressCandidate(macAddressCandidate)) {
					lastMacAddress = normalizeMacAddress(macAddressCandidate);
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param command
	 * @return
	 */
	public static String executeCommand(final String command, String... args) {
		return ProcessExecutor.executeCommand(command, args);
	}

	/**
	 * 
	 * @param macAddress
	 * @return
	 */
	public static String normalizeMacAddress(String macAddress) {
		if (StringUtils.isNotBlank(macAddress)) {
			if (isMacAddressCandidate(macAddress)) {
				macAddress = macAddress.trim();
				StringBuilder addressBuilder = new StringBuilder();
				for (int i = 0; i < macAddress.length(); i++) {
					char part = macAddress.charAt(i);
					addressBuilder.append(Character.toUpperCase(part));
					if (i % 2 == 0) {
						addressBuilder.append("-");
					}
				}
				macAddress = addressBuilder.toString();
			}
		}
		return macAddress;
	}

	/**
	 * 
	 * @return
	 * @throws UnknownHostException
	 */
	public static InetAddress getLocalIpAddress() throws UnknownHostException {
		try {
			InetAddress localAddress = null;
			// load all existed network interfaces
			for (Enumeration<?> networkInterfaces = NetworkInterface.getNetworkInterfaces(); networkInterfaces
					.hasMoreElements();) {
				NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();

				for (Enumeration<?> ipAddresses = networkInterface.getInetAddresses(); ipAddresses.hasMoreElements();) {
					InetAddress ipAddress = (InetAddress) ipAddresses.nextElement();
					if (!ipAddress.isLoopbackAddress()) {
						if (ipAddress.isSiteLocalAddress()) {
							return ipAddress;
						} else if (localAddress == null) {
							localAddress = ipAddress;
						}
					}
				}
			}
			if (localAddress != null) {
				return localAddress;
			}
			// try to get localhost address
			localAddress = InetAddress.getLocalHost();
			if (localAddress == null) {
				throw new UnknownHostException("Could not load localhost ip address");
			}
			return localAddress;
		} catch (Exception e) {
			UnknownHostException unknownHostException = new UnknownHostException(
					"Could not load localhost ip address " + e);
			unknownHostException.initCause(e);
			throw unknownHostException;
		}
	}

	public static String getLocalIpAddressString() {
		try {
			return getLocalIpAddress().getHostAddress();
		} catch (UnknownHostException e) {
			return null;
		}
	}
}
