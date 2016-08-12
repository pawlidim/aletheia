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

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 * 
 * @author PAWLIDIM
 * 
 */
public final class CipherUtils {

	public static final String MESSAGE_DIGEST_ALGORITHM = "MD5";
	public static final String CIPHER_ALGORITHM = "RSA";
	public static final String CIPHER_EXTENDED_ALGORITHM = "RSA/ECB/PKCS1Padding";
	public static final String RANDOM_NUMBER_GENERATOR_ALGORITHM = "SHA1PRNG";

	/** Invisible default constructor */
	private CipherUtils() {
		super();
	}

	/**
	 * 
	 * @param properties
	 * @return
	 */
	public static String computeSignatureString(Properties properties) {
		byte[] signature = computeSignature(properties);
		return Converter.toString(signature);
	}

	/**
	 * 
	 * @param properties
	 * @return
	 */
	public static byte[] computeSignature(Properties properties) {
		if (properties != null && !properties.isEmpty()) {
			StringBuilder builder = new StringBuilder();
			ArrayList keys = new ArrayList<Object>(properties.keySet());
			Collections.sort(keys);
			for (Object key : keys) {
				Object value = properties.get(key);
				if (value != null) {
					builder.append(key);
					builder.append("=");
					builder.append(value);
					builder.append("\n");
				}
			}
			String pattern = builder.toString();
			if (!pattern.isEmpty()) {
				return computeSignature(Converter.getBytesIso8859(pattern));
			}
		}
		return null;
	}

	/**
	 * Compute signature
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] computeSignature(byte[] data) {
		if (ArrayUtils.isNotEmpty(data)) {
			try {
				MessageDigest messageDigest = MessageDigest.getInstance(MESSAGE_DIGEST_ALGORITHM);
				messageDigest.update(data, 0, data.length);
				return messageDigest.digest();
			} catch (Exception e) {
				throw new RuntimeException(
						"Cannot compute signature for " + MESSAGE_DIGEST_ALGORITHM + " message digest algorithm", e);
			}
		}
		return null;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static RSAPublicKey buildPublicKey(final String key) {
		if (StringUtils.isNotEmpty(key)) {
			try {
				byte[] bytes = Converter.toBytes(key);
				KeyFactory keyFactory = KeyFactory.getInstance(CIPHER_ALGORITHM);
				X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(bytes);
				return (RSAPublicKey) keyFactory.generatePublic(pubSpec);
			} catch (Exception e) {
				throw new RuntimeException("Cannot create " + CIPHER_ALGORITHM + " public key from " + key, e);
			}
		}
		return null;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static RSAPrivateKey buildPrivateKey(final String key) {
		if (StringUtils.isNotEmpty(key)) {
			try {
				byte[] bytes = Converter.toBytes(key);
				KeyFactory keyFactory = KeyFactory.getInstance(CIPHER_ALGORITHM);
				PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(bytes);
				return (RSAPrivateKey) keyFactory.generatePrivate(privSpec);
			} catch (Exception e) {
				throw new RuntimeException("Cannot create " + CIPHER_ALGORITHM + " private key from " + key, e);
			}
		}
		return null;
	}

	/**
	 * 
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] encrypt(byte[] data, Key key) {
		if (key != null && ArrayUtils.isNotEmpty(data)) {
			try {
				Cipher rsaCipher = Cipher.getInstance(CIPHER_EXTENDED_ALGORITHM);
				rsaCipher.init(Cipher.ENCRYPT_MODE, key);
				return rsaCipher.doFinal(data);
			} catch (Exception e) {
				throw new RuntimeException("Cannot encrypt, " + CIPHER_ALGORITHM + " error", e);
			}
		}
		return null;
	}

	/**
	 * 
	 * @param data
	 * @param key
	 * @return
	 */
	public static String encryptWithAES(final String data, final String key) {
		if (StringUtils.isNotEmpty(data) && StringUtils.isNotEmpty(key)) {
			SecretKeySpec keySpec = new SecretKeySpec(Converter.getBytesUtf8(key), "AES");
			StringCipher cipher = new StringCipher(keySpec, "AES");
			return cipher.encrypt(data);
		}
		return null;
	}

	/**
	 * 
	 * @param data
	 * @param key
	 * @return
	 */
	public static String decryptWithAES(final String data, final String key) {
		if (StringUtils.isNotEmpty(data) && StringUtils.isNotEmpty(key)) {
			SecretKeySpec keySpec = new SecretKeySpec(Converter.getBytesUtf8(key), "AES");
			StringCipher cipher = new StringCipher(keySpec, "AES");
			return cipher.decrypt(data);
		}
		return null;
	}

	/**
	 * 
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] decrypt(byte[] data, Key key) {
		if (key != null && ArrayUtils.isNotEmpty(data)) {
			try {
				Cipher rsaCipher = Cipher.getInstance(CIPHER_EXTENDED_ALGORITHM);
				rsaCipher.init(Cipher.DECRYPT_MODE, key);
				return rsaCipher.doFinal(data);
			} catch (Exception e1) {
				try {
					Cipher rsaCipher = Cipher.getInstance(CIPHER_ALGORITHM);
					rsaCipher.init(2, key);
					return rsaCipher.doFinal(data);
				} catch (Exception e2) {
					throw new RuntimeException("Cannot decrypt, " + CIPHER_ALGORITHM + " error", e2);
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public static KeyPair generateKeyPair() {
		KeyPairGenerator generator = null;
		SecureRandom secureRandom = null;
		try {
			generator = KeyPairGenerator.getInstance(CIPHER_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Could not generate key", e);
		}
		try {
			secureRandom = SecureRandom.getInstance(RANDOM_NUMBER_GENERATOR_ALGORITHM, "SUN");
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			// ignore exception
		}
		if (secureRandom == null) {
			generator.initialize(2048);
		} else {
			generator.initialize(2048, secureRandom);
		}
		return generator.generateKeyPair();
	}

	/**
	 * 
	 * @param keyPair
	 * @return
	 */
	public static String getPublicKey(KeyPair keyPair) {
		if (keyPair != null && keyPair.getPublic() != null) {
			return Converter.toString(keyPair.getPublic().getEncoded());
		}
		return null;
	}

	/**
	 * 
	 * @param keyPair
	 * @return
	 */
	public static String getPrivateKey(KeyPair keyPair) {
		if (keyPair != null && keyPair.getPrivate() != null) {
			return Converter.toString(keyPair.getPrivate().getEncoded());
		}
		return null;
	}

	public static String encryptPassword(final String password) {
		if (StringUtils.isEmpty(password)) {
			return null;
		}
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		return passwordEncryptor.encryptPassword(password);
	}

	/**
	 * Checks an unencrypted (plain) password against an encrypted one to see if
	 * they match.
	 * 
	 * @param plainPassword
	 *            the plain password to check.
	 * @param encryptedPassword
	 *            the digest against which to check the password.
	 * @return true if passwords match, false if not or empty
	 */
	public static boolean verifyPassword(final String plainPassword, final String encryptedPassword) {
		if (StringUtils.isEmpty(plainPassword) || StringUtils.isEmpty(encryptedPassword)) {
			return false;
		}
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		return passwordEncryptor.checkPassword(plainPassword, encryptedPassword);
	}

}
