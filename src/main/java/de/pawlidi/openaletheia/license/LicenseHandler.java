package de.pawlidi.openaletheia.license;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

import de.pawlidi.openaletheia.LicenseException;
import de.pawlidi.openaletheia.base.model.License;
import de.pawlidi.openaletheia.utils.DateTimeUtils;

/**
 * This class provides license handling methods.
 * 
 * @author PAWLIDIM
 * 
 *         Create: 23:47:53 2015
 * 
 */
public final class LicenseHandler implements Serializable {

	private LicenseGenerator generator;
	private LicenseLoader loader;
	private License license;

	/**
	 * Constructor to construct new license handler with private and public key.
	 * 
	 * @param privateKey
	 * @param publicKey
	 */
	public LicenseHandler(final String privateKey, final String publicKey) {
		super();
		this.generator = new LicenseGenerator(privateKey);
		this.loader = new LicenseLoader(publicKey);
	}

	/**
	 * Constructor to construct new license handler with private, public key and
	 * the license.
	 * 
	 * @param privateKey
	 * @param publicKey
	 * @param license
	 */
	public LicenseHandler(final String privateKey, final String publicKey, License license) {
		this(privateKey, publicKey);
		this.license = license;
	}

	public LicenseHandler load(final String fileName) throws LicenseException {
		this.license = loader.loadLicense(fileName);
		if (!isDateValid()) {
			throw new LicenseException("License date is invalid!");
		}
		return this;
	}

	public LicenseHandler load(File licenseFile) throws LicenseException {
		this.license = loader.loadLicense(licenseFile);
		if (!isDateValid()) {
			throw new LicenseException("License date is invalid!");
		}
		return this;
	}

	public LicenseHandler load(InputStream licenseStream) throws LicenseException {
		this.license = loader.loadLicense(licenseStream);
		if (!isDateValid()) {
			throw new LicenseException("License date is invalid!");
		}
		return this;
	}

	public void save(final String fileName) throws LicenseException {
		save(fileName, "");
	}

	public void save(final String fileName, final String description) throws LicenseException {
		generator.generate(license);
		generator.storeLicense(fileName, description);
	}

	/**
	 * Getter to get the license
	 * 
	 * @return the license
	 */
	public License getLicense() {
		return license;
	}

	/**
	 * Setter to set the license
	 * 
	 * @param license
	 *            the license to set
	 */
	public void setLicense(License license) {
		this.license = license;
	}

	/**
	 * Checks the license due date property.
	 * 
	 * @return true, if due date is after now.
	 */
	public boolean isDateValid() {
		if (license.getDueDate() != null) {
			return license.getDueDate().isAfter(DateTimeUtils.getCurrentTime());
		}
		return false;
	}

	/**
	 * Dispose reserved fields.
	 */
	public void dispose() {
		generator.dispose();
		generator = null;
		loader.dispose();
		loader = null;
	}
}
