package de.pawlidi.openaletheia.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.exec.OS;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AletheiaUtilsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetMacAddress() {
		String macAdress = AletheiaUtils.getMacAddress();
		assertTrue(!macAdress.isEmpty());
	}

	@Test
	public void testGetJavaMacAddress() {
		String macAdress = AletheiaUtils.getJavaNetMacAddress();
		assertTrue(!macAdress.isEmpty());
	}

	@Test
	public void testGetWindowsMacAddress() {
		String macAdress = AletheiaUtils.getWindowsMacAddress();
		assertTrue(!macAdress.isEmpty());
	}

	@Test
	public void testGetLinuxMacAddress() {
		String macAdress = AletheiaUtils.getLinuxMacAddress();
		if (OS.isFamilyWindows()) {
			assertTrue(true);
		} else {
			assertTrue(!macAdress.isEmpty());
		}
	}

	@Test
	public void testRunCommand() {
		String response = AletheiaUtils.executeCommand("ipconfig", "/all");
		assertNotNull(response);
	}

	@Test
	public void testGetLocalAdress() {
		String ip = AletheiaUtils.getLocalIpAddressString();
		assertNotNull(ip);
	}
}
