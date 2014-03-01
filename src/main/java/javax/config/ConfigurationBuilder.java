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
import java.util.function.Predicate;

public interface ConfigurationBuilder<T extends ConfigurationBuilder<T>>{

    T setKey(Object key);

    T setEnvironmentSelector(EnvironmentSelector environment);

    T putAll(Configuration config, boolean overrideExisting);

    T putAll(Configuration config);

    T setMetaInfo(String key, Map<String,String> metaInfo);

    T setMetaInfo(String key, String metaInfoKey, String metaInfoValue);

    T put(String key, String value, Map<String,String> metaInfo);

    T put(String key, int value);

    T put(String key, byte value);

    T put(String key, short value);

    T put(String key, boolean value);

    T put(String key, char value);

    T put(String key, float value);

    T put(String key, double value);

    T put(String key, long value);

    Configuration build();

}
