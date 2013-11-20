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

import java.net.InetAddress;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ServiceLoader;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.config.spi.EnvironmentContext;

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
	 * Get the {@link ConfigService}, based on the given {@link Environment}.
	 * This method can be used for simulations and for evaluating the
	 * configuration valid in a certain context.
	 * 
	 * @return the {@link ConfigService}, not {@code null}.
	 */
	public static ConfigService getConfigService(EnvironmentContext environment) {
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
		/** The default environment provider. */
		private Environment environment = new DefaultEnvironment();

		@Override
		public Configuration getConfiguration(String configId) {
			throw new UnsupportedOperationException(
					"No java-config implementation found.");
		}

		@Override
		public boolean isConfigurationDefined(String configId) {
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
		public Collection<String> getConfigurationIds() {
			throw new UnsupportedOperationException(
					"No java-config implementation found.");
		}

		@Override
		public void configure(Object instance) {
			throw new UnsupportedOperationException(
					"No java-config implementation found.");
		}

		@Override
		public Environment getCurrentEnvironment() {
			return environment;
		}

		@Override
		public Configuration getConfiguration(String configId,
				Environment environment) {
			throw new UnsupportedOperationException(
					"No java-config implementation found.");
		}

		@Override
		public Environment getSystemEnvironment() {
			return environment;
		}

	}

	/**
	 * Default {@link Environment}.
	 * 
	 * @author Anatole Tresch
	 */
	public static final class DefaultEnvironment implements Environment {

		private Map<String, String> properties = new TreeMap<String, String>();

		private static final String STAGE_PROP = "javax.config.stage";
		private static final String DEFAULT_STAGE = "TEST";

		public DefaultEnvironment() {
			String stage = System.getProperty(STAGE_PROP);
			if (stage == null) {
				stage = DEFAULT_STAGE;
			}
			this.properties.put("_stage", stage);
			// Copy system properties....
			for (Entry<Object, Object> en : System.getProperties().entrySet()) {
				this.properties.put(en.getKey().toString(), en.getValue()
						.toString());
			}
			this.properties.put("timezoneId", TimeZone.getDefault().getID());
			this.properties.put("timezoneName", TimeZone.getDefault()
					.getDisplayName(Locale.ENGLISH));
			this.properties.put("locale", Locale.getDefault().toString());
			try {
				this.properties.put("hostName", InetAddress.getLocalHost()
						.getHostName());
			} catch (Exception e) {

			}
			try {
				this.properties.put("hostAddress", InetAddress.getLocalHost()
						.getHostAddress());
			} catch (Exception e) {

			}
			// Copy env properties....
			for (Entry<String, String> en : System.getenv().entrySet()) {
				this.properties.put("env:" + en.getKey(), en
						.getValue()
						.toString());
			}
		}

		public void setStage(Stage stage) {
			this.properties.put("_stage", stage.toString());
		}

		@Override
		public Stage getStage() {
			try {
				return Stage.valueOf(this.properties.get("_stage"));
			} catch (Exception e) {
				return Stage.Development;
			}
		}

		public void setAttribute(String key, String value) {
			this.properties.put(key, value);
		}

		@Override
		public Map<String, String> getProperties() {
			return properties;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuilder out = new StringBuilder();
			out.append("DefaultEnvironment [stage=").append(getStage())
					.append(", properties:\n");
			for (Entry<String, String> en : this.properties.entrySet()) {
				out.append("  ").append(en.getKey().toString()).append(" = ")
						.append(en
								.getValue().toString()).append('\n');
			}
			out.append("]");
			return out.toString();
		}

	}

}
