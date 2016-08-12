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
package de.pawlidi.openaletheia;

/**
 * 
 * @author PAWLIDIM
 *
 *         Create: 19:36:53 2015
 * 
 */
public class LicenseException extends Exception {

	/**
	 * @param arg0
	 */
	public LicenseException(String message) {
		super(message);
	}

	/**
	 * @param arg0
	 */
	public LicenseException(Throwable exception) {
		super(exception);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public LicenseException(String message, Throwable exception) {
		super(message, exception);
	}

}
