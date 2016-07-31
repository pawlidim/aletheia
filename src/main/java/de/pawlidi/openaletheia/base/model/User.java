package de.pawlidi.openaletheia.base.model;

import java.io.Serializable;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import de.pawlidi.openaletheia.utils.CipherUtils;

/**
 * 
 * 
 * @author PAWLIDIM
 *
 *         Create: 00:27:43 02.05.2015
 *
 */
@XmlRootElement(name = "License")
public final class User implements Serializable {

	private static final String VALUE_SEPARATOR = "#";
	private String uuid;
	private String username;
	private String password;

	public User() {
		super();
	}

	public User(String uuid, String username, String password) {
		super();
		this.uuid = UUID.randomUUID().toString();
		this.username = username;
		this.password = password;
	}

	/**
	 * Getter to get the uuid
	 * 
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * Setter to set the uuid
	 * 
	 * @param uuid
	 *            the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * Getter to get the username
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setter to set the username
	 * 
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Getter to get the password
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter to set the password
	 * 
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = CipherUtils.encryptPassword(password);
	}

	public boolean verifyPassword(final String password) {
		return CipherUtils.verifyPassword(password, this.password);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return uuid + VALUE_SEPARATOR + username + VALUE_SEPARATOR + password;
	}

	public User toObject(final String userString) {
		if (StringUtils.isNotEmpty(userString)) {
			String[] values = StringUtils.split(userString, VALUE_SEPARATOR);
			if (ArrayUtils.isNotEmpty(values) && values.length >= 3) {
				uuid = values[0];
				username = values[1];
				password = values[2];
			}
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (password == null) {
			if (other.getPassword() != null) {
				return false;
			}
		} else if (!password.equals(other.getPassword())) {
			return false;
		}
		if (username == null) {
			if (other.getUsername() != null) {
				return false;
			}
		} else if (!username.equals(other.getUsername())) {
			return false;
		}
		if (uuid == null) {
			if (other.getUuid() != null) {
				return false;
			}
		} else if (!uuid.equals(other.getUuid())) {
			return false;
		}
		return true;
	}

}
