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

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.interfaces.RSAPrivateKey;
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
 * 
 * @author PAWLIDIM
 * 
 *         Create: 23:27:42 2015
 * 
 */
final class LicenseGenerator implements Serializable {

	private String privateKey;
	private Properties properties;

	/**
	 * Constructor to construct new license generator.
	 */
	public LicenseGenerator(final String privateKey) {
		super();
		this.properties = new Properties();
		this.privateKey = privateKey;
	}

	/**
	 * Generate license properties for given license object.
	 * 
	 * @param license
	 * @return licenseProperties
	 * @throws LicenseException
	 */
	public Properties generate(License license) throws LicenseException {

		// clear properties
		properties.clear();

		if (!StringUtils.isEmpty(privateKey)) {

			// create license properties
			LicenseProperties.createProperties(properties, license);

			// create signature
			String signature = createSignature();

			// add signature
			PropertiesUtils.setStringProperty(properties, LicenseProperties.SIGNATURE, signature);
		}
		return properties;
	}

	private String createSignature() {
		// compute signature for properties
		String signature = CipherUtils.computeSignatureString(properties);
		// create rsa key object for private key
		RSAPrivateKey rsaPrivateKey = CipherUtils.buildPrivateKey(privateKey);
		// encrypt signature
		byte[] encryptedSignature = CipherUtils.encrypt(Converter.toBytes(signature), rsaPrivateKey);
		// covert encrypted signature to string
		signature = Converter.toString(encryptedSignature);
		return signature;
	}

	/**
	 * Store properties to file with given file name and file description.
	 * 
	 * @param fileName
	 * @param description
	 * @throws LicenseException
	 */
	public void storeLicense(final String fileName, final String description) throws LicenseException {
		if (properties.isEmpty()) {
			return;
		}

		OutputStream outputStream;

		try {
			outputStream = FileUtils.openOutputStream(new File(fileName));
		} catch (IOException openOutputStreamException) {
			throw new LicenseException("Could not write license information to file " + fileName);
		}

		try {
			properties.store(outputStream, description);
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Dispose reserved fields.
	 */
	public void dispose() {
		privateKey = null;
		properties.clear();
		properties = null;
	}
}
