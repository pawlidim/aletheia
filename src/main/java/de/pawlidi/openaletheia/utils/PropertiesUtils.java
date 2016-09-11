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

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import de.pawlidi.openaletheia.Constants;

/**
 * <p>
 * Operations on {@link java.util.Properties} that are <code>null</code> safe.
 * </p>
 * 
 * @author PAWLIDIM
 *
 *         Create: 23:11:57 2015
 *
 */
public class PropertiesUtils {

	private static final String LIST_SEPARATOR = ",";

	/**
	 * <p>
	 * Checks if given properties object is empty or null.
	 * </p>
	 * 
	 * @param properties
	 *            the Properties to check, may be null
	 * @return <code>true</code> if the Properties is empty or null
	 */
	public static boolean isEmpty(Properties properties) {
		return properties == null || properties.isEmpty();
	}

	/**
	 * <p>
	 * Checks if given properties object is not empty or not null.
	 * </p>
	 * 
	 * @param properties
	 *            the Properties to check, may be null
	 * @return <code>true</code> if the Properties is not empty or not null
	 */
	public static boolean isNotEmpty(Properties properties) {
		return !PropertiesUtils.isEmpty(properties);
	}

	public static void setStringProperty(Properties properties, final String key, final String value) {
		if (StringUtils.isNotEmpty(value)) {
			properties.setProperty(key, value);
		}
	}

	public static void setDateProperty(Properties properties, final String key, final DateTime value) {
		if (value != null) {
			properties.setProperty(key, Constants.DATE_FORMAT.print(value));
		}
	}

	public static void setObjectProperty(Properties properties, final String key, final Object value) {
		if (value != null) {
			properties.setProperty(key, value.toString());
		}
	}

	public static String getStringProperty(Properties properties, final String key) {
		if (PropertiesUtils.isEmpty(properties) || StringUtils.isBlank(key)) {
			return null;
		}
		return properties.getProperty(key);
	}

	public static Object removeValue(Properties properties, final String key) {
		if (PropertiesUtils.isEmpty(properties) || StringUtils.isBlank(key)) {
			return null;
		}
		return properties.remove(key);
	}

	public static DateTime getDateProperty(Properties properties, final String key) {
		if (PropertiesUtils.isEmpty(properties) || StringUtils.isBlank(key)) {
			return null;
		}
		String property = properties.getProperty(key);
		if (StringUtils.isBlank(property)) {
			return null;
		}
		try {
			return Constants.DATE_FORMAT.parseDateTime(property);
		} catch (UnsupportedOperationException e) {
			return null;
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	/**
	 * Return boolean value for given key. The boolean returned represents the
	 * value true if the property value is not null and is equal, to the string
	 * <code>'true'</code>, <code>'on'</code> or <code>'yes'</code> (case
	 * insensitive) will return <code>true</code>. <code>'false'</code>,
	 * <code>'off'</code> or <code>'no'</code> (case insensitive) will return
	 * <code>false</code>. Otherwise, <code>null</code> is returned.
	 * </p>
	 * 
	 * @param properties
	 * @param key
	 * @return
	 */
	public static Boolean getBooleanProperty(Properties properties, final String key) {
		if (PropertiesUtils.isEmpty(properties) || StringUtils.isBlank(key)) {
			return null;
		}
		final String property = properties.getProperty(key);
		if (property == null) {
			return null;
		}
		return BooleanUtils.toBoolean(property);
	}

	public static Long getLongProperty(Properties properties, final String key) {
		if (PropertiesUtils.isEmpty(properties) || StringUtils.isBlank(key)) {
			return null;
		}
		final String property = properties.getProperty(key);
		if (property == null) {
			return null;
		}
		try {
			return Long.valueOf(property);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static List<String> getListProperty(Properties properties, final String key) {
		if (PropertiesUtils.isEmpty(properties) || StringUtils.isBlank(key)) {
			return null;
		}

		final String property = getStringProperty(properties, key);
		if (property == null) {
			return null;
		}

		List<String> listProperty = new ArrayList<String>(0);
		String[] values = StringUtils.split(property, LIST_SEPARATOR);
		for (String value : values) {
			listProperty.add(value);
		}
		return listProperty;

	}

	public static void setListProperty(Properties properties, String key, List<String> values) {
		if (PropertiesUtils.isNotEmpty(properties) && StringUtils.isNotEmpty(key) && values != null
				&& !values.isEmpty()) {
			final int listSize = values.size();
			StringBuilder listProperty = new StringBuilder();
			for (int i = 0; i < listSize; i++) {
				final String value = values.get(i);
				if (StringUtils.isNotEmpty(value)) {
					listProperty.append(value);

					// check if current element is not last element
					if (i < listSize - 1) {
						listProperty.append(LIST_SEPARATOR);
					}
				}
			}
		}

	}
}
