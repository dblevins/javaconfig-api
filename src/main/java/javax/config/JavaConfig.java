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

import java.util.Collection;
import java.util.ServiceLoader;

/**
 * Singleton accessor for Java configuration.
 * 
 * @author Anatole Tresch
 */
public final class JavaConfig {
	/** The main delegate. */
	private static final ConfigService configurationService = loadConfigService();

	/**
	 * Private singleton constructor.
	 */
	private JavaConfig() {
		// Singleton
	}

	/**
	 * Get the {@link ConfigService}
	 * 
	 * @return the {@link ConfigService}, not {@code null}.
	 */
	public static ConfigService getConfigService() {
		return configurationService;
	}

	/**
	 * Loads the {@link ConfigService} from the {@link ServiceLoader}, or
	 * instantiates a {@link DefaultConfigService}.
	 * 
	 * @return the {@link ConfigService} to be used.
	 */
	private static ConfigService loadConfigService() {
		try {
			ServiceLoader<ConfigService> services = ServiceLoader
					.load(ConfigService.class);
			for (ConfigService service : services) {
				return service;
			}
		} catch (Exception e) {
			return new DefaultConfigService();
		}
		return new DefaultConfigService();
	}

	/**
	 * Default configuration service.
	 * 
	 * @author Anatole Tresch
	 */
	private static final class DefaultConfigService implements ConfigService {

		@Override
		public Collection<String> getConfigurationNames() {
			throw new UnsupportedOperationException(
					"No java-config implementation found.");
		}

		@Override
		public Configuration getConfiguration() {
			throw new UnsupportedOperationException(
					"No java-config implementation found.");
		}
		
		@Override
		public Configuration getConfiguration(String name) {
			throw new UnsupportedOperationException(
					"No java-config implementation found.");
		}

		@Override
		public Configuration getConfiguration(String name, Environment environment) {
			throw new UnsupportedOperationException(
					"No java-config implementation found.");
		}

		@Override
		public boolean isConfigurationDefined(String name) {
			throw new UnsupportedOperationException(
					"No java-config implementation found.");
		}

		@Override
		public ConfigurationQuery queryConfiguration() {
			throw new UnsupportedOperationException(
					"No java-config implementation found.");
		}

		@Override
		public ConfigurationUpdater updateConfiguration(Configuration config) {
			throw new UnsupportedOperationException(
					"No java-config implementation found.");
		}

		@Override
		public ConfigurationUpdater createConfiguration(String configId) {
			throw new UnsupportedOperationException(
					"No java-config implementation found.");
		}

		@Override
		public void addConfigChangeListener(ConfigChangeListener l) {
			throw new UnsupportedOperationException(
					"No java-config implementation found.");
		}

		@Override
		public void removeConfigChangeListener(ConfigChangeListener l) {
			throw new UnsupportedOperationException(
					"No java-config implementation found.");
		}

		@Override
		public void configure(Object instance) {
			throw new UnsupportedOperationException(
					"No java-config implementation found.");
		}

	}

}
