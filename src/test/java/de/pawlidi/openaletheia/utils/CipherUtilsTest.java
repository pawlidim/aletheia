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

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.apache.commons.lang.ArrayUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * CipherUtilsTest core test case.
 * 
 * @author PAWLIDIM
 *
 */
public class CipherUtilsTest {

	private static final String key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCMcuhEC+hTTRFoBR/KOrs9Ss/HCn33HAyvfvgyCRicUdlXIheq0TcFDdhxTgEhIUbnsMPrLjlpgsqbZ6i7VsalAn/bK23XHaeo1czxy5/bsbxXzpHs92Rzfliv9No6hVTtMGE24/MTM8lkCTiLgwo3LjbRRd3UdGNU+8qJa/8Q+nRiHKNmWQXDaqpUfeYE2nEXky6acl9jykXaWOmvG3hrv7vSwrY3bqfTCI+fGB/vEoZGREBzcBXrlCR5fp8Ky+GoTpmwyCSGMc/KxtvAX/KFmxNGfQ3f4NV1A2kZqPc7xPik6P2poqUPGVxaxAom4VWdgkvmj23hlxLz1nHvwQZpAgMBAAECggEACcQPrHObgvdP54i7ricpT9i829055w3XP/i3L1t8j5oNX8aqGKGVjs9opnBDGdejg4bCCL9dFJ4YknfBvs0I4P5sf4Pkjlmsh8veBU4gXnlcXEqr0Ote270EU6/V4M3WmuuLRvLQuxwVu9P8T48cDbwz6ZdPFm/FXGppqeaNhNOa43l+eLnzu2Zf3kR5F9/sfUHU9w5nnbneHz/MqsvBzhnGGyFwVTQMGXcr/WL9phWFP+2pRRMsMLVkLtvo5PYhDxhqk0N4XY/KDQDk0erGYJLvEfcp/jWr6Pc+q4DLd50Fo8RTKZsu6z52+Nq51nuEwg/1+I8Q2/IqD9O1DlxRxQKBgQDWpQYXo0n1YGjX/a2Qzj0HX8h52gsnUGN5gHpFC8OcNdmhrbauFMgbO/T3jZQPshrUtNl7ZRiPZYQMr/1oqlLhknbjDvORflPUvDG9q+bud6amx6YyUBNZe5F1UNPCOkiS0gkvziPfUmHSO0bKj9LuSO8/3B0r+BpFVk3ojxqm/wKBgQCngk4bu2UaFe0FXkQmdEvvd0g/9xlgLdV54E5wzyM+lcLPqLItoC9+EU2Zf2ZX+O1k+SAZVjlX5zeJFSucrtaz89Qi9cG4N+PYS3tXj1rxxvxAoGuPs4zfpz/ADzJoKRA+AYDeUjwPeVa74M/1bJ9T3EImW9hdRQ4iKacMJ416lwKBgAOZcte0okneGwP8OgvimX1lKsx4TpWTMIfaErLTtq0I7iRa8GRM7uoeAEaidigpOW/1tGvwTxAgiK1ZyisWKBumRB4coxL+hlNb8I2Ys1+uW/oSAQR4+UoX22GXQGXSamMj6ImHsGxPSlqnKdG9xbC/QlvUyhxxpaciAl5y/+RrAoGAZVktsRG8pf/GHI1gZnPOzeA50pnKvKp+kvLoAtEQ3fj2mXXT+E8G7RvC+7BdV7TAzWr9xNaqc0juDP7GGFKgjNhl7ZEL2E3YiTGCOwP9XnOqg9+LtLJJ9bfXlnibYvIVZa67LchLloEvvRTUPgWukKqfS14elrAFUR4qd+wmaZkCgYEA1bOEg2X7ikgiwfyLzKsuR12fhhf86oVCoG7swDmqnqu3RfbLxN1owBnaBibTCh9qkVdIBoa2X9PCOvjpO1khaLusPcehW2VNODGomKqSGlCigxtPvwUQr0rJECol5RD89CFJniY40AQmsVqjrQETmlbw9at/8LS+VTyz8y1iHYY=";
	private static final String secret = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam imperdiet ipsum in libero feugiat lobortis.";

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
	public void testComputeSignature() {
		Assert.assertTrue(true);
	}

	@Test
	public void testBuildPublicKey() {
		KeyPair keyPair = CipherUtils.generateKeyPair();
		String publicKeyString = CipherUtils.getPublicKey(keyPair);
		RSAPublicKey publicKey = CipherUtils.buildPublicKey(publicKeyString);
		Assert.assertNotNull(publicKey);
	}

	@Test
	public void testBuildPrivateKey() {
		KeyPair keyPair = CipherUtils.generateKeyPair();
		String privateKeyString = CipherUtils.getPrivateKey(keyPair);
		Assert.assertNotNull(privateKeyString);
		RSAPrivateKey privateKey = CipherUtils.buildPrivateKey(privateKeyString);
		Assert.assertNotNull(privateKey);
	}

	@Test
	public void testBuildKeyPair() {
		KeyPair keyPair = CipherUtils.generateKeyPair();
		String publicKeyString = CipherUtils.getPublicKey(keyPair);
		String privateKeyString = CipherUtils.getPrivateKey(keyPair);
		Assert.assertNotNull(publicKeyString);
		Assert.assertNotNull(privateKeyString);

		RSAPublicKey publicKey = CipherUtils.buildPublicKey(publicKeyString);
		RSAPrivateKey privateKey = CipherUtils.buildPrivateKey(privateKeyString);
		Assert.assertNotNull(publicKey);
		Assert.assertNotNull(privateKey);
	}

	@Test
	public void testEncryptDecrypt() throws UnsupportedEncodingException {
		KeyPair keyPair = CipherUtils.generateKeyPair();
		String publicKeyString = CipherUtils.getPublicKey(keyPair);
		String privateKeyString = CipherUtils.getPrivateKey(keyPair);
		RSAPublicKey publicKey = CipherUtils.buildPublicKey(publicKeyString);
		RSAPrivateKey privateKey = CipherUtils.buildPrivateKey(privateKeyString);

		byte[] encrypt = CipherUtils.encrypt(Converter.getBytesUtf8(secret), privateKey);
		Assert.assertTrue(ArrayUtils.isNotEmpty(encrypt));

		byte[] decrypt = CipherUtils.decrypt(encrypt, publicKey);
		Assert.assertTrue(ArrayUtils.isNotEmpty(decrypt));

		Assert.assertEquals(secret, new String(decrypt, Converter.UTF_8));
	}

}
