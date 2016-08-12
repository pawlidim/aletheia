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
package de.pawlidi.openaletheia.license;

import static org.junit.Assert.assertFalse;

import java.io.File;
import java.util.Properties;
import java.util.UUID;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.pawlidi.openaletheia.Constants;
import de.pawlidi.openaletheia.LicenseException;
import de.pawlidi.openaletheia.base.model.License;

/**
 * Test suite for the license generator class.
 * 
 * @author PAWLIDIM
 *
 *         Create: 00:53:31 2015
 * 
 */
public class LicenseGeneratorTest {

	private LicenseGenerator generator;
	private License license;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		generator = new LicenseGenerator(Constants.ALETHEIA_PRIVATE_KEY);
		license = new License();
		license.setProduct("Aletheia");
		license.setProductVersion("1.0.0");
		license.setUuid(UUID.randomUUID().toString());
		license.setMaxHost(null);
		license.setAddress(null);
		license.setOwner(null);
		license.setOperatingSystem(null);
		license.setCompany("PAWLIDI.DE");
		license.setMaxUser(null);
		license.setCreated(DateTime.now());
		license.setDueDate(DateTime.now().plusMonths(2));
		license.setDescription("License file for PAWLIDI.DE");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		generator.dispose();
		generator = null;
		license = null;
	}

	/**
	 * Test method for
	 * {@link de.pawlidi.aletheia.license.LicenseGenerator#generate(de.pawlidi.aletheia.base.model.License, java.lang.String)}
	 * .
	 * 
	 * @throws LicenseException
	 */
	@Test
	public void testGenerate() throws LicenseException {
		Properties properties = generator.generate(license);
		assertFalse(properties.isEmpty());
	}

	/**
	 * Test method for
	 * {@link de.pawlidi.aletheia.license.LicenseGenerator#storeLicense(java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws LicenseException
	 */
	@Test
	public void testStoreLicense() throws LicenseException {
		Properties properties = generator.generate(license);
		assertFalse(properties.isEmpty());
		generator.storeLicense("test.license", "Test license file");
		File file = new File("test.license");
		assertFalse(!file.exists());
	}

}
