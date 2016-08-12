/*
 * Copyright (C) 2016 Maximilian Pawlidi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.pawlidi.openaletheia.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.exec.OS;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author PAWLIDIM
 *
 */
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
