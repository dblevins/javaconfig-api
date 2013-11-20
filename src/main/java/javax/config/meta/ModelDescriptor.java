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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class ModelDescriptor implements Comparable<ModelDescriptor>,
		Serializable {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	private String modelName;
	private List<UnitReference> unitReferences = new ArrayList<>();

	public ModelDescriptor(String modelName) {
		Objects.requireNonNull(modelName);
		this.modelName = modelName;
	}

	@Override
	public int compareTo(ModelDescriptor o) {
		return modelName.compareTo(o.modelName);
	}

	public String getModelName(){
		return modelName;
	}
	
	public List<UnitReference> getUnitReferences() {
		return Collections.unmodifiableList(unitReferences);
	}

	public void addUnitReference(UnitReference ref) {
		Objects.requireNonNull(ref);
		synchronized (unitReferences) {
			if (unitReferences.contains(ref)) {
				throw new IllegalArgumentException("Unit reference '" + ref
						+ " is already registered.");
			}
			this.unitReferences.add(ref);
		}
	}

	public void removeUnitReference(UnitReference ref) {
		Objects.requireNonNull(ref);
		synchronized (unitReferences) {
			unitReferences.remove(ref);
		}
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
				+ ((modelName == null) ? 0 : modelName.hashCode());
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
		ModelDescriptor other = (ModelDescriptor) obj;
		if (modelName == null) {
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModelDescriptor [modelName=" + modelName + ", unitReferences="
				+ unitReferences + "]";
	}

}
