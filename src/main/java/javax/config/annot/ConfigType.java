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
package javax.config.annot;

 import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

 @Retention(RetentionPolicy.RUNTIME)
 @Target(value = { ElementType.TYPE, ElementType.FIELD, ElementType.METHOD })
 public @interface ConfigType {
	 
	 public static final String DEPENDENT = "<DEPENDENT>";
	 public static final String GLOBAL = "GLOBAL";
	 public static final String DOMAIN = "DOMAIN";
	 public static final String APPLICATION = "APPLICATION";
	 public static final String WEB = "WEB";
	 public static final String TENANT = "TENANT";
	 
	 String value() default DEPENDENT;

 }

