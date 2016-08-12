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

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import de.pawlidi.openaletheia.base.model.License;
import de.pawlidi.openaletheia.base.model.User;

/**
 * 
 * 
 * @author PAWLIDIM
 * 
 *         Create: 19:32:45 2015
 * 
 */
public final class AletheiaContext implements Serializable {

	private volatile static AletheiaContext instance = null;

	private License license;

	private Map<String, User> userBase;

	private AletheiaContext() {
		super();
		this.userBase = new HashMap<String, User>(0);
	}

	/**
	 * Returns aletheia instance.
	 * 
	 * @return instance
	 */
	public static AletheiaContext instance() {
		if (instance == null) {
			synchronized (AletheiaContext.class) {
				if (instance == null) {
					instance = new AletheiaContext();
				}
			}
		}
		return instance;
	}

	/**
	 * Getter to get the product license
	 * 
	 * @return the license
	 */
	public License getLicense() {
		return license;
	}

	/**
	 * Setter to set the license
	 * 
	 * @param license
	 *            the license to set
	 */
	void setLicense(License license) {
		this.license = license;
		this.userBase.clear();

	}

	/**
	 * Add user list to user base.
	 * 
	 * @param users
	 */
	void addUser(List<User> users) {
		for (User user : users) {
			addUser(user.getUsername(), user);
		}
	}

	/**
	 * Add user to user base.
	 * 
	 * @param username
	 * @param user
	 */
	void addUser(final String username, User user) {
		if (StringUtils.isNotEmpty(username) && user != null) {
			userBase.put(username, user);
		}
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean authenticate(final String username, final String password) {
		if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
			User user = userBase.get(username);
			if (user != null) {
				return user.verifyPassword(password);
			}
		}
		return false;
	}
}
