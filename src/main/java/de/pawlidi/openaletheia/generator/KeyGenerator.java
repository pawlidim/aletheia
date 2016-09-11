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
package de.pawlidi.openaletheia.generator;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import de.pawlidi.openaletheia.utils.CipherUtils;
import de.pawlidi.openaletheia.utils.Converter;

/**
 * 
 * 
 * @author PAWLIDIM
 * 
 *         Create: 23:36:54 2015
 * 
 */
public final class KeyGenerator {

	/** Key file extension. */
	public static final String KEY_FILE_EXTENSION = "key";

	/** Public key file name */
	public static final String PUBLIC_KEY_FILE = "public." + KEY_FILE_EXTENSION;

	/** Private key file name */
	public static final String PRIVATE_KEY_FILE = "private." + KEY_FILE_EXTENSION;

	/** Key specification file extension. */
	public static final String KEYSPEC_FILE_EXTENSION = "keyspec";

	/** Public key specification file name */
	public static final String PUBLIC_KEYSPEC_FILE = "public." + KEYSPEC_FILE_EXTENSION;

	/** Private key specification file name */
	public static final String PRIVATE_KEYSPEC_FILE = "private." + KEYSPEC_FILE_EXTENSION;

	/** Invisible constructor. */
	private KeyGenerator() {
		super();
	}

	/**
	 * Generate key file into given root directory.
	 * 
	 * @param rootDirectory
	 *            - root directory
	 * 
	 * @return true, if successful false otherwise
	 */
	public static boolean generateKeyFiles(String rootDirectory) {
		boolean successful = false;
		if (!StringUtils.isBlank(rootDirectory)) {
			File rootDir = new File(rootDirectory);
			if (rootDir.exists() && rootDir.isDirectory()) {
				// generate key pair
				final KeyPair keyPair = CipherUtils.generateKeyPair();
				final String publicKey = CipherUtils.getPublicKey(keyPair);
				// System.out.println("Public key : " + publicKey);
				final String privateKey = CipherUtils.getPrivateKey(keyPair);
				// System.out.println("Private key : " + privateKey);
				successful = createKeyFile(rootDir, publicKey, PUBLIC_KEY_FILE);
				if (!successful) {
					return successful;
				}
				successful = createKeyFile(rootDir, privateKey, PRIVATE_KEY_FILE);
			}
		}
		return successful;
	}

	/**
	 * Creates key file for given root directory, generated key and file name.
	 * 
	 * @param rootDir
	 *            - root directory to store in the key file
	 * @param key
	 *            - generated key
	 * @param fileName
	 *            - file name to store the key
	 */
	public static boolean createKeyFile(File rootDir, String key, String fileName) {
		try {
			FileUtils.writeStringToFile(new File(rootDir, fileName), key, Converter.UTF_8);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public static String readPrivateKeyFile(final String directory) {
		return readKeyFile(directory, true);
	}

	public static String readPublicKeyFile(final String directory) {
		return readKeyFile(directory, false);
	}

	public static String readKeyFile(final String directory, final boolean privateKeyFile) {
		if (!StringUtils.isBlank(directory)) {
			File rootDir = new File(directory);
			if (rootDir.exists() && rootDir.isDirectory()) {
				Collection<File> files = FileUtils.listFiles(rootDir, new String[] { KEY_FILE_EXTENSION }, false);
				if (!files.isEmpty()) {
					for (File file : files) {
						if (file.getName().endsWith((privateKeyFile ? PRIVATE_KEY_FILE : PUBLIC_KEY_FILE))) {
							try {
								return FileUtils.readFileToString(file, Converter.UTF_8);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}

				}
			}
		}
		return null;
	}

	public static boolean flushPublicKeySpec(final String directory, RSAPublicKey publicKey) {
		if (StringUtils.isBlank(directory) || !new File(directory).isDirectory() || publicKey == null) {
			return false;
		}
		return writeKeySpec(new File(directory, PUBLIC_KEYSPEC_FILE), publicKey.getModulus(),
				publicKey.getPublicExponent());

	}

	public static boolean flushPrivateKeySpec(final String directory, RSAPrivateKey privateKey) {
		if (StringUtils.isBlank(directory) || !new File(directory).isDirectory() || privateKey == null) {
			return false;
		}
		return writeKeySpec(new File(directory, PRIVATE_KEYSPEC_FILE), privateKey.getModulus(),
				privateKey.getPrivateExponent());
	}

	private static boolean writeKeySpec(File file, BigInteger mod, BigInteger exp) {
		boolean successful = false;
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
			try {
				outputStream.writeObject(mod);
				outputStream.writeObject(exp);
				successful = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (outputStream != null) {
					outputStream.close();
					outputStream = null;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return successful;
	}

	public static RSAPublicKeySpec readPublicKeySpec(final String directory) {
		if (StringUtils.isBlank(directory) || !new File(directory).isDirectory()) {
			return null;
		}
		RSAPublicKeySpec publicKeySpec = null;
		ObjectInputStream inputStream = null;
		try {
			inputStream = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(new File(directory, PUBLIC_KEYSPEC_FILE))));
			try {
				BigInteger m = (BigInteger) inputStream.readObject();
				BigInteger e = (BigInteger) inputStream.readObject();
				publicKeySpec = new RSAPublicKeySpec(m, e);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (inputStream != null) {
					inputStream.close();
					inputStream = null;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return publicKeySpec;
	}

	public static RSAPrivateKeySpec readPrivateKeySpec(final String directory) {
		if (StringUtils.isBlank(directory) || !new File(directory).isDirectory()) {
			return null;
		}
		RSAPrivateKeySpec privateKeySpec = null;
		ObjectInputStream inputStream = null;
		try {
			inputStream = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(new File(directory, PRIVATE_KEYSPEC_FILE))));
			try {
				BigInteger m = (BigInteger) inputStream.readObject();
				BigInteger e = (BigInteger) inputStream.readObject();
				privateKeySpec = new RSAPrivateKeySpec(m, e);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (inputStream != null) {
					inputStream.close();
					inputStream = null;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return privateKeySpec;
	}

}
