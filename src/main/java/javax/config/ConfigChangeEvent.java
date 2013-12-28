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
 * Event distributed when a {@link Configuration} has been changed, removed or added.
 * 
 * @author Anatole Tresch
 */
public final class ConfigChangeEvent {
	/** Basic type of change. */
	public static enum Type {
		/** The new and old configuration show differences in some keys. */
		UPDATED,
		/** The configuration was removed. */
		REMOVED,
		/** The configuration has been added. */
		ADDED
	}

	/** The {@link Type} of change. */
	private final Type changeType;
	/** The affected {@link Configuration}- */
	private final Configuration configuration;
	/** Additional event meta data. */
	private final Map<String, String> metaData;
	/** A detailed list of the changes encountered. */
	private final Map<String, ChangeDetail> delta = new HashMap<>();

	/**
	 * Creates a new instances from the {@link Builder}.
	 * 
	 * @param builder
	 *            the builder with the value to be set.
	 */
	private ConfigChangeEvent(Builder builder) {
		Objects.requireNonNull(builder.configuration);
		Objects.requireNonNull(builder.changeType);
		this.configuration = builder.configuration;
		this.changeType = builder.changeType;
		this.metaData = builder.metaData;
		this.delta.putAll(builder.delta);
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
	public final Configuration getConfiguration() {
		return configuration;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConfigChangeEvent [configuration=" + configuration
				+ ", changeType=" + changeType + ", metaData=" + metaData
				+ ", delta=" + delta + "]";
	}

	/**
	 * Class describing the detailed change of an entry.
	 */
	public static final class ChangeDetail implements Serializable {
		/** serial version. */
		private static final long serialVersionUID = 1L;
		/** The key affected, not null. */
		private final String key;
		/** The current value. */
		private final String value;
		/** The previous value. */
		private final String previousValue;

		/**
		 * Creates a new instance.
		 * 
		 * @param key
		 *            the entry's key, not null.
		 * @param value
		 *            the entry's current value, != oldValue.
		 * @param oldValue
		 *            the entry's previous value, != value.
		 */
		private ChangeDetail(String key, String value, String oldValue) {
			Objects.requireNonNull(key);
			this.key = key;
			if (oldValue == value) {
				throw new IllegalArgumentException("oldValue==value");
			}
			if (oldValue != null) {
				if (oldValue.equals(value)) {
					throw new IllegalArgumentException("oldValue.equals(value)");
				}
			}
			else {
				if (value.equals(oldValue)) {
					throw new IllegalArgumentException("value.equals(oldValue)");
				}
			}
			this.previousValue = oldValue;
			this.value = value;
		}

		/**
		 * Get the key of the entry affected.
		 * 
		 * @return the entry's key, never {@code null}.
		 */
		public final String getKey() {
			return key;
		}

		/**
		 * Get the current value of the entry affected.
		 * 
		 * @return the entry's value, may also be {@code null}.
		 */
		public final String getValue() {
			return value;
		}

		/**
		 * Get the previous value of the entry affected.
		 * 
		 * @return the entry's previous value, may also be {@code null}.
		 */
		public final String getPreviousValue() {
			return previousValue;
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			result = prime * result
					+ ((previousValue == null) ? 0 : previousValue.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ChangeDetail other = (ChangeDetail) obj;
			if (key == null) {
				if (other.key != null)
					return false;
			} else if (!key.equals(other.key))
				return false;
			if (previousValue == null) {
				if (other.previousValue != null)
					return false;
			} else if (!previousValue.equals(other.previousValue))
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "ChangeDetail [key=" + key + ", value=" + value
					+ ", previousValue=" + previousValue + "]";
		}

	}

	public static final class Builder {
		private Type changeType;
		private Configuration configuration;
		private Map<String, String> metaData = new HashMap<>();
		private final Map<String, ChangeDetail> delta = new HashMap<>();

		public Builder(Configuration configuration, Type changeType) {
			Objects.requireNonNull(configuration);
			this.configuration = configuration;
		}

		public Builder addChange(String key, String value, String oldValue) {
			Objects.requireNonNull(key);
			this.delta.put(key, new ChangeDetail(key, value, oldValue));
			return this;
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

		public ConfigChangeEvent create() {
			return new ConfigChangeEvent(this);
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Builder [configuration=" + configuration + ", changeType="
					+ changeType + ", metaData=" + metaData + ", delta="
					+ delta + "]";
		}

	}
}
