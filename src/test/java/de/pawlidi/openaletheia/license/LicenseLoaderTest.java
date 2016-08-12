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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.SystemUtils;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.pawlidi.openaletheia.Constants;
import de.pawlidi.openaletheia.LicenseException;
import de.pawlidi.openaletheia.base.model.License;
import de.pawlidi.openaletheia.utils.AletheiaUtils;
import de.pawlidi.openaletheia.utils.PropertiesUtils;

/**
 * @author PAWLIDIM
 * 
 */
public class LicenseLoaderTest {

	private LicenseLoader licenseLoader;
	private Properties properties;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		licenseLoader = new LicenseLoader(Constants.ALETHEIA_PUBLIC_KEY);
		properties = new Properties();

		LicenseGenerator generator = new LicenseGenerator(Constants.ALETHEIA_PRIVATE_KEY);
		License license = new License();
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
		generator.generate(license);
		generator.storeLicense("test.license", "My Test license file");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		licenseLoader.dispose();
		licenseLoader = null;
		properties.clear();
		properties = null;
	}

	/**
	 * Test method for
	 * {@link de.pawlidi.aletheia.license.LicenseLoader#load(java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws LicenseException
	 */
	@Test
	public void testLoadStringString() throws LicenseException {
		properties = licenseLoader.load("test.license");
		assertNotNull(properties);
	}

	/**
	 * Test method for
	 * {@link de.pawlidi.aletheia.license.LicenseLoader#load(java.io.File, java.lang.String)}
	 * .
	 * 
	 * @throws LicenseException
	 */
	@Test
	public void testLoadFileString() throws LicenseException {
		properties = licenseLoader.load(new File("test.license"));
		assertNotNull(properties);
	}

	/**
	 * Test method for
	 * {@link de.pawlidi.aletheia.license.LicenseLoader#load(java.io.InputStream, java.lang.String)}
	 * .
	 * 
	 * @throws IOException
	 * @throws LicenseException
	 */
	@Test
	public void testLoadInputStreamString() throws IOException, LicenseException {
		InputStream inputStream = FileUtils.openInputStream(new File("test.license"));
		properties = licenseLoader.load(inputStream);
		assertNotNull(properties);
	}

	/**
	 * Test method for
	 * {@link de.pawlidi.aletheia.license.LicenseLoader#load(java.util.Properties, java.lang.String)}
	 * .
	 * 
	 * @throws IOException
	 * @throws LicenseException
	 */
	@Test
	public void testLoadPropertiesString() throws IOException, LicenseException {
		InputStream inputStream = FileUtils.openInputStream(new File("test.license"));
		properties.load(inputStream);
		properties = licenseLoader.load(properties);
		assertTrue(PropertiesUtils.isNotEmpty(properties));
	}

}
