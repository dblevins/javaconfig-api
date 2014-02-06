/*
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE CONDITION THAT YOU
 * ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT. PLEASE READ THE TERMS AND CONDITIONS OF THIS
 * AGREEMENT CAREFULLY. BY DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF
 * THE AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE" BUTTON AT THE
 * BOTTOM OF THIS PAGE. Specification: JSR-xxx Java Configuration API ("Specification") Copyright
 * (c) 2012-2013, Credit Suisse All rights reserved.
 */
package javax.config;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Event distributed when a {@link Configuration} has been changed, removed or
 * added.<br/>
 * Instances of this class are
 * <ul>
 * <li>immutable
 * <li>thread-safe
 * <li>serializable
 * </ul>
 * 
 * @author Anatole Tresch
 */
public final class EnvironmentChangeEvent implements Serializable {
	/** serialVersionUID. */
	private static final long serialVersionUID = -5607391349699806375L;

	/** Basic type of change. */
	public static enum Type {
		/** The configuration was removed. */
		REMOVED,
		/** The configuration has been added. */
		ADDED
	}

	/** The {@link Type} of change. */
	private final Type changeType;
	/** The affected {@link Configuration}- */
	private final Environment environment;
	/** Additional event meta data. */
	private final Map<String, String> metaData;

	/**
	 * Creates a new instances from the {@link Builder}.
	 * 
	 * @param builder
	 *            the builder with the value to be set.
	 */
	private EnvironmentChangeEvent(Builder builder) {
		Objects.requireNonNull(builder.environment);
		Objects.requireNonNull(builder.changeType);
		this.environment = builder.environment;
		this.changeType = builder.changeType;
		this.metaData = builder.metaData;
	}

	/**
	 * The {@link Type} of {@link Configuration} change.
	 * 
	 * @return the changeType, never {@code null}.
	 */
	public final Type getChangeType() {
		return changeType;
	}

	/**
	 * Access the {@link Configuration} affected.
	 * 
	 * @return the configuration, never {@code null}.
	 */
	public final Environment getEnvironment() {
		return environment;
	}

	/**
	 * Access the optional meta data.
	 * 
	 * @return the meta data, never {@code null}.
	 */
	public Map<String, String> getMetaData() {
		return Collections.unmodifiableMap(metaData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConfigChangeEvent [environment=" + environment
				+ ", changeType=" + changeType + ", metaData=" + metaData + "]";
	}

	public static final class Builder {
		private Type changeType;
		private Environment environment;
		private Map<String, String> metaData = new HashMap<>();

		public Builder(Environment environment, Type changeType) {
			Objects.requireNonNull(environment);
			this.environment = environment;
		}

		public Builder setType(Type changeType) {
			Objects.requireNonNull(changeType);
			this.changeType = changeType;
			return this;
		}

		public Builder addMetaData(String key, String value) {
			this.metaData.put(key, value);
			return this;
		}

		public Builder removeMetaData(String key) {
			this.metaData.remove(key);
			return this;
		}

		public Builder clearMetaData() {
			this.metaData.clear();
			return this;
		}

		public EnvironmentChangeEvent create() {
			return new EnvironmentChangeEvent(this);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Builder [environment=" + environment + ", changeType="
					+ changeType + ", metaData=" + metaData + "]";
		}

	}
}
