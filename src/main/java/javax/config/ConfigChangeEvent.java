/*
 * Copyright (c) 2013, Anatole Tresch.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package javax.config;

public class ConfigChangeEvent {

	public static enum Type {
		REPLACED,
		UPDATED,
		REMOVED,
		ADDED
	}

	private Type changeType;
	private Configuration configuration;
	private Configuration node;
	private PropertyValueMetaInfo value;

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
	public final Configuration getNode() {
		return node;
	}

	/**
	 * @return the value
	 */
	public final PropertyValueMetaInfo getValue() {
		return value;
	}

}