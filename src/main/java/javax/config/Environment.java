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
import java.util.Set;

/**
 * Models the current runtime environment. Instances of this class are used to
 * evaluate the correct configuration artifacts.<br/>
 * <h3>Implementation Specification</h3>
 * <p>
 * Implementations of this interface must be
 * <ul>
 * <li>Thread safe.
 * <li>Immutable
 * <li>serializable
 * </ul>
 * 
 * @author Anatole Tresch
 */
public final class Environment implements Serializable {
	/** serialVersionUID. */
	private static final long serialVersionUID = -7410447407000577031L;

	/** The environment's type. */
	private EnvironmentType environmentType;
	/** The environment's name. */
	private String name;
	/** The parent environment. */
	private Environment parent;
	/** The attributes. */
	private Map<Class<?>, Map<Object, Object>> attributes = new HashMap<Class<?>, Map<Object, Object>>();

	/**
	 * Creates a new {@link Environment}.
	 * 
	 * @param builder
	 *            the {@link Builder} instance.
	 */
	public Environment(Builder builder) {
		this.environmentType = builder.environmentType;
		this.name = builder.name;
		this.parent = builder.parent;
		this.attributes = builder.attributes;
	}

	/**
	 * Get the environment's type.
	 * 
	 * @return the environment's type, not {@code null}
	 */
	public EnvironmentType getType() {
		return environmentType;
	}

	/**
	 * Get the name of the environment. The environment's name must be unique in
	 * combination with the EnvironmentType.
	 * 
	 * @return the environment's name, not {@code null}
	 */
	public String getName() {
		return getName();
	}

	/**
	 * Get the overall (read-only) properties for this {@link Environment}. The
	 * values of the parent environment, by default are also visible, but may be
	 * overridden, or extended by this {@link Environment}. Removal of entries
	 * visible on the parent is rare, but is not forbidden.
	 * 
	 * @return the overall (read-only) properties for this {@link Environment},
	 *         never {@code null}.
	 */
	public Set<Object> getAttributeKeys(Class<?> targetType) {
		Map<Object, Object> values = attributes.get(targetType);
		if (values == null) {
			return Collections.emptySet();
		}
		return values.keySet();
	}

	/**
	 * Get an attribute.
	 * 
	 * @param key
	 *            the attribute's key.
	 * @param type
	 *            the attribute's type.
	 * @return the attribute's value.
	 */
	public <T> T getAttribute(Object key, Class<T> type) {
		Map<Object, Object> values = attributes.get(type);
		if (values == null) {
			return null;
		}
		return type.cast(values.get(key));
	}

	/**
	 * Get an attribute.
	 * 
	 * @param key
	 *            the attribute's key.
	 * @param type
	 *            the attribute's type.
	 * @return the attribute's value.
	 */
	public <T> T getAttribute(Class<T> type) {
		return getAttribute(type, type);
	}

	/**
	 * Get the parent {@link Environment} of this environment. The values of the
	 * parent environment, by default are also visible, but may be overridden,
	 * or extended by this {@link Environment}. Removal of entries visible on
	 * the parent is rare, but is not forbidden.
	 * 
	 * @see ConfigService#getCurrentEnvironment()
	 * @see ConfigService#getSystemEnvironment()
	 * @return the parent {@link Environment}, or {@code null}, if there is no
	 *         parent, e.g. for the global system environment as returned by
	 *         {@link ConfigService#getSystemEnvironment()} .
	 */
	public Environment getParent() {
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Environment [environmentType=" + environmentType + ", name="
				+ name + ", parent=" + parent + "]";
	}

	/**
	 * The builder class to create an environment.
	 * 
	 * @author Anatole Tresch
	 */
	public static final class Builder {
		/** The environment's type. */
		private EnvironmentType environmentType;
		/** The environment's name. */
		private String name;
		/** The environment's parent. */
		private Environment parent;
		/** The attributes. */
		private Map<Class<?>, Map<Object, Object>> attributes = new HashMap<Class<?>, Map<Object, Object>>();

		/**
		 * Creates a new builder.
		 * 
		 * @param environmentType
		 *            the environment's type, not {@code null}
		 * @param name
		 *            the environment's name, not {@code null}.
		 */
		public Builder(EnvironmentType environmentType, String name) {
			Objects.requireNonNull(environmentType, "EnvironmentType required.");
			Objects.requireNonNull(name, "Environment name required.");
			this.environmentType = environmentType;
			this.name = name;
		}

		/**
		 * Set the environment's parent.
		 * 
		 * @param environment
		 *            The environment, not {@code null}.
		 * @return this instance for chaining.
		 */
		public Builder setParent(Environment environment) {
			Objects.requireNonNull(environment);
			this.parent = environment;
			return this;
		}

		/**
		 * Sets an attribute.
		 * 
		 * @param key
		 *            the attribute's key, not {@code null}.
		 * @param type
		 *            the attribute's type, not {@code null}.
		 * @param value
		 *            the attribute.s value, not {@code null}.
		 * @return this instance for chaining.
		 */
		public <T> Builder setAttribute(Object key, Class<T> type, Object value) {
			Map<Object, Object> values = attributes.get(type);
			if (values == null) {
				values = new HashMap<Object, Object>();
				attributes.put(type, values);
			}
			values.put(key, value);
			return this;
		}

		/**
		 * Sets an attribute, using the value's type as attribute's type.
		 * 
		 * @param key
		 *            the attribute's key, not {@code null}.
		 * @param value
		 *            the attribute.s value, not {@code null}.
		 * @return this instance for chaining.
		 */
		public <T> Builder setAttribute(Object key, Object value) {
			Map<Object, Object> values = attributes.get(value.getClass());
			if (values == null) {
				values = new HashMap<Object, Object>();
				attributes.put(value.getClass(), values);
			}
			values.put(key, value);
			return this;
		}

		/**
		 * Sets an attribute, using the value's type as attribute's type and the
		 * value class as attribute key.
		 * 
		 * @param value
		 *            the attribute.s value, not {@code null}.
		 * @return this instance for chaining.
		 */
		public Builder setAttribute(Object value) {
			setAttribute(value.getClass(), value.getClass(), value);
			return this;
		}

		public Environment create() {
			return new Environment(this);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Environment.Builder [environmentType=" + environmentType
					+ ", name=" + name + ", parent=" + parent.getName()
					+ ", attributes=" + attributes + "]";
		}

	}

}
