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

public interface ConfigurationModelBuilder {

	public ConfigurationModelBuilder withName(String name);
	
	public ConfigurationModelBuilder withModel(ConfigurationModel type);

	public ConfigurationModelBuilder withModel(String modelId);

	public ConfigurationModelBuilder withConfiguration(Configuration config);

	public ConfigurationModelBuilder withQuery(ConfigurationQuery query);

	public ConfigurationModel build();

}
