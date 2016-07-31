package de.pawlidi.openaletheia.license;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.interfaces.RSAPublicKey;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import de.pawlidi.openaletheia.LicenseException;
import de.pawlidi.openaletheia.base.model.License;
import de.pawlidi.openaletheia.utils.CipherUtils;
import de.pawlidi.openaletheia.utils.Converter;
import de.pawlidi.openaletheia.utils.PropertiesUtils;

/**
 * 
 * @author PAWLIDIM
 * 
 *         Create: 23:36:13 2015
 * 
 */
final class LicenseLoader implements Serializable {

	private String publicKey;

	/**
	 * Constructor to construct new license loader.
	 */
	public LicenseLoader(final String publicKey) {
		super();
		this.publicKey = publicKey;
	}

	public License loadLicense(final String fileName) throws LicenseException {
		Properties properties = load(fileName);
		License license = LicenseProperties.createLicense(properties);
		return license;
	}

	public Properties load(final String fileName) throws LicenseException {
		if (StringUtils.isEmpty(fileName)) {
			throw new LicenseException("Invalid license file");
		}
		return load(new File(fileName));
	}

	public License loadLicense(File licenseFile) throws LicenseException {
		Properties properties = load(licenseFile);
		License license = LicenseProperties.createLicense(properties);
		return license;
	}

	public Properties load(File licenseFile) throws LicenseException {
		if (licenseFile == null) {
			throw new LicenseException("Invalid license file");
		}
		try {
			return load(FileUtils.openInputStream(licenseFile));
		} catch (IOException e) {
			throw new LicenseException("Could not load license file " + licenseFile.getName());
		}
	}

	public License loadLicense(InputStream licenseStream) throws LicenseException {
		Properties properties = load(licenseStream);
		License license = LicenseProperties.createLicense(properties);
		return license;
	}

	public Properties load(InputStream licenseStream) throws LicenseException {
		if (licenseStream == null) {
			throw new LicenseException("Given input stream cannot be null");
		}
		Properties properties = new Properties();
		try {
			properties.load(licenseStream);
		} catch (IOException e) {
			throw new LicenseException("Could not load license file");
		}
		return load(properties);
	}

	public Properties load(Properties properties) throws LicenseException {
		if (PropertiesUtils.isEmpty(properties)) {
			throw new LicenseException("Given properties cannot be empty or null");
		}

		if (StringUtils.isEmpty(publicKey)) {
			throw new LicenseException("Given public key cannot be empty or null");
		}

		Object signature = PropertiesUtils.removeValue(properties, LicenseProperties.SIGNATURE);
		if (signature == null) {
			throw new LicenseException("Invalid license file");
		}

		final String encryptedSignature = (String) signature;

		if (StringUtils.isNotEmpty(encryptedSignature)) {

			// decrypt signature
			byte[] signatureData = Converter.toBytes(encryptedSignature);
			RSAPublicKey rsaPublicKey = CipherUtils.buildPublicKey(publicKey);
			byte[] decryptedSignatureData = CipherUtils.decrypt(signatureData, rsaPublicKey);
			String decryptedSignature = Converter.toString(decryptedSignatureData);

			// compute other signature
			byte[] otherSignatureData = CipherUtils.computeSignature(properties);
			String otherSignature = Converter.toString(otherSignatureData);

			// compare both
			if (!StringUtils.equals(otherSignature, decryptedSignature)) {
				throw new LicenseException("License signature violation");
			}
		} else {
			throw new LicenseException("Invalid license file");
		}
		return properties;
	}

	/**
	 * Dispose reserved fields.
	 */
	public void dispose() {
		publicKey = null;
	}
}
