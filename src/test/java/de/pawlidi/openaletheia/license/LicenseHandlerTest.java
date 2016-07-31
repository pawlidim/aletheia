package de.pawlidi.openaletheia.license;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.SystemUtils;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import de.pawlidi.openaletheia.LicenseException;
import de.pawlidi.openaletheia.base.model.License;
import de.pawlidi.openaletheia.utils.AletheiaUtils;
import de.pawlidi.openaletheia.utils.CipherUtils;

/**
 * 
 * 
 * @author PAWLIDIM
 *
 *         Create: 22:50:27 02.05.2015
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LicenseHandlerTest {

	private static final String LICENSE_FILE = "test.license";
	private static String publicKey;
	private static String privateKey;
	private static License license;
	private LicenseHandler licenseHandler;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		KeyPair keyPair = CipherUtils.generateKeyPair();
		publicKey = CipherUtils.getPublicKey(keyPair);
		privateKey = CipherUtils.getPrivateKey(keyPair);

		license = new License();
		license.setProduct("License product");
		license.setProductVersion("1.2.0");
		license.setUuid(UUID.randomUUID().toString());
		license.setMaxHost(3L);
		license.setAddress(AletheiaUtils.getMacAddress());
		license.setOwner(SystemUtils.USER_NAME);
		license.setOperatingSystem(SystemUtils.OS_NAME);
		license.setCompany("Company abc");
		license.setMaxUser(3L);
		license.setCreated(DateTime.now());
		license.setDescription("License file for license product");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		publicKey = null;
		privateKey = null;
	}

	@Before
	public void setUp() throws Exception {
		licenseHandler = new LicenseHandler(privateKey, publicKey, license);
	}

	@After
	public void tearDown() throws Exception {
		licenseHandler.dispose();
		licenseHandler = null;

	}

	@Test
	public void test1_SavePathLicense() throws LicenseException {
		licenseHandler.save(LICENSE_FILE);
		File file = new File(LICENSE_FILE);
		assertTrue(file.exists());
	}

	@Test
	public void test2_SaveFileLicense() throws LicenseException {
		licenseHandler.save(LICENSE_FILE, "MTService license");
		File file = new File(LICENSE_FILE);
		assertTrue(file.exists());
	}

	@Test(expected = LicenseException.class)
	public void test3_LoadString() throws LicenseException {
		licenseHandler.load(LICENSE_FILE);
		assertNotNull(licenseHandler.getLicense());
	}

	@Test(expected = LicenseException.class)
	public void test4_LoadFile() throws LicenseException {
		File file = new File(LICENSE_FILE);
		licenseHandler.load(file);
		assertNotNull(licenseHandler.getLicense());
	}

	@Test(expected = LicenseException.class)
	public void test5_LoadInputStream() throws LicenseException, IOException {
		licenseHandler.load(FileUtils.openInputStream(new File(LICENSE_FILE)));
		assertTrue(licenseHandler.getLicense() != null);
	}

	@Test(expected = LicenseException.class)
	public void test6_IsDateValid() throws LicenseException {
		licenseHandler.load(LICENSE_FILE);
		assertNotNull(licenseHandler.getLicense());
		assertFalse(licenseHandler.isDateValid());
	}

}
