/**
 * 
 */
package de.pawlidi.openaletheia;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import de.pawlidi.openaletheia.generator.KeyGenerator;
import de.pawlidi.openaletheia.license.LicenseHandler;
import de.pawlidi.openaletheia.utils.CipherUtils;
import de.pawlidi.openaletheia.utils.Converter;

/**
 * Main class
 * 
 * @author PAWLIDIM
 *
 */
public final class Aletheia implements Serializable {

	private LicenseHandler licenseHandler;

	/**
	 * Default constructor to construct new aletheia object.
	 */
	private Aletheia() {
		super();
		licenseHandler = new LicenseHandler(Constants.ALETHEIA_PRIVATE_KEY, Constants.ALETHEIA_PUBLIC_KEY);
	}

	public Aletheia(final String path) throws LicenseException {
		this();
		licenseHandler.load(path);
	}

	public Aletheia(File licenseFile) throws LicenseException {
		this();
		licenseHandler.load(licenseFile);
	}

	public Aletheia(InputStream licenseStream) throws LicenseException {
		this();
		licenseHandler.load(licenseStream);
	}

	public void verifyLicense() throws LicenseException {
		// AletheiaBaseClient baseClient = new AletheiaBaseClient();
		// if (!baseClient.verifyLicense(licenseHandler.getLicense())) {
		// throw new LicenseException("License invalid!");
		// }
		AletheiaContext.instance().setLicense(licenseHandler.getLicense());
	}

	public void dispose() {
		licenseHandler.dispose();
	}

	/**
	 * 
	 * @param rootDirectory
	 * @return
	 */
	static boolean generateKeyFiles(final String rootDirectory) {
		if (StringUtils.isNotBlank(rootDirectory)) {
			return KeyGenerator.generateKeyFiles(rootDirectory);
		}
		return false;
	}

	/**
	 * 
	 * @param rootDirectory
	 * @param password
	 * @return
	 */
	static String encryptPassword(final String rootDirectory, String password) {
		if (StringUtils.isNotEmpty(rootDirectory) && StringUtils.isNotEmpty(password)) {
			final String privateKeyString = KeyGenerator.readPrivateKeyFile(rootDirectory);
			if (StringUtils.isNotEmpty(privateKeyString)) {
				RSAPrivateKey privateKey = CipherUtils.buildPrivateKey(privateKeyString);
				byte[] encryptedData = CipherUtils.encrypt(Converter.getBytesUtf8(password), privateKey);
				try {
					password = new String(encryptedData, Converter.UTF_8);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return password;
	}

	/**
	 * 
	 * @param rootDirectory
	 * @param password
	 * @return
	 */
	static String decryptPassword(final String rootDirectory, String password) {
		if (StringUtils.isNotEmpty(rootDirectory) && StringUtils.isNotEmpty(password)) {
			final String publicKeyString = KeyGenerator.readPublicKeyFile(rootDirectory);
			if (StringUtils.isNotEmpty(publicKeyString)) {
				RSAPublicKey publicKey = CipherUtils.buildPublicKey(publicKeyString);
				byte[] decryptedData = CipherUtils.decrypt(Converter.getBytesUtf8(password), publicKey);
				try {
					password = new String(decryptedData, Converter.UTF_8);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return password;
	}

	/**
	 * Use following arguments.
	 * <ol>
	 * <li>Create private and public key -> <code> 1 C:\temp</code></li>
	 * <li>Encrypt password -> <code> 2 C:\temp LfhPCfmr6AHP</code></li>
	 * <li>Decrypt password -> <code> 3 C:\temp wqefasdv234q4512tsdg</code></li>
	 * </ol>
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if (ArrayUtils.isNotEmpty(args)) {
			final String command = args[0];
			if ("1".equalsIgnoreCase(command)) {
				if (args.length > 1) {
					generateKeyFiles(args[1]);
				}
			} else if ("2".equalsIgnoreCase(command)) {
				if (args.length >= 3) {
					System.out.println(encryptPassword(args[1], args[2]));
				}
			} else if ("3".equalsIgnoreCase(command)) {
				if (args.length >= 3) {
					System.out.println(decryptPassword(args[1], args[2]));
				}
			}
		}
	}
}
