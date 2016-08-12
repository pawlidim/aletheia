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

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.lang.CharEncoding;

/**
 * 
 * @author PAWLIDIM
 *
 */
public final class Converter {

	public static final String UTF_8 = CharEncoding.UTF_8;

	public static final String ISO_8859_1 = CharEncoding.ISO_8859_1;

	private Converter() {
		super();
	}

	public static String toString(byte[] array) {
		return Base64.encodeBase64String(array);
	}

	public static byte[] toBytes(String string) {
		return Base64.decodeBase64(string);
	}

	public static byte[] getBytesUtf8(final String string) {
		return StringUtils.getBytesUtf8(string);
	}

	public static byte[] getBytesIso8859(final String string) {
		return StringUtils.getBytesIso8859_1(string);
	}

	public static String hexToString(byte[] data) {
		return Hex.encodeHexString(data);
	}
}
