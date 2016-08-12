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

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * KeyGenerator core test case.
 * 
 * @author PAWLIDIM
 *
 */
public class KeyGeneratorTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link de.pawlidi.aletheia.generator.KeyGenerator#generateKeyFiles(java.lang.String)}
	 * .
	 */
	@Test
	public void testGenerateKeyFiles() {
		assertTrue(KeyGenerator.generateKeyFiles("."));

	}

	/**
	 * Test method for
	 * {@link de.pawlidi.aletheia.generator.KeyGenerator#createKeyFile(java.io.File, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testCreateKeyFile() {
		assertTrue(true);
	}

	/**
	 * Test method for
	 * {@link de.pawlidi.aletheia.generator.KeyGenerator#readPrivateKeyFile(java.lang.String)}
	 * .
	 */
	@Test
	public void testReadPrivateKeyFile() {
		assertTrue(true);
	}

	/**
	 * Test method for
	 * {@link de.pawlidi.aletheia.generator.KeyGenerator#readPublicKeyFile(java.lang.String)}
	 * .
	 */
	@Test
	public void testReadPublicKeyFile() {
		assertTrue(true);
	}

	/**
	 * Test method for
	 * {@link de.pawlidi.aletheia.generator.KeyGenerator#readKeyFile(java.lang.String, boolean)}
	 * .
	 */
	@Test
	public void testReadKeyFile() {
		assertTrue(true);
	}

}
