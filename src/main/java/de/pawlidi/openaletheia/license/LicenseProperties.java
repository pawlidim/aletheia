package de.pawlidi.openaletheia.license;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.BooleanUtils;

import de.pawlidi.openaletheia.base.model.License;
import de.pawlidi.openaletheia.base.model.User;
import de.pawlidi.openaletheia.utils.PropertiesUtils;

/**
 * Defines the license properties.
 * 
 * @author PAWLIDIM
 * 
 */
public abstract class LicenseProperties {

	/** Defines label for the product name */
	public static final String PRODUCT = "product";
	/** Defines label for the product version */
	public static final String VERSION = "version";
	/** Defines label for the creation date */
	public static final String CREATED = "created";
	/** Defines label for the modification date */
	public static final String MODIFIED = "modified";
	/** Defines label for the mac adress */
	public static final String ADDRESS = "address";
	/** Defines label for the host size */
	public static final String HOST = "host";
	/** Defines label for the user size */
	public static final String MAX_USER = "max_user";
	/** Defines label for the user */
	public static final String USER = "user";
	/** Defines label for the company name */
	public static final String COMPANY = "company";
	/** Defines label for the license description */
	public static final String DESCRIPTION = "description";
	/** Defines label for the signature */
	public static final String SIGNATURE = "signature";
	/** Defines label for the base ip */
	public static final String BASE = "base";
	/** Defines label for the due date */
	public static final String DUE_DATE = "due_date";
	/** Defines label for remote validation */
	public static final String REMOTE = "remote";
	/** Defines label for the license owner */
	public static final String OWNER = "owner";
	/** Defines label for the operating system */
	public static final String OS = "os";

	public static License createLicense(Properties properties) {
		return createLicense(properties, new License());
	}

	/**
	 * 
	 * @param properties
	 * @param license
	 * @return
	 */
	public static License createLicense(Properties properties, License license) {
		if (PropertiesUtils.isEmpty(properties) || license == null) {
			return null;
		}
		license.setProduct(PropertiesUtils.getStringProperty(properties, LicenseProperties.PRODUCT));
		license.setProductVersion(PropertiesUtils.getStringProperty(properties, LicenseProperties.VERSION));
		license.setCreated(PropertiesUtils.getDateProperty(properties, LicenseProperties.CREATED));
		license.setModified(PropertiesUtils.getDateProperty(properties, LicenseProperties.MODIFIED));
		license.setAddress(PropertiesUtils.getStringProperty(properties, LicenseProperties.ADDRESS));
		license.setMaxHost(PropertiesUtils.getLongProperty(properties, LicenseProperties.HOST));
		license.setMaxUser(PropertiesUtils.getLongProperty(properties, LicenseProperties.MAX_USER));
		license.setCompany(PropertiesUtils.getStringProperty(properties, LicenseProperties.COMPANY));
		license.setDescription(PropertiesUtils.getStringProperty(properties, LicenseProperties.DESCRIPTION));
		license.setDueDate(PropertiesUtils.getDateProperty(properties, LicenseProperties.DUE_DATE));
		license.setRemote(PropertiesUtils.getBooleanProperty(properties, LicenseProperties.REMOTE));
		license.setSignature(PropertiesUtils.getStringProperty(properties, LicenseProperties.SIGNATURE));
		license.setOwner(PropertiesUtils.getStringProperty(properties, LicenseProperties.OWNER));
		license.setOperatingSystem(PropertiesUtils.getStringProperty(properties, LicenseProperties.OS));

		List<String> userProperies = PropertiesUtils.getListProperty(properties, LicenseProperties.USER);
		if (userProperies != null && !userProperies.isEmpty()) {
			List<User> users = new ArrayList<User>(0);
			for (String userProperty : userProperies) {
				users.add(new User().toObject(userProperty));
			}
			license.setUsers(users);
		}
		return license;

	}

	public static Properties createProperties(License license) {
		return createProperties(new Properties(), license);
	}

	public static Properties createProperties(Properties properties, License license) {

		// check parameter
		if (license == null) {
			return null;
		}

		// write data
		PropertiesUtils.setStringProperty(properties, LicenseProperties.PRODUCT, license.getProduct());
		PropertiesUtils.setStringProperty(properties, LicenseProperties.VERSION, license.getProductVersion());
		PropertiesUtils.setDateProperty(properties, LicenseProperties.CREATED, license.getCreated());
		PropertiesUtils.setDateProperty(properties, LicenseProperties.MODIFIED, license.getModified());
		PropertiesUtils.setStringProperty(properties, LicenseProperties.ADDRESS, license.getAddress());
		PropertiesUtils.setObjectProperty(properties, LicenseProperties.HOST, license.getMaxHost());
		PropertiesUtils.setObjectProperty(properties, LicenseProperties.MAX_USER, license.getMaxUser());
		PropertiesUtils.setStringProperty(properties, LicenseProperties.COMPANY, license.getCompany());
		PropertiesUtils.setStringProperty(properties, LicenseProperties.DESCRIPTION, license.getDescription());
		PropertiesUtils.setStringProperty(properties, LicenseProperties.OWNER, license.getOwner());
		PropertiesUtils.setStringProperty(properties, LicenseProperties.OS, license.getOperatingSystem());
		PropertiesUtils.setDateProperty(properties, LicenseProperties.DUE_DATE, license.getDueDate());
		if (BooleanUtils.isFalse(license.getRemote())) {
			PropertiesUtils.setObjectProperty(properties, LicenseProperties.REMOTE, license.getRemote());
		}

		List<String> users = new ArrayList<String>(0);
		for (User user : license.getUsers()) {
			users.add(user.toString());
		}
		PropertiesUtils.setListProperty(properties, LicenseProperties.USER, users);

		return properties;
	}

}
