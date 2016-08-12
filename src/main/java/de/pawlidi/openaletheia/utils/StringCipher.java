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

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * The class is created with a key and can be used repeatedly to encrypt and
 * decrypt strings using specific key. Some of the more popular algorithms are:
 * Blowfish DES DESede PBEWithMD5AndDES PBEWithMD5AndTripleDES TripleDES
 * 
 * @author PAWLIDIM
 * 
 *         Create: 23:47:08 15.05.2015
 * 
 */
public final class StringCipher implements Serializable {

	private Cipher ecipher;
	private Cipher dcipher;

	/**
	 * Constructor used to create this object. Responsible for setting and
	 * initializing this object's encrypter and decrypter Chipher instances
	 * given a Secret Key and algorithm.
	 * 
	 * @param key
	 *            Secret Key used to initialize both the encrypter and decrypter
	 *            instances.
	 * @param algorithm
	 *            Which algorithm to use for creating the encrypter and
	 *            decrypter instances.
	 */
	public StringCipher(SecretKey key, String algorithm) {
		try {
			ecipher = Cipher.getInstance(algorithm);
			dcipher = Cipher.getInstance(algorithm);
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			dcipher.init(Cipher.DECRYPT_MODE, key);
		} catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
		}
	}

	/**
	 * Takes a single String as an argument and returns an Encrypted version of
	 * that String.
	 * 
	 * @param str
	 *            String to be encrypted
	 * @return <code>String</code> Encrypted version of the provided String
	 */
	public String encrypt(String data) {
		try {
			// Encode the string into bytes using utf-8
			byte[] utf8 = Converter.getBytesUtf8(data);

			// Encrypt
			byte[] enc = ecipher.doFinal(utf8);

			// Encode bytes to base64 to get a string
			return Converter.toString(enc);

		} catch (BadPaddingException | IllegalBlockSizeException e) {
		}
		return null;
	}

	/**
	 * Takes a encrypted String as argument, decrypts it and returns the
	 * decrypted String.
	 * 
	 * @param data
	 *            Encrypted String to be decrypted
	 * @return <code>String</code> Decrypted version of the provided String
	 */
	public String decrypt(String data) {

		try {

			// Decode base64 to get bytes
			byte[] dec = Converter.toBytes(data);

			// Decrypt
			byte[] utf8 = dcipher.doFinal(dec);

			// Decode using utf-8
			return new String(utf8, Converter.UTF_8);

		} catch (BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException e) {
		}
		return null;
	}
}
