/*
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE
 * CONDITION THAT YOU ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT.
 * PLEASE READ THE TERMS AND CONDITIONS OF THIS AGREEMENT CAREFULLY. BY
 * DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF THE
 * AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE"
 * BUTTON AT THE BOTTOM OF THIS PAGE.
 * 
 * Specification: JSR-xxx Java Configuration API ("Specification")
 * 
 * Copyright (c) 2012-2013, Credit Suisse All rights reserved.
 */
package javax.config;

import java.util.Collections;
import java.util.Map;

public final class ConfigChangeEvent {

	public static enum Type {
		REPLACED,
		UPDATED,
		REMOVED,
		ADDED
	}

	private Type changeType;
	private Configuration configuration;
	private String key;
	private String value;
	private String oldValue;
	private Map<String, String> metaData;

	/**
	 * @return the changeType
	 */
	public final Type getChangeType() {
		return changeType;
	}

	/**
	 * @return the configuration
	 */
	public final Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * @return the node
	 */
	public final String getKey() {
		return key;
	}

	/**
	 * @return the value
	 */
	public final String getNewValue() {
		return value;
	}

	public final String getOldValue() {
		return oldValue;
	}

	public Map<String, String> getMetaData() {
		return Collections.unmodifiableMap(metaData);
	}

}
