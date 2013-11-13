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

import java.util.Map;

public interface ConfigurationUpdater {

	public boolean isDeleteable();

	public boolean isUpdateable();

	public boolean isCreateable();

	public ConfigurationUpdater delete();

	public ConfigurationUpdater removeProperty(String key);

	public ConfigurationUpdater withName(String name);

	public ConfigurationUpdater withEnvironmentSelector(
			EnvironmentSelector environment);

	public ConfigurationUpdater withConfiguration(Configuration config);

	public ConfigurationUpdater withLocation(String location);

	public ConfigurationUpdater withQueryPath(String queryPath);

	public ConfigurationUpdater withMetaInfo(String key,
			Map<String, String> metaInfo);

	public ConfigurationUpdater withMetaInfo(String key, String metaInfoKey,
			String metaInfoValue);

	public ConfigurationUpdater withProperty(String key, String value,
			Map<String, String> metaInfo);

	public ConfigurationUpdater withProperty(String key,
			int value);

	public ConfigurationUpdater withProperty(String key,
			byte value);

	public ConfigurationUpdater withProperty(String key,
			short value);

	public ConfigurationUpdater withProperty(String key,
			boolean value);

	public ConfigurationUpdater withProperty(String key,
			char value);

	public ConfigurationUpdater withProperty(String key,
			float value);

	public ConfigurationUpdater withProperty(String key,
			double value);

	public ConfigurationUpdater withProperty(String key,
			long value);

	public ConfigurationUpdater withQuery(ConfigurationQuery<Boolean> query);

	public Configuration commit();

}
