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
package javax.config.meta;

import java.io.Serializable;
import java.util.Objects;
import java.util.Properties;

public final class UnitDescriptor implements Comparable<UnitDescriptor>,
		Serializable {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	private String unitName;
	private int priority;
	private String className;
	private Properties properties = new Properties();
	private boolean shared = false;

	
	public UnitDescriptor(String unitName) {
		Objects.requireNonNull(unitName);
		this.unitName = unitName;
	}
	
	/**
	 * @return the unitName
	 */
	public final String getUnitName() {
		return unitName;
	}

	/**
	 * @return the priority
	 */
	public final int getPriority() {
		return priority;
	}

	/**
	 * @return the className
	 */
	public final String getClassName() {
		return className;
	}

	/**
	 * @return the properties
	 */
	public final Properties getProperties() {
		return properties;
	}

	/**
	 * @return the shared
	 */
	public final boolean isShared() {
		return shared;
	}

	@Override
	public int compareTo(UnitDescriptor o) {
		int compare = o.priority - this.priority;
		if (compare == 0) {
			return this.unitName.compareTo(o.unitName);
		}
		return compare;
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
		result = prime * result
				+ ((unitName == null) ? 0 : unitName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		UnitDescriptor other = (UnitDescriptor) obj;
		if (unitName == null) {
			if (other.unitName != null)
				return false;
		} else if (!unitName.equals(other.unitName))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UnitDescriptor [unitName=" + unitName + ", className="
				+ className + ", priority=" + priority + ", properties="
				+ properties + ", shared=" + shared + "]";
	}

}
